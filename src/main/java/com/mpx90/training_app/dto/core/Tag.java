package com.mpx90.training_app.dto.core;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tag {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
}