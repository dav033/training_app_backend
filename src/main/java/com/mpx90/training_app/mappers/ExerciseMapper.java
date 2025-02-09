package com.mpx90.training_app.mappers;

import com.mpx90.training_app.dto.core.Exercise;
import com.mpx90.training_app.models.training.ExerciseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ExerciseMapper {

    ExerciseMapper INSTANCE = Mappers.getMapper(ExerciseMapper.class);

    Exercise toDto(ExerciseEntity entity);

    ExerciseEntity toEntity(Exercise dto);
}