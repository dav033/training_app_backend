package com.mpx90.training_app.dto.core;


import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Training {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private LocalDateTime createdAt;
    private List<Long> routineIds;      // Referencia a RoutineEntity por IDs
    private List<Long> trainingTagIds;  // Referencia a TrainingTags por IDs
}
