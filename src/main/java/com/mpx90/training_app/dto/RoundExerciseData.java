package com.mpx90.training_app.dto;

import com.mpx90.training_app.dto.core.Exercise;
import com.mpx90.training_app.dto.core.RoundExercise;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoundExerciseData {
    RoundExercise roundExercise;
    Exercise exercise;
}
