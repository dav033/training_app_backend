package com.mpx90.training_app.repositories;
import com.mpx90.training_app.models.training.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<TagEntity, Long> {
}
