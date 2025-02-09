package com.mpx90.training_app.mappers;

import com.mpx90.training_app.models.training.RoutineTagEntity;
import com.mpx90.training_app.dto.core.RoutineTag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoutineTagMapper {

    RoutineTagMapper INSTANCE = Mappers.getMapper(RoutineTagMapper.class);

    // Mapeo de Entity a DTO
    @Mapping(source = "routine.id", target = "routineId")
    @Mapping(source = "tag.id", target = "tagId")
    RoutineTag toDto(RoutineTagEntity entity);

    // Mapeo de DTO a Entity
    @Mapping(source = "routineId", target = "routine.id")
    @Mapping(source = "tagId", target = "tag.id")
    RoutineTagEntity toEntity(RoutineTag dto);
}
