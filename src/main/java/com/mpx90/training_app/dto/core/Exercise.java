package com.mpx90.training_app.dto.core;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exercise {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private String videoUrl;
    private String thumbnailUrl;
}
