package ru.hse.glassofwater.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hse.glassofwater.model.UserInfo;

public interface UserInfoRepo extends JpaRepository<UserInfo, Long> {
    UserInfo getByEmail(String email);
}
