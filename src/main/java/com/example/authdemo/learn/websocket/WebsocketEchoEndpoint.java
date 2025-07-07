package com.example.authdemo.learn.websocket;

import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@ServerEndpoint(value = "/channel/echo/{id}")
public class WebsocketEchoEndpoint implements ApplicationContextAware {
    public static final ConcurrentHashMap<String, WebsocketEchoEndpoint> endpointMap = new ConcurrentHashMap<>();

    private Session session;
    private String id;
    private WebsocketEchoEndpoint targetEndpoint;
    private ApplicationContext applicationContext;

    /**
     * 服务器端收到消息时的回调
     *
     * @param message 客户端发送的消息
     * @param last    是否是最后一条数据
     */
    @OnMessage
    public void onMessage(String message, boolean last) throws IOException {
        log.info("[websocket] 收到消息：id={}，message={}", this.session.getId(), message);

        if (message.equalsIgnoreCase("bye")) {
            // 服务器端关闭连接。状态码为 NORMAL_CLOSURE（正常关闭）。
            log.info("[websocket] 服务器端关闭连接：id={}", this.session.getId());
            this.session.close(new CloseReason(CloseReason.CloseCodes.NORMAL_CLOSURE, "Bye"));
            return;
        } else if (message.startsWith("to")) {
            if (message.equalsIgnoreCase("to")) {
                targetEndpoint = null;
            } else if (message.startsWith("to:")) {
                // 转发到目标
                if (targetEndpoint != null) {
                    targetEndpoint.onMessage("internal:" + message, last);
                }
            } else {
                // 保存目标
                log.info("[websocket] 保存目标 endpoint id={}", message.substring("to".length()));
                this.targetEndpoint = Optional.ofNullable(endpointMap.get(message.substring("to".length())))
                        .orElseThrow(() -> new RuntimeException("目标不存在"));
            }
        }

        // 服务器端返回
        this.session.getAsyncRemote().sendText("[" + Instant.now().toEpochMilli() + "] 服务端 " + message);
    }

    public void send(String message) {
        log.info("[websocket] send id={}, message={}", id, message);
        this.session.getAsyncRemote().sendText(message);
    }

    /**
     * 连接打开
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("id") String id, EndpointConfig endpointConfig) {
        // 保存 session 到对象
        this.session = session;
        this.id = id;
        endpointMap.put(id, this);
        log.info("[websocket] 新的连接：sessionId={}, id={}, websocketPath={}", this.session.getId(), id, this);
    }

    /**
     * 连接关闭
     */
    @OnClose
    public void onClose(CloseReason closeReason) {
        endpointMap.remove(id);
        log.info("[websocket] 连接断开：id={}，reason={}", this.session.getId(), closeReason);
    }

    /**
     * 连接异常
     */
    @OnError
    public void onError(Throwable throwable) throws IOException {
        log.info("[websocket] 连接异常：id={}，throwable={}", this.session.getId(), throwable.getMessage());
        // 关闭连接。状态码为 UNEXPECTED_CONDITION（意料之外的异常）
        endpointMap.remove(id);
        this.session.close(new CloseReason(CloseReason.CloseCodes.UNEXPECTED_CONDITION, throwable.getMessage()));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}

