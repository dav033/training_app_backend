package com.mpx90.training_app.dto.requests;

import com.mpx90.training_app.dto.core.RoundExercise;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateRoundExerciseListRequest {
    List<RoundExercise> roundExercises;
}
