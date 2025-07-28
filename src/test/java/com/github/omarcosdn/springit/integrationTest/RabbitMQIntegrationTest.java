package com.github.omarcosdn.springit.integrationTest;

import com.github.omarcosdn.springit.integrationTest.config.ContainersConfiguration;
import com.github.omarcosdn.springit.integrationTest.consumer.RabbitMQConsumer;
import com.github.omarcosdn.springit.integrationTest.producer.RabbitMQProducer;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@SpringBootTest(classes = IntegrationTestApplication.class)
@DirtiesContext
public class RabbitMQIntegrationTest extends ContainersConfiguration {

    @Autowired
    private RabbitMQProducer producer;

    @Autowired
    private RabbitMQConsumer consumer;

    @Test
    public void testSendAndReceiveMessage() throws InterruptedException {
        log.info("\n===================================="
                + "\n######## RABBIT - ENVIANDO MENSAGEM"
                + "\n==================================="
        );
        // Mensagem a ser enviada
        String message = "Hello RabbitMQ!";
        log.info("Mensagem enviada: {}", message);
        // Enviar a mensagem
        producer.sendMessage(message);

        log.info("\n====================================="
                + "\n######## RABBIT - RECEBENDO MENSAGEM"
                + "\n===================================="
        );

        // Espera até que a mensagem seja recebida ou o tempo limite seja atingido
        boolean messageReceived = consumer.getLatch().await(10, TimeUnit.SECONDS);

        // Verifica se a mensagem foi recebida com sucesso
        assertTrue(messageReceived, "A mensagem não foi recebida dentro do tempo limite");
        assertEquals(message, consumer.getReceivedMessage(), "A mensagem recebida não corresponde à mensagem enviada");
    }

    @Test
    public void testSendAndReceiveMessageContainer() throws InterruptedException {

        log.info("\n===================================="
                + "\n######## RABBIT - ENVIANDO MENSAGEM2"
                + "\n==================================="
        );
        // Mensagem a ser enviada
        String message = "Hello RabbitMQ!";

        log.info("Mensagem enviada: {}", message);
        // Enviar a mensagem
        producer.sendMessage(message);

        log.info("\n====================================="
                + "\n######## RABBIT - RECEBENDO MENSAGEM2"
                + "\n===================================="
        );
        // Esperar até que a mensagem seja recebida ou o tempo limite seja atingido
        boolean messageReceived = consumer.getLatch().await(10, TimeUnit.SECONDS);
        log.info("Mensagem recebida: {}", consumer.getReceivedMessage());

        // Verificar se a mensagem foi recebida com sucesso
        assertTrue(messageReceived, "A mensagem não foi recebida dentro do tempo limite");
        assertEquals(message, consumer.getReceivedMessage(), "A mensagem recebida não corresponde à mensagem enviada");
    }
} 