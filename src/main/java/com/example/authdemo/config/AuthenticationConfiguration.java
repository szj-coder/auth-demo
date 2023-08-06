package com.example.authdemo.config;

import com.example.authdemo.service.AccountDetailsServiceImpl;
import com.example.authdemo.service.DemoRememberMeServices;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
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
                new PersistentTokenBasedRememberMeServices("PersistentToken", myUserDetailsService, tokenRepository);
        http.authorizeRequests()
                .antMatchers("/hello/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().defaultSuccessUrl("/user", true)
                .and()
                .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer
                        .deleteCookies("JSESSIONID") // todo 什么时候设置的这个cookies呢？
                        .invalidateHttpSession(true)
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                ).securityContext(securitySecurityContextConfigurer -> securitySecurityContextConfigurer
                        .securityContextRepository(new HttpSessionSecurityContextRepository()))
                .sessionManagement(session -> session
                        .invalidSessionUrl("/invalidSession.htm")
                        .maximumSessions(1) // 阻止了一个用户多次登录。第二次登录会导致第一次登录无效。
//                        .maxSessionsPreventsLogin(true)
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
