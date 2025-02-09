package com.mpx90.training_app.dto.core;

import com.mpx90.training_app.enums.PaymentMethod;
import com.mpx90.training_app.enums.PaymentStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
    private Long id;
    private Long userId;              // Referencia al UserEntity por ID
    private BigDecimal amount;
    private PaymentStatus status;
    private PaymentMethod method;
    private String methodDetails;     // Información en formato JSON
    private LocalDateTime createdAt;
}
