package com.mpx90.training_app.repositories;

import com.mpx90.training_app.models.training.TrainingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingRepository extends JpaRepository<TrainingEntity, Long> {
}
