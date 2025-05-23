package com.example.authdemo.learn.network.netty.client;

import com.example.authdemo.learn.network.netty.data.ResponseData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ResponseDataDecoder extends ReplayingDecoder<ResponseData> {
    private final Charset charset = StandardCharsets.UTF_8;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        final ResponseData data = new ResponseData();
        data.setResponseId(in.readInt());
        final int strLen = in.readInt();
        data.setDesc(in.readCharSequence(strLen, charset).toString());
        out.add(data);
    }


}
