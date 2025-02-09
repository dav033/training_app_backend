package com.mpx90.training_app.dto.core;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainingTag {
    private Long id;
    private Long trainingId;  // Referencia al TrainingEntity por ID
    private Long tagId;       // Referencia al TagEntity por ID
}
