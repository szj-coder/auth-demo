package com.example.authdemo.transaction;

import com.aliyun.alimt20181012.Client;
import com.aliyun.alimt20181012.models.TranslateGeneralRequest;
import com.aliyun.alimt20181012.models.TranslateGeneralResponse;
import com.aliyun.tea.TeaException;
import com.aliyun.teautil.Common;
import com.aliyun.teautil.models.RuntimeOptions;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AliTranslate implements Translate {

    /**
     * <b>description</b> :
     * <p>使用凭据初始化账号Client</p>
     *
     * @return Client
     */
    public static Client createClient() throws Exception {
        // 工程代码建议使用更安全的无AK方式，凭据配置方式请参见：https://help.aliyun.com/document_detail/378657.html。
        com.aliyun.credentials.Client credential = new com.aliyun.credentials.Client();
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                .setCredential(credential);
        // Endpoint 请参考 https://api.aliyun.com/product/alimt
        config.endpoint = "mt.cn-hangzhou.aliyuncs.com";
        String accessKey = System.getenv("ali.accessKey");
        String accessSecret = System.getenv("ali.accessSecret");
        config.setAccessKeyId(accessKey);
        config.setAccessKeySecret(accessSecret);
        return new Client(config);
    }

    public String translate(String sourceText, String sourceLanguage, String targetLanguage) {
        Client client;
        try {
            client = createClient();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        TranslateGeneralRequest translateGeneralRequest = new TranslateGeneralRequest();

        translateGeneralRequest.formatType = "text";
        translateGeneralRequest.sourceText = sourceText;
        translateGeneralRequest.sourceLanguage = sourceLanguage;
        translateGeneralRequest.targetLanguage = targetLanguage;
        translateGeneralRequest.scene = "general";

        RuntimeOptions runtime = new RuntimeOptions();
        try {
            // 复制代码运行请自行打印 API 的返回值
            TranslateGeneralResponse translateGeneralResponse = client.translateGeneralWithOptions(translateGeneralRequest, runtime);
            return translateGeneralResponse.body.data.translated;
        } catch (TeaException error) {
            // 此处仅做打印展示，请谨慎对待异常处理，在工程项目中切勿直接忽略异常。
            // 错误 message
            log.error(error.getMessage());
            // 诊断地址
            if (error.getData() != null) {
                log.error(String.valueOf(error.getData().get("Recommend")));
            }
            return Common.assertAsString(error.message);
        } catch (Exception _error) {
            TeaException error = new TeaException(_error.getMessage(), _error);
            // 此处仅做打印展示，请谨慎对待异常处理，在工程项目中切勿直接忽略异常。
            // 错误 message
            log.error(error.getMessage());
            // 诊断地址
            if (error.getData() != null) {
                log.error(String.valueOf(error.getData().get("Recommend")));
            }
            return Common.assertAsString(error.message);
        }
    }

}
