//package com.example.authdemo.transaction;
//
//public class AliAsyncTransaction {
//    public static void main(String[] args) throws Exception {
//
//        // HttpClient Configuration
//        /*HttpClient httpClient = new ApacheAsyncHttpClientBuilder()
//                .connectionTimeout(Duration.ofSeconds(10)) // Set the connection timeout time, the default is 10 seconds
//                .responseTimeout(Duration.ofSeconds(10)) // Set the response timeout time, the default is 20 seconds
//                .maxConnections(128) // Set the connection pool size
//                .maxIdleTimeOut(Duration.ofSeconds(50)) // Set the connection pool timeout, the default is 30 seconds
//                // Configure the proxy
//                .proxy(new ProxyOptions(ProxyOptions.Type.HTTP, new InetSocketAddress("<your-proxy-hostname>", 9001))
//                        .setCredentials("<your-proxy-username>", "<your-proxy-password>"))
//                // If it is an https connection, you need to configure the certificate, or ignore the certificate(.ignoreSSL(true))
//                .x509TrustManagers(new X509TrustManager[]{})
//                .keyManagers(new KeyManager[]{})
//                .ignoreSSL(false)
//                .build();*/
//
//        // Configure Credentials authentication information, including ak, secret, token
//        StaticCredentialProvider provider = StaticCredentialProvider.create(Credential.builder()
//                // Please ensure that the environment variables ALIBABA_CLOUD_ACCESS_KEY_ID and ALIBABA_CLOUD_ACCESS_KEY_SECRET are set.
//                .accessKeyId(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID"))
//                .accessKeySecret(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_SECRET"))
//                //.securityToken(System.getenv("ALIBABA_CLOUD_SECURITY_TOKEN")) // use STS token
//                .build());
//
//        // Configure the Client
//        AsyncClient client = AsyncClient.builder()
//                .region("cn-hangzhou") // Region ID
//                //.httpClient(httpClient) // Use the configured HttpClient, otherwise use the default HttpClient (Apache HttpClient)
//                .credentialsProvider(provider)
//                //.serviceConfiguration(Configuration.create()) // Service-level configuration
//                // Client-level configuration rewrite, can set Endpoint, Http request parameters, etc.
//                .overrideConfiguration(
//                        ClientOverrideConfiguration.create()
//                                // Endpoint 请参考 https://api.aliyun.com/product/alimt
//                                .setEndpointOverride("mt.cn-hangzhou.aliyuncs.com")
//                        //.setConnectTimeout(Duration.ofSeconds(30))
//                )
//                .build();
//
//        // Parameter settings for API request
//        TranslateGeneralRequest translateGeneralRequest = TranslateGeneralRequest.builder()
//                // Request-level configuration rewrite, can set Http request parameters, etc.
//                // .requestConfiguration(RequestConfiguration.create().setHttpHeaders(new HttpHeaders()))
//                .build();
//
//        // Asynchronously get the return value of the API request
//        CompletableFuture<TranslateGeneralResponse> response = client.translateGeneral(translateGeneralRequest);
//        // Synchronously get the return value of the API request
//        TranslateGeneralResponse resp = response.get();
//        System.out.println(new Gson().toJson(resp));
//        // Asynchronous processing of return values
//        /*response.thenAccept(resp -> {
//            System.out.println(new Gson().toJson(resp));
//        }).exceptionally(throwable -> { // Handling exceptions
//            System.out.println(throwable.getMessage());
//            return null;
//        });*/
//
//        // Finally, close the client
//        client.close();
//    }
//}
