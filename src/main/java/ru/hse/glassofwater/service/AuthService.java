package ru.hse.glassofwater.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hse.glassofwater.model.User;
import ru.hse.glassofwater.model.UserInfo;
import ru.hse.glassofwater.repository.UserInfoRepo;
import ru.hse.glassofwater.repository.UserRepo;
import ru.hse.glassofwater.util.AuthStatus;
import ru.hse.glassofwater.util.Pair;
import ru.hse.glassofwater.util.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class AuthService {
    @Autowired
    UserInfoRepo userInfoRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    MailService mailService;


    /**
     * @param email Адрес почты пользователя.
     * @return "success" - если сообщение с кодом удолось отправить на данный адрес.
     */
    public Status create(String email) {
        String code = Integer.toString(new Random().nextInt(9000) + 1000);
        UserInfo tmp = new UserInfo();

        tmp.setEmail(email);
        tmp.setCode(code);

        if (mailService.sendMessage(email, code)) {
            UserInfo currentUserInfo = userInfoRepo.getByEmail(email);
            if (currentUserInfo != null) {
                userInfoRepo.delete(currentUserInfo);
            }
            userInfoRepo.save(tmp);
            return new Status("success");
        } else {
            return new Status("failed");
        }
    }

    public Pair<AuthStatus, User> confirm(UserInfo userInfo) {
        UserInfo currentUserInfo = userInfoRepo.getByEmail(userInfo.getEmail());

        if (currentUserInfo == null) {
            return new Pair<>(AuthStatus.failed, null);
        }

        if (currentUserInfo.getCode().equals(userInfo.getCode())) {
            User user = userRepo.findByEmail(userInfo.getEmail());

            if (user == null) {
                user = new User();
                user.setEmail(userInfo.getEmail());
                user.setRate(0);
                user.setUsername(user.getEmail().split("@")[0]);
                user.setTrips(new ArrayList<>());
                userRepo.save(user);
            }

            userInfoRepo.delete(currentUserInfo);

            return new Pair<>(AuthStatus.success, user);
        } else {
            return new Pair<>(AuthStatus.failed, null);
        }
    }

    public List<User> getUsers() {
        return userRepo.findAll();
    }

    public List<UserInfo> getUsersInfo() {
        return userInfoRepo.findAll();
    }

    public void delete(UserInfo userInfo) {
        userInfoRepo.delete(userInfo);
    }
}
