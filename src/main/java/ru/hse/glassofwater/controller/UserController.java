package ru.hse.glassofwater.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.hse.glassofwater.dto.TripDto;
import ru.hse.glassofwater.model.LatModel;
import ru.hse.glassofwater.model.Trip;
import ru.hse.glassofwater.model.User;
import ru.hse.glassofwater.repository.LatlenRepo;
import ru.hse.glassofwater.repository.TripRepo;
import ru.hse.glassofwater.repository.UserRepo;
import ru.hse.glassofwater.util.TripMapper;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserRepo userRepo;
    private final TripRepo tripRepo;
    private final LatlenRepo latlenRepo;

    @Autowired
    public UserController(UserRepo userRepo, TripRepo tripRepo, LatlenRepo latlenRepo) {
        this.userRepo = userRepo;
        this.tripRepo = tripRepo;
        this.latlenRepo = latlenRepo;
    }

    @GetMapping("{id}")
    public User getUser(@PathVariable("id") User user) {
        return user;
    }

    @GetMapping("{id}/trips")
    public List<TripDto> getTrips(@PathVariable("id") User user) {
        List<TripDto> result = new ArrayList<>();
        List<Trip> trips = user.getTrips();

        for (Trip trip : trips) {
            result.add(TripMapper.mapTripDto(trip));
        }

        return result;
    }

    @GetMapping("{id}/trips/{tripId}")
    public TripDto getTrip(@PathVariable("id") User user,
                        @PathVariable("tripId") Trip trip) {
        return TripMapper.mapTripDto(trip);
    }

    @PostMapping("{id}/trips")
    public void createTrip(@PathVariable("id") User user, @RequestBody TripDto tripDto) {
        Trip trip = TripMapper.mapTrip(tripDto);
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
