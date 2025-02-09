package com.mpx90.training_app.services.crud;


import com.mpx90.training_app.dto.core.RoutineTag;
import com.mpx90.training_app.mappers.RoutineTagMapper;
import com.mpx90.training_app.models.training.RoutineTagEntity;
import com.mpx90.training_app.repositories.RoutineTagRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoutineTagService {
    private final RoutineTagRepository routineTagRepository;
    private final RoutineTagMapper routineTagMapper;

    public RoutineTagService(RoutineTagRepository routineTagRepository, RoutineTagMapper routineTagMapper) {
        this.routineTagRepository = routineTagRepository;
        this.routineTagMapper = routineTagMapper;
    }

    public RoutineTag create(RoutineTag routineTag) {
        RoutineTagEntity entity = routineTagMapper.toEntity(routineTag);
        return routineTagMapper.toDto(routineTagRepository.save(entity));
    }

    public List<RoutineTag> findAll() {
        return routineTagRepository.findAll().stream()
                .map(routineTagMapper::toDto)
                .collect(Collectors.toList());
    }

    public RoutineTag findById(Long id) {
        return routineTagRepository.findById(id)
                .map(routineTagMapper::toDto)
                .orElse(null);
    }

    public RoutineTag update(Long id, RoutineTag routineTag) {
        return routineTagRepository.findById(id)
                .map(existing -> {
                    RoutineTagEntity updatedEntity = routineTagMapper.toEntity(routineTag);
                    updatedEntity.setId(existing.getId());
                    return routineTagMapper.toDto(routineTagRepository.save(updatedEntity));
                })
                .orElse(null);
    }

    public void delete(Long id) {
        routineTagRepository.deleteById(id);
    }
}
