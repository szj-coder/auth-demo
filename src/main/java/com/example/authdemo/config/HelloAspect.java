package com.example.authdemo.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 面向切面demo
 *
 * @author szj
 */
@Slf4j
@Aspect
@Component
public class HelloAspect {

    @Before("execution(public * com.example.authdemo.controller.HelloController.*(..))")
    public void before() {
        log.info("hello before.");
    }
}
