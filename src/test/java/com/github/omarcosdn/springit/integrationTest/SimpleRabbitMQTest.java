package com.github.omarcosdn.springit.integrationTest;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
public class SimpleRabbitMQTest {

    @Container
    static RabbitMQContainer rabbit = new RabbitMQContainer("rabbitmq:3.12-management")
            .withExposedPorts(5672, 15672);

    @Test
    public void testRabbitMQContainer() {
        assertTrue(rabbit.isRunning());
        System.out.println("RabbitMQ container is running on port: " + rabbit.getAmqpPort());
    }
} 