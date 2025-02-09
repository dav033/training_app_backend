package com.mpx90.training_app.mappers;

import com.mpx90.training_app.dto.core.Round;
import com.mpx90.training_app.models.training.RoundEntity;
import com.mpx90.training_app.models.training.RoundExerciseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RoundMapper {

    RoundMapper INSTANCE = Mappers.getMapper(RoundMapper.class);

    // Mapeo de Entity a DTO:
    // Se mapea routine.id y, en lugar de roundType, se ignora o se deja null
    // Además se transforma la colección de RoundExerciseEntity a una lista de IDs.
    @Mapping(source = "routine.id", target = "routineId")
    @Mapping(target = "roundTypeId", ignore = true)
    @Mapping(source = "roundExercises", target = "roundExerciseIds", qualifiedByName = "mapRoundExercisesToIds")
    Round toDto(RoundEntity entity);

    // Mapeo de DTO a Entity:
    // Se asigna routineId a routine.id y se ignora roundType para permitir
    // la creación de un Round sin relación con RoundType.
    @Mapping(source = "routineId", target = "routine.id")
    @Mapping(target = "roundType", ignore = true)
    @Mapping(target = "roundExercises", ignore = true)
    RoundEntity toEntity(Round dto);

    // Método auxiliar para transformar el Set de RoundExerciseEntity en una lista de IDs (Long)
    @Named("mapRoundExercisesToIds")
    default List<Long> mapRoundExercisesToIds(Set<RoundExerciseEntity> roundExercises) {
        if (roundExercises == null) {
            return null;
        }
        return roundExercises.stream()
                .map(RoundExerciseEntity::getId)
                .collect(Collectors.toList());
    }
}
