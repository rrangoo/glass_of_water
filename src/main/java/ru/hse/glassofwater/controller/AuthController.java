package ru.hse.glassofwater.controller;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.hse.glassofwater.dto.EmailDto;
import ru.hse.glassofwater.model.AuthUserData;
import ru.hse.glassofwater.model.User;
import ru.hse.glassofwater.util.AuthStatus;
import ru.hse.glassofwater.util.Pair;
import ru.hse.glassofwater.util.Status;

public interface AuthController {
    @PostMapping("/email")
    Status sendCodeByEmail(@RequestBody EmailDto emailDto);

    @PostMapping("/code")
    Pair<AuthStatus, User> confirmEmail(@RequestBody AuthUserData authUserData);

    @DeleteMapping("{id}")
    void deleteAuthUserData(@PathVariable("id") AuthUserData authUserData);
}
