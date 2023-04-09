package ru.hse.glassofwater.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "lat_table")
public class LatModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double latitude;
    private Double longitude;
}
