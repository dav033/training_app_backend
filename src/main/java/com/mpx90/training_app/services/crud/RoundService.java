package com.mpx90.training_app.services.crud;

import com.mpx90.training_app.dto.core.Round;
import com.mpx90.training_app.mappers.RoundMapper;
import com.mpx90.training_app.models.training.RoundEntity;
import com.mpx90.training_app.repositories.RoundRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoundService {
    private final RoundRepository roundRepository;
    private final RoundMapper roundMapper;

    public RoundService(RoundRepository roundRepository, RoundMapper roundMapper) {
        this.roundRepository = roundRepository;
        this.roundMapper = roundMapper;
    }

    public Round create(Round round) {
        RoundEntity entity = roundMapper.toEntity(round);
        return roundMapper.toDto(roundRepository.save(entity));
    }

    public List<Round> findAll() {
        return roundRepository.findAll().stream()
                .map(roundMapper::toDto)
                .collect(Collectors.toList());
    }

    public Round findById(Long id) {
        return roundRepository.findById(id)
                .map(roundMapper::toDto)
                .orElse(null);
    }

    public Round update(Long id, Round round) {
        return roundRepository.findById(id)
                .map(existing -> {
                    RoundEntity updatedEntity = roundMapper.toEntity(round);
                    updatedEntity.setId(existing.getId());
                    return roundMapper.toDto(roundRepository.save(updatedEntity));
                })
                .orElse(null);
    }

    public void delete(Long id) {
        roundRepository.deleteById(id);
    }

    public List<Round> findByRoutineId(Long routineId) {
        return roundRepository.findByRoutineId(routineId).stream()
                .map(roundMapper::toDto)
                .collect(Collectors.toList());
    }
}