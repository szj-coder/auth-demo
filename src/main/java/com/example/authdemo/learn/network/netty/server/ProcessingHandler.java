package com.example.authdemo.learn.network.netty.server;

import com.example.authdemo.learn.network.netty.data.RequestData;
import com.example.authdemo.learn.network.netty.data.ResponseData;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProcessingHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        final RequestData requestData = (RequestData) msg;
        final ResponseData responseData = new ResponseData();
        responseData.setResponseId(requestData.getId());
        responseData.setDesc(Boolean.TRUE.toString());
        final ChannelFuture future = ctx.writeAndFlush(responseData);
        future.addListener(ChannelFutureListener.CLOSE);
        System.out.println("收到讯息 address:" + ctx.channel().remoteAddress() + " msg:" + requestData);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error(cause.getMessage(), cause);
        ctx.close();
    }
}