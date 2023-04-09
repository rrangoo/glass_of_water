package ru.hse.glassofwater.repository;

import org.springframework.data.repository.CrudRepository;
import ru.hse.glassofwater.model.UserInfo;

import java.util.List;

public interface UserInfoRepo extends CrudRepository<UserInfo, Long> {
    UserInfo getByEmail(String email);
}
