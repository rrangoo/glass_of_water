package ru.hse.glassofwater.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.hse.glassofwater.dto.TripDto;
import ru.hse.glassofwater.model.Trip;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TripMapper implements Mapper<TripDto, Trip> {
    private final LatMapper mapper;

    @Override
    public TripDto to(Trip trip) {
        TripDto tripDto = new TripDto();

        tripDto.setRate(trip.getRate());
        tripDto.setTime(trip.getTime());
        tripDto.setAverageSpeed(trip.getAverageSpeed());
        tripDto.setCountOfGlasses(trip.getCountOfGlasses());
        tripDto.setStartTime(trip.getStartTime());
        tripDto.setLatlen(trip.getLatlen().stream().map(mapper::to).collect(Collectors.toList()));

        return tripDto;
    }

    @Override
    public Trip from(TripDto tripDto) {
        return Trip.builder()
                .averageSpeed(tripDto.getAverageSpeed())
                .countOfGlasses(tripDto.getCountOfGlasses())
                .startTime(tripDto.getStartTime())
                .time(tripDto.getStartTime())
                .rate(tripDto.getRate())
                .latlen(tripDto.getLatlen().stream().map(mapper::from).collect(Collectors.toList()))
                .build();
    }
}
