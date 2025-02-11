package com.mpx90.training_app.repositories;

import com.mpx90.training_app.models.training.ExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<ExerciseEntity, Long> {
    Boolean existsByName(String name);
    List<ExerciseEntity> findAllByIdIn(List<Long> ids);}
