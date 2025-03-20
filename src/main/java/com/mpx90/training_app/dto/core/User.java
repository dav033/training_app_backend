package com.mpx90.training_app.dto.core;

import com.mpx90.training_app.enums.Role;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Long id;
    private Role role;
    private String name;
    private String email;
    private UUID uuid;
    private LocalDateTime createdAt;
}
