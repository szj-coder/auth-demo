package com.example.authdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author szj
 * @date 2022/01/24 11:12
 */
@Controller
@RequestMapping("")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "nth hello world";
    }
}
