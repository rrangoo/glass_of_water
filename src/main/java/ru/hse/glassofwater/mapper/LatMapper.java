package ru.hse.glassofwater.mapper;

import org.springframework.stereotype.Component;
import ru.hse.glassofwater.model.Lat;

import java.util.ArrayList;
import java.util.List;

@Component
public class LatMapper implements Mapper<List<Double>, Lat> {
    @Override
    public List<Double> to(Lat lat) {
        List<Double> list = new ArrayList<>();
        list.add(lat.getLatitude());
        list.add(lat.getLongitude());
        return list;
    }

    @Override
    public Lat from(List<Double> doubleList) {
        return Lat.builder()
                .latitude(doubleList.get(0))
                .longitude(doubleList.get(1))
                .build();
    }
}
