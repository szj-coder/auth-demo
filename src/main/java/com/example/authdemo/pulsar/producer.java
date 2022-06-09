package com.example.authdemo.pulsar;

import lombok.SneakyThrows;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;

import java.util.Date;

/**
 * @author szj
 * @date 2022/06/09 10:26
 */
public class producer {

    @SneakyThrows
    public static void main(String[] args) {
        PulsarClient client = PulsarClient.builder()
                .serviceUrl("pulsar://localhost:6650")
                .build();
        Producer<String> test = client.newProducer(Schema.STRING)
                .topic("test")
                .create();
        new Thread(() -> {
            while (true) {
                Date date = new Date();
                System.out.println("send: " + date);
                try {
                    test.send(date.toString());
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
