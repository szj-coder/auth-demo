package com.example.authdemo.config;

import com.example.authdemo.service.AccountDetailsServiceImpl;
import com.example.authdemo.service.DemoAccessDecisionManager;
import com.example.authdemo.service.DemoRememberMeServices;
import com.example.authdemo.service.DemoSecurityMetadataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.session.HttpSessionEventPublisher;

/**
 * @author szj
 * @date 2022/01/27 10:07
 */
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthenticationConfiguration extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    private final AccountDetailsServiceImpl myUserDetailsService;
    private final DemoAuthenticationProvider usernamePasswordAuthenticationProvider;
    private final DemoRememberMeServices demoRememberMeServices;
    private final DemoSecurityMetadataSource secrityMetadataSource;
    private final DemoAccessDecisionManager accessDecisionManager;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder);
        auth.authenticationProvider(usernamePasswordAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        final InMemoryTokenRepositoryImpl tokenRepository = new InMemoryTokenRepositoryImpl();
        final PersistentTokenBasedRememberMeServices rememberMeServices =
                new PersistentTokenBasedRememberMeServices("DemoPersistentToken", myUserDetailsService, tokenRepository);
        // 静态配置
        http.authorizeRequests()
                .antMatchers("/hello/**").permitAll()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setSecurityMetadataSource(secrityMetadataSource);
                        object.setAccessDecisionManager(accessDecisionManager);
                        return object;
                    }
                })
                .and()
                .formLogin().defaultSuccessUrl("/user", true)
                .and()
                .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer
                        .invalidateHttpSession(true)
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                );
        http.securityContext(securitySecurityContextConfigurer -> securitySecurityContextConfigurer
                        .securityContextRepository(new HttpSessionSecurityContextRepository()))
                .sessionManagement(session -> session
                        .invalidSessionUrl("/login")
                        .maximumSessions(1) // 阻止了一个用户多次登录。第二次登录会导致第一次登录无效。
                        .and().sessionFixation().migrateSession() // default Session Fixation
                ).rememberMe(httpSecurityRememberMeConfigurer -> httpSecurityRememberMeConfigurer
                        .rememberMeServices(rememberMeServices)
                        .alwaysRemember(true));
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
}
