package com.mpx90.training_app.dto.responses;


import com.mpx90.training_app.dto.RoundData;
import com.mpx90.training_app.dto.core.Routine;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoutineAllDataResponse {
    private Routine routine;
    private List<RoundData> roundData;
}
