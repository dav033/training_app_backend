package com.mpx90.training_app.models.training;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "routines")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoutineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = true)
    @JoinColumn(name = "training_id", nullable = true)
    private TrainingEntity training;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal price = BigDecimal.ZERO;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name="is_public", nullable = false)
    private Boolean isPublic = false;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;


    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();

        this.isPublic = this.isPublic != null ? this.isPublic : false;

        if (this.price == null) {
            this.price = BigDecimal.ZERO;
        }
    }

    //hacer que si la columna ispublic es null se ponga a false para todos los registros

}