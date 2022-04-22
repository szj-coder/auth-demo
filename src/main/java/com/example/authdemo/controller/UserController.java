package com.example.authdemo.controller;

import com.example.authdemo.service.AccountDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
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

    private final AccountDetailsServiceImpl userDetailsService;

    @GetMapping
    public String getCurrentUser() {
        log.info(SecurityContextHolder.getContext().getAuthentication().toString());
        return SecurityContextHolder.getContext().getAuthentication().getDetails().toString();
    }

    @GetMapping("{username}")
    public String getByUsername(@PathVariable("username") String username) {
        return userDetailsService.loadUserByUsername(username).toString();
    }
}
