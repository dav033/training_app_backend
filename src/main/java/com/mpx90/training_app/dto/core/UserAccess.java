package com.mpx90.training_app.dto.core;

import com.mpx90.training_app.enums.AccessType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAccess {
    private Long id;
    private Long userId;        // Referencia al UserEntity por ID
    private Long trainingId;    // Referencia al TrainingEntity por ID
    private Long routineId;     // Referencia al RoutineEntity por ID
    private AccessType accessType;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
