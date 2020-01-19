package ru.petrov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.petrov.domain.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
