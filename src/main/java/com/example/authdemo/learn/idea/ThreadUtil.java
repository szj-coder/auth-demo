package com.example.authdemo.learn.idea;

import lombok.SneakyThrows;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadUtil {

    @SneakyThrows
    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor(
                Executors.defaultThreadFactory());
        System.out.println(System.currentTimeMillis());
        executor.scheduleWithFixedDelay(() -> {
            System.out.println(System.currentTimeMillis() + "  Hi!");
        }, 1000L, 1000L, TimeUnit.MILLISECONDS);

        while (true) {
            Thread.sleep(1000);
        }
    }
}
