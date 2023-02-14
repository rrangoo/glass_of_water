package ru.hse.glassofwater.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hse.glassofwater.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
