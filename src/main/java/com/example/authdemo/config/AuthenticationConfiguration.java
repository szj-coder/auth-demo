package com.example.authdemo.config;

import com.example.authdemo.service.DemoAccessDecisionManager;
import com.example.authdemo.service.DemoSecurityMetadataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import javax.sql.DataSource;

/**
 * @author szj
 * @date 2022/01/27 10:07
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class AuthenticationConfiguration {

    private final PasswordEncoder passwordEncoder;

    private final DemoSecurityMetadataSource securityMetadataSource;
    private final DemoAccessDecisionManager accessDecisionManager;


    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
                .build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, UserDetailsService userDetailsService) throws Exception {
        final InMemoryTokenRepositoryImpl tokenRepository = new InMemoryTokenRepositoryImpl();
        final PersistentTokenBasedRememberMeServices rememberMeServices =
                new PersistentTokenBasedRememberMeServices("DemoPersistentToken", userDetailsService, tokenRepository);
        // 静态配置
        http.authorizeHttpRequests(requests -> requests
                        .requestMatchers("/hello/**").permitAll()
                        .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                            @Override
                            public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                                object.setSecurityMetadataSource(securityMetadataSource);
                                object.setAccessDecisionManager(accessDecisionManager);
                                return object;
                            }
                        }))
                        .formLogin(login -> login.defaultSuccessUrl("/user", true))
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
        return http.build();
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
}
