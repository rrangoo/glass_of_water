package ru.hse.glassofwater.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.hse.glassofwater.model.Trip;
import ru.hse.glassofwater.model.User;
import ru.hse.glassofwater.repository.TripRepo;
import ru.hse.glassofwater.repository.UserRepo;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserRepo userRepo;
    private final TripRepo tripRepo;

    @Autowired
    public UserController(UserRepo userRepo, TripRepo tripRepo) {
        this.userRepo = userRepo;
        this.tripRepo = tripRepo;
    }

    @GetMapping("{id}")
    public User getUser(@PathVariable("id") User user) {
        return user;
    }

    @GetMapping("{id}/trips")
    public List<Trip> getTrips(@PathVariable("id") User user) {
        return user.getTrips();
    }

    @GetMapping("{id}/trips/{tripId}")
    public Trip getTrip(@PathVariable("id") User user,
                        @PathVariable("tripId") Trip trip) {
        return trip;
    }

    @PostMapping("{id}/trips")
    public void createTrip(@PathVariable("id") User user, @RequestBody Trip trip) {
        user.getTrips().add(trip);
        tripRepo.save(trip);
        userRepo.save(user);
    }

    @DeleteMapping("{id}/trips")
    public void deleteTrip(@PathVariable("id") User user) {
        List<Long> ids = new ArrayList<>();
        for (Trip trip : user.getTrips()) {
            ids.add(trip.getId());
        }
        user.getTrips().removeAll(user.getTrips());
        userRepo.save(user);

        for (Long id : ids) {
            tripRepo.deleteById(id);
        }
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return userRepo.save(user);
    }

    @PutMapping("{id}")
    public User update(
            @PathVariable("id") User userFromDb,
            @RequestBody User user
    ) {
        userFromDb.setRate(user.getRate());
        return userRepo.save(userFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") User user) {
        userRepo.delete(user);
    }
}
