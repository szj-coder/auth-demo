package com.example.authdemo.learn.thread;

import lombok.SneakyThrows;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * @author szj
 * @date 2022/06/29 16:49
 */
public class CompletableFutureLearn {
    @SneakyThrows
    public static void main(String[] args) {
        CompletableFuture<String> future = null;
        CompletableFuture<String> future1 = null;
        for (int i = 0; i < 10; i++) {
            int num = i;
            if (future == null) {
                future = build(String.valueOf(i));
            } else {
                future = future.applyToEitherAsync(build(String.valueOf(i)), (res) -> {
                    System.out.println(">> " + num + " : " + res);
                    return res;
                });
            }
            if (i == 2) {
                future1 = future.whenComplete((res, e) -> {
                    System.out.println("complete2: " + res);
                });
            }
//            future.get();
        }
        future = future.whenCompleteAsync((res, e) -> {
            System.out.println("complete: " + res);
        }).exceptionally((e) -> {
            System.out.println("error: " + e.getMessage());
            return null;
        });
        future.get();

        future.whenComplete((res, e) -> {
            System.out.println("complete1: " + res);
        }).get();
        future1.get();
    }

    public static CompletableFuture<String> build(String str) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(new Random().nextInt(1000));
//                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + "  ==> " + str);
            if (Integer.parseInt(str) == 9 && new Random().nextInt(10) % 2 == 0) {
                throw new RuntimeException("err");
            }
            return str;
        });
    }
}
