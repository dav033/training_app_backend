package com.mpx90.training_app.dto.core;

import lombok.*;

import java.time.LocalDateTime;

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
    private Integer roundPosition;
    private LocalDateTime createdAt;
}