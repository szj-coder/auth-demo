package com.example.authdemo.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.text.MessageFormat;
import java.util.Map;

public class JsonUtils {
    public static final JsonMapper jsonMapper = new JsonMapper();

    public static String serialization(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return jsonMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static <T> T deserialization(String content, TypeReference<T> valueTypeRef) {
        if (content == null || content.isBlank()) {
            return null;
        }
        try {
            return jsonMapper.readValue(content, valueTypeRef);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(MessageFormat.format("反序列化失败:{0}", e.getMessage()), e);
        }
    }

    public static Map<String, Object> deserialization(String content) {
        return deserialization(content, new TypeReference<>() {
        });
    }
}
