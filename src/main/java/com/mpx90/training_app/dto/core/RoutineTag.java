package com.mpx90.training_app.dto.core;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoutineTag {
    Long id;
    Long routineId;        // Referencia al RoutineEntity por ID
    Long tagId;            // Referencia al TagEntity por ID
}
