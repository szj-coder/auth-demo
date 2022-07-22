package com.example.authdemo.controller;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;

/**
 * @author szj
 * @date 2022/07/12 09:49
 */
public class VertexController {
    private static final Vertx VERTX = Vertx.vertx();

    public static void main(String[] args) {
        HttpServer httpServer = VERTX.createHttpServer();
        httpServer.requestHandler(request -> {
            request.response().end("vertex hello world!");
        });
    }
}
