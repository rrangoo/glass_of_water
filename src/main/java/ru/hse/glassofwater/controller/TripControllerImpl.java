package ru.hse.glassofwater.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hse.glassofwater.dto.TripDto;
import ru.hse.glassofwater.service.TripService;

import java.util.List;

@RestController
@RequestMapping("trips")
@RequiredArgsConstructor
public class TripControllerImpl implements TripController {
    private final TripService tripService;

    @Override
    public List<TripDto> getTrips(Long id) {
        return tripService.getUsersTrips(id);
    }

    @Override
    public TripDto getTrip(Long userId, Long tripId) {
        return tripService.getTrip(tripId);
    }

    @Override
    public void createTrip(Long id, TripDto tripDto) {
        tripService.createTrip(id, tripDto);
    }
}
