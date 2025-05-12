package com.example.authdemo.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class ChatConfig {

    @Bean
    public VectorStore vectorStore(EmbeddingModel model) {
        return SimpleVectorStore.builder(model).build();
    }

    @Bean
    public ChatClient chatClient(@Value("classpath:wikipedia-typhoon.pdf") Resource hurricaneDocs,
                          ChatClient.Builder chatClientBuilder, VectorStore vectorStore) {
//        vectorStore.add(new TokenTextSplitter().split(new PagePdfDocumentReader(hurricaneDocs).read()));
        return chatClientBuilder
                .defaultSystem("你是一个全能专家") // Set the system prompt
                .defaultAdvisors(new MessageChatMemoryAdvisor(new InMemoryChatMemory())) // Enable chat memory
//                .defaultAdvisors(new QuestionAnswerAdvisor(vectorStore)) // Enable RAG
                .build();
    }
}
