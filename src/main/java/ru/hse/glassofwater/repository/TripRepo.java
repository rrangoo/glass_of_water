package ru.hse.glassofwater.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hse.glassofwater.model.Trip;

public interface TripRepo extends JpaRepository<Trip, Long> {

    @Override
    void deleteById(Long id);
}
