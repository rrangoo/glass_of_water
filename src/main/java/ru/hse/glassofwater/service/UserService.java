package ru.hse.glassofwater.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse.glassofwater.model.User;
import ru.hse.glassofwater.repository.UserRepo;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    public User getUserById(Long id) {
        try {
            return getUser(id);
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public User saveNewUser(User user) {
        return userRepo.save(user);
    }

    public User updateUser(Long id, User user) {
        User oldUser;
        try {
            oldUser = getUser(id);

            oldUser.setRate(Optional.ofNullable(user.getRate()).orElseGet(oldUser::getRate));
            oldUser.setLevel(Optional.ofNullable(user.getLevel()).orElseGet(oldUser::getLevel));
            oldUser.setEmail(Optional.ofNullable(user.getEmail()).orElseGet(oldUser::getEmail));

            try {
                getUserByUsername(user.getUsername());
                throw new IllegalArgumentException();
            } catch (NoSuchElementException ignored){}

            oldUser.setUsername(Optional.ofNullable(user.getUsername()).orElseGet(oldUser::getUsername));
            oldUser.setTrips(Optional.ofNullable(user.getTrips()).orElseGet(oldUser::getTrips));

            return userRepo.save(oldUser);
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public void deleteUser(Long id) {
        try {
            User user = getUser(id);
            userRepo.delete(user);
        } catch (NoSuchElementException ignore) {}
    }

    public User getUserByUsername(String username) throws NoSuchElementException{
        return userRepo.findByUsername(username).orElseThrow();
    }

    private User getUser(Long id) throws NoSuchElementException {
        return userRepo.findById(id).orElseThrow();
    }
}
