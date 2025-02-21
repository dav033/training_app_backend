package com.mpx90.training_app.dto.core;

import com.mpx90.training_app.enums.RoundExerciseRepetitionsTime;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoundExercise {
    Long id;
    Long roundId;        // Referencia al RoundEntity por ID
    Long exerciseId;     // Referencia al ExerciseEntity por ID
    String repetitions;
    Integer time;
    RoundExerciseRepetitionsTime roundExerciseType;
    Integer exercisePosition;
    LocalDateTime createdAt;
}
