package com.github.omarcosdn.springit.infrastructure.messaging.producer;

import com.github.omarcosdn.springit.infrastructure.config.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RabbitMQProducer {

  private final RabbitTemplate rabbitTemplate;

  public void sendMessage(final String message) {
    rabbitTemplate.convertAndSend(
        RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, message);
  }
}
