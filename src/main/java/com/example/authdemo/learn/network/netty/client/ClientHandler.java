package com.example.authdemo.learn.network.netty.client;

import com.example.authdemo.learn.network.netty.data.RequestData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalTime;
import java.util.Random;

@Slf4j
public class ClientHandler extends ChannelInboundHandlerAdapter {

    private ByteBuf tmp;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        RequestData msg = new RequestData();
        msg.setId(new Random().nextInt(100));
        msg.setTime(LocalTime.now().toString());
        System.out.println("=== build msg:" + msg);
        ctx.writeAndFlush(msg);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("收到服务端的消息" + msg);
//        ctx.writeAndFlush(new RequestData(-1, "收到"));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error(cause.getMessage(), cause);
//        ctx.close();
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        System.out.println("=== Handler added");
        tmp = ctx.alloc().buffer(4);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        System.out.println("=== Handler removed");
        tmp.release();
        tmp = null;
    }
}