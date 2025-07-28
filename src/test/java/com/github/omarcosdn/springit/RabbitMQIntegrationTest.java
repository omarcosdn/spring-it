package com.github.omarcosdn.springit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.omarcosdn.springit.domain.repositories.MessageRepository;
import com.github.omarcosdn.springit.infrastructure.messaging.consumer.RabbitMQConsumer;
import com.github.omarcosdn.springit.infrastructure.messaging.producer.RabbitMQProducer;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(classes = Application.class)
@DirtiesContext
public class RabbitMQIntegrationTest extends TestContainersConfiguration {

  @Autowired private RabbitMQProducer producer;

  @Autowired private RabbitMQConsumer consumer;

  @Autowired private MessageRepository messageRepository;

  @Test
  public void testSendAndReceiveMessage() throws InterruptedException {
    consumer.resetLatch();

    var message = "Hello RabbitMQ!";

    producer.sendMessage(message);

    var messageReceived = consumer.getLatch().await(10, TimeUnit.SECONDS);

    assertTrue(messageReceived, "The message was not received within the time limit");

    var savedMessages = messageRepository.findByContentContainingIgnoreCase(message);
    assertFalse(savedMessages.isEmpty(), "The message was not saved in the database");

    var savedMessage = savedMessages.getFirst();
    assertEquals(
        message, savedMessage.getContent(), "The content of the saved message does not match");
    assertNotNull(savedMessage.getReceivedAt(), "The receipt date has not been set");
    assertNotNull(savedMessage.getId(), "Message ID not generated");
  }

  @Test
  public void testMultipleMessagesSavedInDatabase() throws InterruptedException {
    consumer.resetLatch();

    messageRepository.deleteAll();

    String[] messages = {"First test message", "Second test message", "Third test message"};

    for (var message : messages) {
      producer.sendMessage(message);
      Thread.sleep(1000);
    }

    Thread.sleep(2000);

    var allMessages = messageRepository.findAll();
    assertEquals(
        messages.length,
        allMessages.size(),
        "The number of saved messages does not match the number sent");

    for (var originalMessage : messages) {
      var savedMessages = messageRepository.findByContentContainingIgnoreCase(originalMessage);
      assertFalse(
          savedMessages.isEmpty(),
          "The message '" + originalMessage + "' was not found in the database");
    }
  }
}
