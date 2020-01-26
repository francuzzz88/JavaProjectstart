package ru.petrov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.petrov.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
