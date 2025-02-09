package com.mpx90.training_app.services.crud;


import com.mpx90.training_app.dto.core.RoundExercise;
import com.mpx90.training_app.mappers.RoundExerciseMapper;
import com.mpx90.training_app.models.training.RoundExerciseEntity;
import com.mpx90.training_app.repositories.RoundExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoundExerciseService {
    private final RoundExerciseRepository roundExerciseRepository;
    private final RoundExerciseMapper roundExerciseMapper;

    public RoundExerciseService(RoundExerciseRepository roundExerciseRepository, RoundExerciseMapper roundExerciseMapper) {
        this.roundExerciseRepository = roundExerciseRepository;
        this.roundExerciseMapper = roundExerciseMapper;
    }

    public RoundExercise create(RoundExercise roundExercise) {
        RoundExerciseEntity entity = roundExerciseMapper.toEntity(roundExercise);
        return roundExerciseMapper.toDto(roundExerciseRepository.save(entity));
    }

    public List<RoundExercise> findAll() {
        return roundExerciseRepository.findAll().stream()
                .map(roundExerciseMapper::toDto)
                .collect(Collectors.toList());
    }

    public RoundExercise findById(Long id) {
        return roundExerciseRepository.findById(id)
                .map(roundExerciseMapper::toDto)
                .orElse(null);
    }

    public RoundExercise update(Long id, RoundExercise roundExercise) {
        return roundExerciseRepository.findById(id)
                .map(existing -> {
                    RoundExerciseEntity updatedEntity = roundExerciseMapper.toEntity(roundExercise);
                    updatedEntity.setId(existing.getId());
                    return roundExerciseMapper.toDto(roundExerciseRepository.save(updatedEntity));
                })
                .orElse(null);
    }

    public void delete(Long id) {
        roundExerciseRepository.deleteById(id);
    }
}