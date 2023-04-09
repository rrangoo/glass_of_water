package ru.hse.glassofwater.dto;

import lombok.Data;

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