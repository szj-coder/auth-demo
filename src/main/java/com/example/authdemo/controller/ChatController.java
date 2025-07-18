package com.example.authdemo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {
    private final ChatClient chatClient;

//    @GetMapping(produces = "text/event-stream;charset=UTF-8")
    @GetMapping(produces = "text/plain;charset=UTF-8")
    public Flux<String> chat(@RequestParam String text) {
        return chatClient.prompt(text).stream().content();
    }

    @GetMapping(value = "/html", produces = "text/html;charset=UTF-8")
    public Flux<String> chatHtml(@RequestParam String text) {
        return chatClient.prompt(text + ";使用html格式返回").stream().content();
    }
}
