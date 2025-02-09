package com.mpx90.training_app.repositories;

import com.mpx90.training_app.models.training.RoutineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoutineRepository extends JpaRepository<RoutineEntity, Long> {
}
