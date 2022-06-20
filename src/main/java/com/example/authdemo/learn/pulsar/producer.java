package com.example.authdemo.learn.pulsar;

import lombok.SneakyThrows;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * @author szj
 * @date 2022/06/09 10:26
 */
public class producer {

    @SneakyThrows
    public static void main(String[] args) {
        PulsarClient client = PulsarClient.builder().serviceUrl("pulsar://localhost:6650").build();
        Producer<String> producer = client.newProducer(Schema.STRING).topic("test").create();
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            for (int i = 0; i < 10; i++) {
                Date date = new Date();
                System.out.println("send: " + date);
                try {
                    producer.send(date.toString());
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        future.thenRun(() -> {
            System.out.println("send success");
            try {
                client.close();
                producer.close();
            } catch (PulsarClientException e) {
                throw new RuntimeException(e);
            }
        });
        future.get();
        future.get();
    }

}
