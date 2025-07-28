package com.github.omarcosdn.springit.domain.repositories;

import com.github.omarcosdn.springit.domain.entities.Message;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

  List<Message> findByContentContainingIgnoreCase(String content);
}
