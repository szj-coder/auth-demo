package com.example.authdemo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.shade.io.swagger.annotations.Authorization;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author szj
 * @date 2022/01/27 17:20
 */
@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserDetailsService userDetailsService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public String getCurrentUser() {
        log.info(SecurityContextHolder.getContext().getAuthentication().toString());
        final String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return "hello %s\n".formatted(name) + SecurityContextHolder.getContext().getAuthentication().getDetails().toString();
    }

    @GetMapping("{username}")
    public String getByUsername(@PathVariable String username) {
        return userDetailsService.loadUserByUsername(username).toString();
    }
}
