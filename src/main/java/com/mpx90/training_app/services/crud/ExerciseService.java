package com.mpx90.training_app.services.crud;

import com.mpx90.training_app.dto.core.Exercise;
import com.mpx90.training_app.exception.DuplicateEntryException;
import com.mpx90.training_app.mappers.ExerciseMapper;
import com.mpx90.training_app.models.training.ExerciseEntity;
import com.mpx90.training_app.repositories.ExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final ExerciseMapper exerciseMapper;

    public ExerciseService(ExerciseRepository exerciseRepository, ExerciseMapper exerciseMapper) {
        this.exerciseRepository = exerciseRepository;
        this.exerciseMapper = exerciseMapper;
    }

    public Exercise create(Exercise exercise) {
        ExerciseEntity entity = exerciseMapper.toEntity(exercise);

        if(exerciseRepository.existsByName(entity.getName())) {
            throw new DuplicateEntryException("Exercise with name '" + exercise.getName() + "' already exists.");
        }

        return exerciseMapper.toDto(exerciseRepository.save(entity));
    }

    public List<Exercise> findAll() {
        return exerciseRepository.findAll().stream()
                .map(exerciseMapper::toDto)
                .collect(Collectors.toList());
    }

    public Exercise findById(Long id) {
        return exerciseRepository.findById(id)
                .map(exerciseMapper::toDto)
                .orElse(null);
    }

    public Exercise update(Long id, Exercise exercise) {
        return exerciseRepository.findById(id)
                .map(existing -> {
                    ExerciseEntity updatedEntity = exerciseMapper.toEntity(exercise);
                    updatedEntity.setId(existing.getId());
                    return exerciseMapper.toDto(exerciseRepository.save(updatedEntity));
                })
                .orElse(null);
    }

    public void delete(Long id) {
        exerciseRepository.deleteById(id);
    }

    public Boolean existByName(Long id) {
        return exerciseRepository.existsById(id);
    }
}