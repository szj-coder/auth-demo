package com.example.authdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Slf4j
@SpringBootApplication
@EnableAspectJAutoProxy
@EnableJpaAuditing
public class AuthDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthDemoApplication.class, args);
        log.info("service started");
    }

}
