package com.example.authdemo.learn.pulsar;

import org.apache.pulsar.client.api.*;

import java.util.concurrent.TimeUnit;

/**
 * @author szj
 * @date 2022/06/09 10:26
 */
public class consumer {

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
            while (true) {
                try {
                    Message<String> message = consumer.receive(100, TimeUnit.MINUTES);
                    System.out.println("consumer:" + message.getValue());
                } catch (PulsarClientException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
