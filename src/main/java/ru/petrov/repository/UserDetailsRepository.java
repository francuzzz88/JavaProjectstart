package ru.petrov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.petrov.domain.User;

public interface UserDetailsRepository extends JpaRepository<User, String> {
}
