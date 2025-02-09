package com.mpx90.training_app.services.crud;


import com.mpx90.training_app.dto.core.Training;
import com.mpx90.training_app.mappers.TrainingMapper;
import com.mpx90.training_app.models.training.TrainingEntity;
import com.mpx90.training_app.repositories.TrainingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainingService {
    private final TrainingRepository trainingRepository;
    private final TrainingMapper trainingMapper;

    public TrainingService(TrainingRepository trainingRepository, TrainingMapper trainingMapper) {
        this.trainingRepository = trainingRepository;
        this.trainingMapper = trainingMapper;
    }

    public Training create(Training training) {
        TrainingEntity entity = trainingMapper.toEntity(training);
        return trainingMapper.toDto(trainingRepository.save(entity));
    }

    public List<Training> findAll() {
        return trainingRepository.findAll().stream()
                .map(trainingMapper::toDto)
                .collect(Collectors.toList());
    }

    public Training findById(Long id) {
        return trainingRepository.findById(id)
                .map(trainingMapper::toDto)
                .orElse(null);
    }

    public Training update(Long id, Training training) {
        return trainingRepository.findById(id)
                .map(existing -> {
                    TrainingEntity updatedEntity = trainingMapper.toEntity(training);
                    updatedEntity.setId(existing.getId());
                    return trainingMapper.toDto(trainingRepository.save(updatedEntity));
                })
                .orElse(null);
    }

    public void delete(Long id) {
        trainingRepository.deleteById(id);
    }
}

