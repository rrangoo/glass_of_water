package ru.hse.glassofwater.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hse.glassofwater.model.AuthUserData;

import java.util.Optional;

public interface AuthUserDataRepo extends JpaRepository<AuthUserData, Long> {
    Optional<AuthUserData> findByEmail(String email);
}
