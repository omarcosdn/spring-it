package com.github.omarcosdn.springit.integrationTest.consumer;

import com.github.omarcosdn.springit.integrationTest.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class RabbitMQConsumer {

    private final CountDownLatch latch = new CountDownLatch(1);
    private String receivedMessage;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveMessage(String message) {
        this.receivedMessage = message;
        latch.countDown();  // Sinaliza que a mensagem foi recebida
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public String getReceivedMessage() {
        return receivedMessage;
    }
} 