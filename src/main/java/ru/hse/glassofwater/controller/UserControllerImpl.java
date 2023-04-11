package ru.hse.glassofwater.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hse.glassofwater.model.User;
import ru.hse.glassofwater.service.UserService;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {
    private final UserService userService;

    @Override
    public User getUser(Long id) {
        return userService.getUserById(id);
    }

    @Override
    public User create(User user) {
        return userService.saveNewUser(user);
    }

    @Override
    public User update(Long id, User user) {
        return userService.updateUser(id, user);
    }

    @Override
    public void delete(Long id) {
        userService.deleteUser(id);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleSameUsername(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
