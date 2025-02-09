package com.mpx90.training_app.dto.core;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Round {
    private Long id;
    private Long routineId;        // Referencia al RoutineEntity por ID
    private Long roundTypeId;      // Referencia al RoundTypeEntity por ID
    private Integer rest;
    private LocalDateTime createdAt;
    // Ahora se expone solo una lista de IDs de RoundExercise en lugar de los objetos completos.
    private List<Long> roundExerciseIds;
}