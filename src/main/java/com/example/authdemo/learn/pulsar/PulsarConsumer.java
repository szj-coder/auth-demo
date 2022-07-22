package com.example.authdemo.learn.pulsar;

import org.apache.pulsar.client.api.*;

import java.util.concurrent.TimeUnit;

/**
 * @author szj
 * @date 2022/06/09 10:26
 */
public class PulsarConsumer {

    public static void main(String[] args) throws PulsarClientException {
        PulsarClient client = PulsarClient.builder()
                .serviceUrl("pulsar://localhost:6650")
                .build();
        Consumer<String> consumer = client.newConsumer(Schema.STRING)
                .topic("test")
                .consumerName("testConsumer")
                .subscriptionName("testSubscriptionName")
                .subscribe();
        new Thread(() -> {
            long dateSum = 0L;
            long dateCount = 0L;
            long lastTime = System.currentTimeMillis();
            while (true) {
                try {
                    Message<String> message = consumer.receive(100, TimeUnit.MILLISECONDS);
                    if (message == null) {
                        continue;
                    }
                    long time = Long.parseLong(message.getValue());
                    dateSum += System.currentTimeMillis() - time;
                    dateCount++;
                    System.out.println("耗时: " + (System.currentTimeMillis() - time));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (System.currentTimeMillis() - lastTime > 10000) {
                    System.out.println(String.format("平均: %s 条数: %s",  (dateSum/dateCount), dateCount));
                    dateSum = 0;
                    dateCount = 0;
                    lastTime = System.currentTimeMillis();
                }
            }
        }).start();
    }
}
