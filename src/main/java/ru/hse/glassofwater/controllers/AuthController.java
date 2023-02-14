package ru.hse.glassofwater.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hse.glassofwater.model.Email;
import ru.hse.glassofwater.model.User;
import ru.hse.glassofwater.model.UserInfo;
import ru.hse.glassofwater.service.AuthService;
import ru.hse.glassofwater.util.AuthStatus;
import ru.hse.glassofwater.util.Pair;
import ru.hse.glassofwater.util.Status;

import java.util.List;

@RestController
@RequestMapping("auth")
public class AuthController {

    AuthService authService;

    public AuthController(@Autowired AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("users")
    public List<User> getUsers() {
        return authService.getUsers();
    }

    @GetMapping("infos")
    public List<UserInfo> getUsersInfo() {
        return authService.getUsersInfo();
    }

    @PostMapping("/email")
    public ResponseEntity<Status> createUserInfo(@RequestBody Email email) {
        return ResponseEntity.ok(authService.create(email.getEmail()));
    }

    @PostMapping("/code")
    public ResponseEntity<Pair<AuthStatus, User>> auth(@RequestBody UserInfo userInfo) {
        Pair<AuthStatus, User> response = authService.confirm(userInfo);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") UserInfo userInfo) {
        authService.delete(userInfo);
    }
}
