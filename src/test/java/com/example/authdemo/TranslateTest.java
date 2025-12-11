package com.example.authdemo;

import com.example.authdemo.transaction.AliTranslate;
import com.example.authdemo.transaction.DsTranslate;
import org.junit.jupiter.api.Test;

public class TranslateTest {

    @Test
    public void ali() {
        AliTranslate aliTranslate = new AliTranslate();
        String sourceText = "你好，很高心见到你，您吃了吗？";

        System.out.println(aliTranslate.translate(sourceText, "zh", "en"));
        System.out.println(aliTranslate.translate(sourceText, "zh", "zh-tw"));
        System.out.println(aliTranslate.translate(sourceText, "zh", "ar"));
    }

    @Test
    public void ds() {
        DsTranslate dsTranslate = new DsTranslate();
        String sourceText = "你好，很高心见到你，您吃了吗？";

        System.out.println(dsTranslate.translate(sourceText, "zh", "en"));
        System.out.println(dsTranslate.translate(sourceText, "zh", "zh-tw"));
        System.out.println(dsTranslate.translate(sourceText, "zh", "ar"));
    }
}
