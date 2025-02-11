package com.mpx90.training_app.repositories;

import com.mpx90.training_app.models.training.RoundExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface RoundExerciseRepository extends JpaRepository<RoundExerciseEntity, Long> {
    List<RoundExerciseEntity> findAllByRoundId(Long roundId);

    List<RoundExerciseEntity> findAllByRoundIdIn(Collection<Long> roundIds);
}
