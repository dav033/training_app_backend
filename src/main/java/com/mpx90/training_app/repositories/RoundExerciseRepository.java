package com.mpx90.training_app.repositories;

import com.mpx90.training_app.models.training.RoundExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoundExerciseRepository extends JpaRepository<RoundExerciseEntity, Long> {
}
