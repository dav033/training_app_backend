package com.mpx90.training_app.services.admin;

import com.mpx90.training_app.dto.core.Routine;
import com.mpx90.training_app.mappers.RoutineMapper;
import com.mpx90.training_app.models.training.RoutineEntity;
import com.mpx90.training_app.repositories.RoutineRepository;
import com.mpx90.training_app.services.base.BaseService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class RoutineAdminService extends BaseService<Routine, Long, RoutineEntity, RoutineRepository> {
    public RoutineAdminService(RoutineRepository repository, @Qualifier("routineMapperImpl") RoutineMapper mapper) {
        super(repository, mapper);
    }
}
