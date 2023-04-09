package ru.hse.glassofwater.dto;

import lombok.Data;
import ru.hse.glassofwater.model.LatModel;
import ru.hse.glassofwater.model.Trip;

import java.util.ArrayList;
import java.util.List;

@Data
public class TripDto {
    private Integer rate;
    private Integer countOfGlasses;
    private String time;
    private Integer averageSpeed;
    private String startTime;

    private List<List<Double>> latlen;
}
