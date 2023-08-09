package com.example.authdemo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import java.util.Collection;


@Slf4j
@Component
public class DemoSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String requestURI =
                ((FilterInvocation) object).getRequest().getRequestURI();
        log.info("security metadata, access url:" + requestURI);
        return SecurityConfig.createList("ROLE_ADMIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return SecurityConfig.createList("ROLE_ADMIN");
    }

    @Override
    public boolean supports(Class<?> clazz) {
        log.info("security metadata, supports:<{}>.", clazz.getName());
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}