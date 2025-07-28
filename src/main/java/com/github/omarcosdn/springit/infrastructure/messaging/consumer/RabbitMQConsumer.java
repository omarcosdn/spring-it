package com.github.omarcosdn.springit.infrastructure.messaging.consumer;

import com.github.omarcosdn.springit.domain.entities.Message;
import com.github.omarcosdn.springit.domain.repositories.MessageRepository;
import com.github.omarcosdn.springit.infrastructure.config.RabbitMQConfig;
import java.util.concurrent.CountDownLatch;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class RabbitMQConsumer {

  private final MessageRepository messageRepository;

  @Getter private CountDownLatch latch = new CountDownLatch(1);

  @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
  public void receiveMessage(final String message) {
    log.info("Message received: {}", message);

    try {
      var messageEntity = new Message(message, RabbitMQConfig.QUEUE_NAME);
      var savedMessage = messageRepository.save(messageEntity);

      log.info("Message saved with ID: {}", savedMessage.getId());

      latch.countDown();

    } catch (Exception e) {
      log.error("An error occurs saving message: {}", e.getMessage(), e);
      latch.countDown();
    }
  }

  public void resetLatch() {
    this.latch = new CountDownLatch(1);
  }
}
