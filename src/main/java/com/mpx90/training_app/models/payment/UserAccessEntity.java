package com.mpx90.training_app.models.payment;


import com.mpx90.training_app.enums.AccessType;
import com.mpx90.training_app.models.UserEntity;
import com.mpx90.training_app.models.training.RoutineEntity;
import com.mpx90.training_app.models.training.TrainingEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_access")
public class UserAccessEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "training_id")
    private TrainingEntity training;

    @ManyToOne
    @JoinColumn(name = "routine_id")
    private RoutineEntity routine;

    @Enumerated(EnumType.STRING)
    @Column(name = "access_type", nullable = false)
    private AccessType accessType;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate = LocalDateTime.now();

    @Column(name = "end_date")
    private LocalDateTime endDate;
}

