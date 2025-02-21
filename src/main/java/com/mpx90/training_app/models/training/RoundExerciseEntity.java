package com.mpx90.training_app.models.training;

import com.mpx90.training_app.enums.RoundExerciseRepetitionsTime;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "round_exercises")
public class RoundExerciseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "round_id", nullable = false)
    private RoundEntity round;

    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    private ExerciseEntity exercise;

    @Column(nullable = true)
    private Integer time;

    @Column(nullable = true)
    private String repetitions;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoundExerciseRepetitionsTime roundExerciseType;


    @Column(nullable = false, name = "exercise_position")
    private Integer exercisePosition;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.roundExerciseType = RoundExerciseRepetitionsTime.REPS;
        this.repetitions = "10";
        this.time = 30;
    }

}
