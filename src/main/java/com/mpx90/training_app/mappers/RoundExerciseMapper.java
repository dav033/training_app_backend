package com.mpx90.training_app.mappers;

import com.mpx90.training_app.dto.core.RoundExercise;
import com.mpx90.training_app.models.training.RoundExerciseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoundExerciseMapper {
    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    // Mapeo de Entity a DTO
    @Mapping(source = "round.id", target = "roundId")
    @Mapping(source = "exercise.id", target = "exerciseId")
    RoundExercise toDto(RoundExerciseEntity entity);

    // Mapeo de DTO a Entity

    @Mapping(source = "roundId", target = "round.id")
    @Mapping(source = "exerciseId", target = "exercise.id")
    RoundExerciseEntity toEntity(RoundExercise dto);

}
