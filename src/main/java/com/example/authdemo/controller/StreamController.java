package com.example.authdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/stream")
public class StreamController {

    @GetMapping
    public void stream(HttpServletResponse response) throws IOException {
        // PipedOutputStream 可以做到异步输出
        ServletOutputStream outputStream = response.getOutputStream();
        int i = 0;
        while (i < 100) {
            outputStream.println(i++);
            outputStream.flush();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
    }
}
