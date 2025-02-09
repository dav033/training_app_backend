package com.mpx90.training_app.repositories;

import com.mpx90.training_app.models.payment.UserAccessEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccessRepository extends JpaRepository<UserAccessEntity, Long> {
}
