package com.mpx90.training_app.services.admin;

import com.mpx90.training_app.dto.core.Exercise;
import com.mpx90.training_app.mappers.ExerciseMapper;
import com.mpx90.training_app.models.training.ExerciseEntity;
import com.mpx90.training_app.repositories.ExerciseRepository;
import com.mpx90.training_app.services.base.BaseService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseAdminService extends BaseService<Exercise, Long, ExerciseEntity, ExerciseRepository> {
    public ExerciseAdminService(ExerciseRepository repository, @Qualifier("exerciseMapperImpl") ExerciseMapper mapper) {
        super(repository, mapper);
    }


    public List<Exercise> findAllByIds(List<Long> ids) {
        return repository.findAllByIdIn(ids).stream()
            .map(mapper::toDto)
            .toList(); // Replaces collect(Collectors.toList()) with toList()
    }
}
