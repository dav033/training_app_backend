package com.mpx90.training_app.dto.requests;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoundExerciseDto {
    private Long exerciseId; // ID del ejercicio
    private String repetitions; // Cantidad de repeticiones
    private Integer exercisePosition; // Posición del ejercicio en la ronda
}
