package com.mpx90.training_app.dto;
import com.mpx90.training_app.dto.core.Round;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoundData {
    private Round round;
    private List<RoundExerciseData> roundExerciseData;
}
