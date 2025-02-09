package com.mpx90.training_app.dto.core;

import com.mpx90.training_app.enums.SubscriptionStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subscription {
    private Long id;
    private Long userId;          // Referencia al UserEntity por ID
    private SubscriptionStatus status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long paymentId;       // Referencia al PaymentEntity por ID
}
