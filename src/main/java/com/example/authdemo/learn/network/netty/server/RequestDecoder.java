package com.example.authdemo.learn.network.netty.server;

import com.example.authdemo.learn.network.netty.data.RequestData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class RequestDecoder extends ReplayingDecoder<RequestData> {
    private final Charset charset = StandardCharsets.UTF_8;

    @Override
    protected void decode(ChannelHandlerContext ctx,
                    ByteBuf in, List<Object> out) throws Exception {
        final RequestData data = new RequestData();
        data.setId(in.readInt());
        final int strLen = in.readInt();
        if (strLen < 0) {
            out.add(data);
            return;
        }
        data.setTime(in.readCharSequence(strLen, charset).toString());
        out.add(data);
    }
}