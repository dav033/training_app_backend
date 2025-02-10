package com.mpx90.training_app.mappers;

import com.mpx90.training_app.dto.core.Round;
import com.mpx90.training_app.models.training.RoundEntity;
import com.mpx90.training_app.models.training.RoundExerciseEntity;
import com.mpx90.training_app.models.training.RoutineEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RoundMapper extends GenericMapper<Round, RoundEntity> {

    RoundMapper INSTANCE = Mappers.getMapper(RoundMapper.class);

    // Mapeo de Entity a DTO
    @Mapping(source = "routine.id", target = "routineId")
    @Mapping(source = "roundType.id", target = "roundTypeId", ignore = true)
    @Mapping(source = "roundExercises", target = "roundExerciseIds", qualifiedByName = "mapRoundExercisesToIds")
    Round toDto(RoundEntity entity);

    // Mapeo de DTO a Entity
    @Mapping(source = "routineId", target = "routine", qualifiedByName = "mapRoutineIdToEntity")
    @Mapping(target = "roundType", ignore = true)
    @Mapping(target = "roundExercises", ignore = true)
    RoundEntity toEntity(Round dto);

    // Método auxiliar para transformar el Set de RoundExerciseEntity en una lista de IDs (Long)
    @Named("mapRoundExercisesToIds")
    default List<Long> mapRoundExercisesToIds(Set<RoundExerciseEntity> roundExercises) {
        return (roundExercises != null)
                ? roundExercises.stream().map(RoundExerciseEntity::getId).collect(Collectors.toList())
                : null;
    }

    // Método auxiliar para mapear routineId a una entidad RoutineEntity
    @Named("mapRoutineIdToEntity")
    default RoutineEntity mapRoutineIdToEntity(Long routineId) {
        if (routineId == null) {
            return null;
        }
        RoutineEntity routine = new RoutineEntity();
        routine.setId(routineId);
        return routine;
    }
}
