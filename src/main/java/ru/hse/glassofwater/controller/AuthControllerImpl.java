package ru.hse.glassofwater.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hse.glassofwater.dto.EmailDto;
import ru.hse.glassofwater.model.AuthUserData;
import ru.hse.glassofwater.model.User;
import ru.hse.glassofwater.service.AuthService;
import ru.hse.glassofwater.util.AuthStatus;
import ru.hse.glassofwater.util.Pair;
import ru.hse.glassofwater.util.Status;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {
    private final AuthService authService;

    @Override
    public Status sendCodeByEmail(EmailDto emailDto) {
        return authService.sendCode(emailDto.getEmail());
    }

    @Override
    public Pair<AuthStatus, User> confirmEmail(AuthUserData authUserData) {
        return authService.confirmCode(authUserData);
    }

    @Override
    public void deleteAuthUserData(AuthUserData authUserData) {
        authService.delete(authUserData);
    }
}




