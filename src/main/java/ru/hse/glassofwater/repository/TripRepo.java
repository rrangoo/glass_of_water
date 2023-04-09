package ru.hse.glassofwater.repository;

import org.springframework.data.repository.CrudRepository;
import ru.hse.glassofwater.model.Trip;

public interface TripRepo extends CrudRepository<Trip, Long> {

}
