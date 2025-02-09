package com.mpx90.training_app.repositories;

import com.mpx90.training_app.models.training.TrainingTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingTagRepository extends JpaRepository<TrainingTagEntity, Long> {
}
