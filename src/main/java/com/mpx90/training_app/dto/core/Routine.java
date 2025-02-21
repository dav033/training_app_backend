package com.mpx90.training_app.dto.core;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Routine {
    Long id;
    Long trainingId;
    String name;
    String description;
    Double price;
    LocalDateTime createdAt;
    Boolean isPublic;
}
