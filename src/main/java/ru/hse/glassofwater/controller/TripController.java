package ru.hse.glassofwater.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.hse.glassofwater.dto.TripDto;

import java.util.List;

public interface TripController {
    @GetMapping("{id}")
    List<TripDto> getTrips(@PathVariable("id") Long id);

    @PostMapping("{id}")
    void createTrip(@PathVariable("id") Long id, @RequestBody TripDto tripDto);

    @GetMapping("{id}/{tripId}")
    TripDto getTrip(@PathVariable("id") Long userId, @PathVariable("tripId") Long tripId);
}
