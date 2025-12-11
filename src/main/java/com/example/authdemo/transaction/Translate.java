package com.example.authdemo.transaction;

public interface Translate {
    /**
     * 翻译
     *
     * @param sourceText     翻译文本
     * @param sourceLanguage 翻译文本的语言类型
     * @param targetLanguage 目标语言类型
     * @return 翻译结果
     */
    String translate(String sourceText, String sourceLanguage, String targetLanguage);
}
