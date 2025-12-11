package com.example.authdemo.transaction;

import com.example.authdemo.util.JsonUtils;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;

/**
 * 使用ds实现的翻译
 *
 * @author szj
 * @date 2025/12/11 18:13
 */
@Slf4j
public class DsTranslate implements Translate {
    @Override
    public String translate(String sourceText, String sourceLanguage, String targetLanguage) {
        String host = "https://api.deepseek.com/chat/completions";
        String key = System.getenv("ds.accessKey");
        if (key == null || key.isBlank()) {
            throw new RuntimeException("ds.accessKey is null");
        }

        DsRequest dsRequest = build(sourceText, sourceLanguage, targetLanguage);

        try (HttpClient httpClient = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder(URI.create(host))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + key)
                    .POST(HttpRequest.BodyPublishers.ofString(JsonUtils.serialization(dsRequest)))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() < 200 || 300 < response.statusCode()) {
                log.error("访问ds出错 url:{} requestBody:{}", request.toString(), JsonUtils.serialization(dsRequest));
                log.error("response code:{}, responseBody:{}", response.statusCode(), response.body());
                throw new RuntimeException("访问ds出错");
            }
            return JsonUtils.jsonMapper.readTree(response.body()).findValue("choices").findValue("message").findValues("content");
        } catch (IOException e) {
            throw new RuntimeException("访问异常", e);
        } catch (InterruptedException e) {
            log.error("连接中断");
            throw new RuntimeException("连接中断", e);
        }
    }

    public DsRequest build(String sourceText, String sourceLanguage, String targetLanguage) {
        Data system = new Data("SYSTEM", """
                你是一个专业的翻译助手，精通多语言翻译。你的目标是提供准确、流畅的翻译，同时保留原意的文化 nuance。
                规则：
                - 目标语言默认为用户指定的（例如“翻译成中文”）。
                - 输出格式：只需要给出翻译结果。
                - 保持简洁、专业，避免添加无关内容。""");

        HashMap<String, String> body = new HashMap<>();
        body.put("sourceText", sourceText);
        body.put("targetLanguage", targetLanguage);
        body.put("sourceLanguage", sourceLanguage);
        Data user = new Data(JsonUtils.serialization(JsonUtils.serialization(body)));

        return new DsRequest(List.of(system, user));
    }

    @lombok.Data
    @NoArgsConstructor
    public static class DsRequest {
        public DsRequest(List<Data> messages) {
            this.messages = messages;
        }

        private String model = "deepseek-chat";
        private List<Data> messages;
        private boolean stream = false;
    }

    @lombok.Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Data {
        public Data(String content) {
            this.role = "user";
            this.content = content;
        }

        private String role;
        private String content;
    }
}
