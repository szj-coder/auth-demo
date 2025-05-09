package com.example.authdemo.learn.pulsar;

import lombok.SneakyThrows;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

public class FutureTest {
    @SneakyThrows
    public static void main(String[] args) {
        final AtomicInteger i = new AtomicInteger();
        final CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("future => " + i.getAndIncrement());
            return i.get();
        });
        for (int j = 0; j < 10; j++) {
            System.out.println(future.get().toString().concat(" " + future.join()));
        }
    }
}
