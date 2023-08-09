package com.example.authdemo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import java.util.Collection;


@Slf4j
@Component
public class DemoAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        log.info("access decision manager, decide:<{}>.", configAttributes);
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        log.info("access decision manager, supports attribute:<{}>.", attribute.getAttribute());
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        log.info("access decision manager, supports class:<{}>.", clazz.getName());
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
