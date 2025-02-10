package com.mpx90.training_app.mappers;

import com.mpx90.training_app.dto.core.RoundExercise;
import com.mpx90.training_app.models.training.RoundExerciseEntity;
import com.mpx90.training_app.models.training.RoundEntity;
import com.mpx90.training_app.models.training.ExerciseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoundExerciseMapper extends GenericMapper<RoundExercise, RoundExerciseEntity> {
    RoundExerciseMapper INSTANCE = Mappers.getMapper(RoundExerciseMapper.class);

    // Mapeo de Entity a DTO
    @Mapping(source = "round.id", target = "roundId")
    @Mapping(source = "exercise.id", target = "exerciseId")
    RoundExercise toDto(RoundExerciseEntity entity);

    // Mapeo de DTO a Entity con métodos auxiliares para evitar referencias directas
    @Mapping(source = "roundId", target = "round", qualifiedByName = "mapRoundIdToEntity")
    @Mapping(source = "exerciseId", target = "exercise", qualifiedByName = "mapExerciseIdToEntity")
    RoundExerciseEntity toEntity(RoundExercise dto);

    @Named("mapRoundIdToEntity")
    default RoundEntity mapRoundIdToEntity(Long roundId) {
        if (roundId == null) {
            return null;
        }
        RoundEntity round = new RoundEntity();
        round.setId(roundId);
        return round;
    }

    @Named("mapExerciseIdToEntity")
    default ExerciseEntity mapExerciseIdToEntity(Long exerciseId) {
        if (exerciseId == null) {
            return null;
        }
        ExerciseEntity exercise = new ExerciseEntity();
        exercise.setId(exerciseId);
        return exercise;
    }
}
