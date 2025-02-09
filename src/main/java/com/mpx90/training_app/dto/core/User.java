package com.mpx90.training_app.dto.core;

import com.mpx90.training_app.enums.Role;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Long id;
    private Role role;
    private String email;
    private UUID uuid;
    private LocalDateTime createdAt;
    private List<Long> paymentIds;        // Referencia a PaymentEntity por IDs
    private List<Long> subscriptionIds;   // Referencia a SubscriptionEntity por IDs
    private List<Long> userAccessIds;     // Referencia a UserAccessEntity por IDs
}
