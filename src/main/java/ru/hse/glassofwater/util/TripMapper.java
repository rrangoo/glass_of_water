package ru.hse.glassofwater.util;

import ru.hse.glassofwater.dto.TripDto;
import ru.hse.glassofwater.model.LatModel;
import ru.hse.glassofwater.model.Trip;

import java.util.ArrayList;
import java.util.List;

public class TripMapper {
    public static Trip mapTrip(TripDto tripDto){
        Trip trip = new Trip();

        trip.setRate(tripDto.getRate());
        trip.setTime(tripDto.getTime());
        trip.setAverageSpeed(tripDto.getAverageSpeed());
        trip.setCountOfGlasses(tripDto.getCountOfGlasses());
        trip.setStartTime(tripDto.getStartTime());

        List<LatModel> newLatModelList = new ArrayList<>();
        for (List<Double> lat : tripDto.getLatlen()) {
            LatModel model = new LatModel();
            model.setLatitude(lat.get(0));
            model.setLongitude(lat.get(1));
            newLatModelList.add(model);
        }

        trip.setLatlen(newLatModelList);

        return trip;
    }

    public static TripDto mapTripDto(Trip trip){
        TripDto tripDto = new TripDto();

        tripDto.setRate(trip.getRate());
        tripDto.setTime(trip.getTime());
        tripDto.setAverageSpeed(trip.getAverageSpeed());
        tripDto.setCountOfGlasses(trip.getCountOfGlasses());
        tripDto.setStartTime(trip.getStartTime());

        List<List<Double>> newList = new ArrayList<>();
        for (LatModel lat : trip.getLatlen()) {
            List<Double> list = new ArrayList<>();
            list.add(lat.getLatitude());
            list.add(lat.getLongitude());
            newList.add(list);
        }

        tripDto.setLatlen(newList);

        return tripDto;
    }
}
