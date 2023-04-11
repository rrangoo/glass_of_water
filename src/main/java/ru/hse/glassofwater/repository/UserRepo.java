package ru.hse.glassofwater.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hse.glassofwater.model.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String Username);
}
