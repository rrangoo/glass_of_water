package ru.hse.glassofwater.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "trips_table")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Integer rate;
    private Integer countOfGlasses;
    private String time;
    private Integer averageSpeed;
    private String startTime;
}