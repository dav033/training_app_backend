package com.mpx90.training_app.dto.requests;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoundRequest {
    private Long routineId;  // ID de la rutina asociada
    private List<RoundDto> rounds; // Lista de rondas con sus ejercicios
}