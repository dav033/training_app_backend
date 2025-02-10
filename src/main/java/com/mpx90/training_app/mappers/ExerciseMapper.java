package com.mpx90.training_app.mappers;

import com.mpx90.training_app.dto.core.Exercise;
import com.mpx90.training_app.models.training.ExerciseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface ExerciseMapper extends GenericMapper<Exercise, ExerciseEntity> {

    ExerciseMapper INSTANCE = Mappers.getMapper(ExerciseMapper.class);

    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "mapCreatedAt")
    Exercise toDto(ExerciseEntity entity);

    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "mapCreatedAt")
    ExerciseEntity toEntity(Exercise dto);

    @Named("mapCreatedAt")
    default LocalDateTime mapCreatedAt(LocalDateTime createdAt) {
        return createdAt != null ? createdAt : LocalDateTime.now();
    }
}
