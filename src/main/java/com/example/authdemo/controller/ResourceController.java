package com.example.authdemo.controller;

import com.example.authdemo.auth.Role;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author szj
 * @date 2022/03/04 18:43
 */
@RestController
@RequestMapping("/resource")
public class ResourceController {

    @GetMapping("admin")
    @RolesAllowed(Role.ADMIN)
    public String admin() {
        return "welcome admin";
    }

    @GetMapping("user")
    @RolesAllowed(Role.USER)
    public String user() {
        return "welcome user";
    }
}
