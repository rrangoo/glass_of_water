package ru.hse.glassofwater.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse.glassofwater.dto.TripDto;
import ru.hse.glassofwater.mapper.Mapper;
import ru.hse.glassofwater.model.Trip;
import ru.hse.glassofwater.model.User;
import ru.hse.glassofwater.repository.TripRepo;
import ru.hse.glassofwater.repository.UserRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TripService {
    private final TripRepo tripRepo;
    private final UserRepo userRepo;
    private final UserService userService;
    private final Mapper<TripDto, Trip> mapper;

    public List<TripDto> getUsersTrips(Long id) {
        try {
            User user = userService.getUserById(id);
            List<TripDto> list = new ArrayList<>();

            for (Trip trip : user.getTrips()) {
                list.add(mapper.to(trip));
            }
            return list;
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public TripDto getTrip(Long tripId) {
        try {
            return mapper.to(getTripById(tripId));
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public void createTrip(Long userId, TripDto tripDto) {
        try {
            User user = getUser(userId);
            Trip trip = mapper.from(tripDto);
            user.getTrips().add(trip);
            tripRepo.save(trip);
            userRepo.save(user);
        } catch (NoSuchElementException ignored) {
        }
    }

    private User getUser(Long userId) throws NoSuchElementException {
        return Optional.ofNullable(userService.getUserById(userId)).orElseThrow();
    }

    private Trip getTripById(Long tripId) throws NoSuchElementException {
        return tripRepo.findById(tripId).orElseThrow();
    }
}
