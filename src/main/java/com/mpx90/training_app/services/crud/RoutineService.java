package com.mpx90.training_app.services.crud;

import com.mpx90.training_app.dto.core.Routine;
import com.mpx90.training_app.mappers.RoutineMapper;
import com.mpx90.training_app.models.training.RoutineEntity;
import com.mpx90.training_app.repositories.RoutineRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoutineService {
    private final RoutineRepository routineRepository;
    private final RoutineMapper routineMapper;

    public RoutineService(RoutineRepository routineRepository, RoutineMapper routineMapper) {
        this.routineRepository = routineRepository;
        this.routineMapper = routineMapper;
    }

    public Routine create(Routine routine) {
        RoutineEntity entity = routineMapper.toEntity(routine);
        return routineMapper.toDto(routineRepository.save(entity));
    }

    public List<Routine> findAll() {
        return routineRepository.findAll().stream()
                .map(routineMapper::toDto)
                .collect(Collectors.toList());
    }

    public Routine findById(Long id) {
        return routineRepository.findById(id)
                .map(routineMapper::toDto)
                .orElse(null);
    }

    public Routine update(Long id, Routine routine) {
        return routineRepository.findById(id)
                .map(existing -> {
                    RoutineEntity updatedEntity = routineMapper.toEntity(routine);
                    updatedEntity.setId(existing.getId());
                    return routineMapper.toDto(routineRepository.save(updatedEntity));
                })
                .orElse(null);
    }

    public void delete(Long id) {
        routineRepository.deleteById(id);
    }
}

