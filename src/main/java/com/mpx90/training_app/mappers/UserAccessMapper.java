package com.mpx90.training_app.mappers;

import com.mpx90.training_app.models.payment.UserAccessEntity;
import com.mpx90.training_app.dto.core.UserAccess;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserAccessMapper {

    UserAccessMapper INSTANCE = Mappers.getMapper(UserAccessMapper.class);

    // Mapeo de Entity a DTO
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "training.id", target = "trainingId")
    @Mapping(source = "routine.id", target = "routineId")
    UserAccess toDto(UserAccessEntity entity);

    // Mapeo de DTO a Entity
    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "trainingId", target = "training.id")
    @Mapping(source = "routineId", target = "routine.id")
    UserAccessEntity toEntity(UserAccess dto);
}
