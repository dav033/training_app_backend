package com.mpx90.training_app.models.training;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "training_tags")
public class TrainingTagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "training_id", nullable = false)
    private TrainingEntity training;

    @ManyToOne
    @JoinColumn(name = "tag_id", nullable = false)
    private TagEntity tag;
}
