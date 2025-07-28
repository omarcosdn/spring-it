package com.github.omarcosdn.springit.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "messages")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 1000)
  private String content;

  @Column(name = "received_at", nullable = false)
  private LocalDateTime receivedAt;

  @Column(name = "queue_name", nullable = false)
  private String queueName;

  public Message(String content, String queueName) {
    this.content = content;
    this.queueName = queueName;
    this.receivedAt = LocalDateTime.now();
  }
}
