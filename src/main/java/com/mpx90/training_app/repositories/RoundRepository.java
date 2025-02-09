package com.mpx90.training_app.repositories;

import com.mpx90.training_app.models.training.RoundEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoundRepository extends JpaRepository<RoundEntity, Long> {
    List<RoundEntity> findByRoutineId(Long routineId);
}
