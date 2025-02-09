package com.mpx90.training_app.repositories;

import com.mpx90.training_app.models.training.RoundTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoundTypeExercise extends JpaRepository<RoundTypeEntity, Long> {
}
