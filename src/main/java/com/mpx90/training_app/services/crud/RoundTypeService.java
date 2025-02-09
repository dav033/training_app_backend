package com.mpx90.training_app.services.crud;


import com.mpx90.training_app.dto.core.RoundType;
import com.mpx90.training_app.mappers.RoundTypeMapper;
import com.mpx90.training_app.models.training.RoundTypeEntity;
import com.mpx90.training_app.repositories.RoundTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoundTypeService {
    private final RoundTypeRepository roundTypeRepository;
    private final RoundTypeMapper roundTypeMapper;

    public RoundTypeService(RoundTypeRepository roundTypeRepository, RoundTypeMapper roundTypeMapper) {
        this.roundTypeRepository = roundTypeRepository;
        this.roundTypeMapper = roundTypeMapper;
    }

    public RoundType create(RoundType roundType) {
        RoundTypeEntity entity = roundTypeMapper.toEntity(roundType);
        return roundTypeMapper.toDto(roundTypeRepository.save(entity));
    }

    public List<RoundType> findAll() {
        return roundTypeRepository.findAll().stream()
                .map(roundTypeMapper::toDto)
                .collect(Collectors.toList());
    }

    public RoundType findById(Long id) {
        return roundTypeRepository.findById(id)
                .map(roundTypeMapper::toDto)
                .orElse(null);
    }

    public RoundType update(Long id, RoundType roundType) {
        return roundTypeRepository.findById(id)
                .map(existing -> {
                    RoundTypeEntity updatedEntity = roundTypeMapper.toEntity(roundType);
                    updatedEntity.setId(existing.getId());
                    return roundTypeMapper.toDto(roundTypeRepository.save(updatedEntity));
                })
                .orElse(null);
    }

    public void delete(Long id) {
        roundTypeRepository.deleteById(id);
    }
}
