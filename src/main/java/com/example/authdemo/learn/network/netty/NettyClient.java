package com.example.authdemo.learn.network.netty;

import com.example.authdemo.learn.network.netty.client.ClientHandler;
import com.example.authdemo.learn.network.netty.client.RequestDataEncoder;
import com.example.authdemo.learn.network.netty.client.ResponseDataDecoder;
import com.example.authdemo.learn.network.netty.data.RequestData;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class NettyClient {
    private static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());


    public static void main(String[] args) throws Exception {
        final String host = "localhost";
        final int port = 8080;
        final EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            final Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new RequestDataEncoder(), new ResponseDataDecoder(), new ClientHandler());
                }
            });
            final ChannelFuture f = b.connect(host, port).sync();
            final Channel channel = f.channel();
            int num = 0;
            while (true) {
                final Scanner scanner = new Scanner(System.in);
                final RequestData data = new RequestData(num++, scanner.nextLine());
                if ("close".equalsIgnoreCase(data.getTime())) {
                    channel.closeFuture().sync();
                    break;
                }
                threadPoolExecutor.execute(() -> {
                    final Channel c = f.channel();
                    c.writeAndFlush(data);
                    System.out.println("发送消息：" + data);
                });
            }
            System.out.println(channel.remoteAddress());
            System.out.println("-=---");
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}