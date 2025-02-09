package com.mpx90.training_app.repositories;

import com.mpx90.training_app.models.payment.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
}
