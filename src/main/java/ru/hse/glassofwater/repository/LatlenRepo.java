package ru.hse.glassofwater.repository;

import org.springframework.data.repository.CrudRepository;
import ru.hse.glassofwater.model.LatModel;

public interface LatlenRepo extends CrudRepository<LatModel, Long> {

}
