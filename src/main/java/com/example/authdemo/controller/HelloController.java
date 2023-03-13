package com.example.authdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @author szj
 * @date 2022/01/24 11:12
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public String hello() {
        return "nth hello world";
    }

    @GetMapping("/reactor")
    public Flux<String> helloFlux() {
        return Flux.just("hello world");
    }
}
