package com.example.authdemo.learn.future;

import lombok.SneakyThrows;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * future 学习
 * {@see https://www.jianshu.com/p/6bac52527ca4}
 */
public class CompletableFutureLearn {
    @SneakyThrows
    public static void main(String[] args) {
        test();
        acceptEither();
    }

    /**
     * 串行执行
     */
    public static void test() throws ExecutionException, InterruptedException {
        System.out.println(Thread.currentThread());
        final CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("start1: " + Thread.currentThread());
            for (int i = 0; i < 3; i++) {
                System.out.println("i = " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("start1: ");
            System.out.println("--------");
            return true;
        });
        final CompletableFuture<String> future1 = future.thenApply((res) -> {
            System.out.println("start2: " + Thread.currentThread());
            return res.toString();
        });
        System.out.println(">> main start");
        System.out.println(">> future1: " + future1.get());
    }

    private static void acceptEither() throws Exception {
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f1=" + t);
            return t;
        });

        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f2=" + t);
            return t;
        });
        f1.acceptEither(f2, System.out::println).get();
    }
}
