package com.mpx90.training_app.repositories;

import com.mpx90.training_app.models.payment.SubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Long> {
}
