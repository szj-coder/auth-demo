package com.example.authdemo.learn.pulsar;

import lombok.SneakyThrows;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

/**
 * @author szj
 * @date 2022/06/09 10:26
 */
public class PulsarProducer {

    @SneakyThrows
    public static void main(String[] args) {
        final PulsarClient client = PulsarClient.builder().serviceUrl("pulsar://localhost:6650").build();
        final Producer<String> producer = client.newProducer(Schema.STRING).topic("test").create();
        final CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            for (int i = 0; i < 100000; i++) {
                final long date = System.currentTimeMillis();
                System.out.println("send: " + new Date(date));
                try {
                    producer.send(Long.toString(date));
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
