package com.example.authdemo.learn.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Optional;

public class ReactorContext {

    /**
     * 测试reactor上下文传播
     */
    public static void main(String[] args) {
        Mono.just("hello")
                .doOnNext(System.out::println)
                .flatMap(it -> {
                    return Mono.deferContextual(ctx -> {
                        Optional<Object> optional = ctx.getOrEmpty(it);
                        if (optional.isEmpty()) {
                            return Mono.just("gg");
                        }
                        return Mono.just(String.valueOf(optional.get()));
                    }).doOnEach(ctx -> {
                        System.out.println(ctx.getContextView());
                    }).doOnNext(it1 -> {
                        Flux.range(1, 10)
                                .doOnEach(ctx -> {
                                    System.out.println("child:" + ctx.getContextView().getOrDefault("hello", null));
                                }).subscribeOn(Schedulers.boundedElastic())
                                .subscribe(System.out::println);
                    });
                }).contextWrite(ctx -> {
                    return ctx.put("hello", "world");
                }).subscribe(System.out::println);
    }
}
