package com.example.authdemo.learn.netty.client;

import com.example.authdemo.learn.netty.data.ResponseData;
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
        ResponseData data = new ResponseData();
        data.setResponseId(in.readInt());
        int strLen = in.readInt();
        data.setDesc(in.readCharSequence(strLen, charset).toString());
        out.add(data);
    }


}
