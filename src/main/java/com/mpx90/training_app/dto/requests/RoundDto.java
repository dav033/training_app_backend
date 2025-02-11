package com.mpx90.training_app.dto.requests;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoundDto {
    private Integer rest; // Tiempo de descanso
    private List<RoundExerciseDto> exercises; // Lista de ejercicios en la ronda
    private Integer roundPosition; // Posición de la ronda en la rutina
}