package ru.hse.glassofwater.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "trips_table")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private Integer rate;
    private Integer countOfGlasses;
    private String time;
    private Integer averageSpeed;
    private String startTime;

    @OneToMany(cascade = CascadeType.ALL)
    private List<LatModel> latlen;
}