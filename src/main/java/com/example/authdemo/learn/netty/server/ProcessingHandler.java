package com.example.authdemo.learn.netty.server;

import com.example.authdemo.learn.netty.data.RequestData;
import com.example.authdemo.learn.netty.data.ResponseData;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ProcessingHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        RequestData requestData = (RequestData) msg;
        ResponseData responseData = new ResponseData();
        responseData.setResponseId(requestData.getId());
        responseData.setDesc(Boolean.TRUE.toString());
        ChannelFuture future = ctx.writeAndFlush(responseData);
        future.addListener(ChannelFutureListener.CLOSE);
        System.out.println(">>> " + ctx.channel().remoteAddress() + " " + requestData);
    }
}