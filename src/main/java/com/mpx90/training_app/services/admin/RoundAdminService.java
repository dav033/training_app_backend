package com.mpx90.training_app.services.admin;

import com.mpx90.training_app.dto.core.Round;
import com.mpx90.training_app.mappers.RoundMapper;
import com.mpx90.training_app.models.training.RoundEntity;
import com.mpx90.training_app.repositories.RoundRepository;
import com.mpx90.training_app.services.base.BaseService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class RoundAdminService extends BaseService<Round, Long, RoundEntity, RoundRepository> {
    public RoundAdminService(RoundRepository repository, @Qualifier("roundMapperImpl") RoundMapper mapper) {
        super(repository, mapper);
    }
}
