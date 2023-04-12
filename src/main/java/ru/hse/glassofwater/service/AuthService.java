package ru.hse.glassofwater.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;
import ru.hse.glassofwater.model.AuthUserData;
import ru.hse.glassofwater.model.User;
import ru.hse.glassofwater.repository.AuthUserDataRepo;
import ru.hse.glassofwater.repository.UserRepo;
import ru.hse.glassofwater.util.AuthStatus;
import ru.hse.glassofwater.util.Pair;
import ru.hse.glassofwater.util.Status;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthUserDataRepo authUserDataRepo;
    private final UserRepo userRepo;
    private final MailService mailService;

    public Status sendCode(String email) {
        String code = generateCode();

        try {
            mailService.sendMessage(email, code);

            AuthUserData authUserData = getAuthUserData(email);
            authUserData.setCode(code);

            authUserDataRepo.save(authUserData);

        } catch (MailException e) {
            e.printStackTrace();
            return new Status("failed");

        } catch (NoSuchElementException e) {
            AuthUserData authUserData = AuthUserData.builder()
                    .code(code)
                    .email(email)
                    .build();

            authUserDataRepo.save(authUserData);
        }

        return new Status("success");
    }

    public Pair<AuthStatus, User> confirmCode(AuthUserData inputData) {
        Pair<AuthStatus, User> result = new Pair<>(AuthStatus.failed, null);

        String email = inputData.getEmail();
        String code = inputData.getCode();

        AuthUserData authUserData;

        try {
            authUserData = getAuthUserData(email);
        } catch (NoSuchElementException e) {
            return result;
        }

        if (authUserData.getCode().equals(code)) {
            User user;

            if (userRepo.findByEmail(email).isPresent()) {
                user = userRepo.findByEmail(email).get();
            } else {
                do {
                    user = getUser(email);
                } while (userRepo.findByUsername(user.getUsername()).isPresent());
            }
            userRepo.save(user);

            authUserDataRepo.delete(authUserData);
            result = new Pair<>(AuthStatus.success, user);
        }
        return result;
    }

    public void delete(AuthUserData authUserData) {
        authUserDataRepo.delete(authUserData);
    }

    private String generateCode() {
        return Integer.toString(new Random().nextInt(9000) + 1000);
    }

    private AuthUserData getAuthUserData(String email) throws NoSuchElementException {
        return authUserDataRepo.findByEmail(email).orElseThrow();
    }

    private User getUser(String email) {
        return User.builder()
                .trips(new ArrayList<>())
                .level("beginner")
                .rate(0)
                .friends(new ArrayList<>())
                .username(generateUsername())
                .email(email)
                .usersInvitations(new ArrayList<>())
                .usersSubscriptions(new ArrayList<>())
                .build();
    }

    private String generateUsername() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("user-");

        for (int i = 0; i < 5; i++) {
            stringBuilder.append(new Random().nextInt(10));
        }
        return stringBuilder.toString();
    }
}
