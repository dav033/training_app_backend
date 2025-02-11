package com.mpx90.training_app.mappers;

import com.mpx90.training_app.dto.core.Round;
import com.mpx90.training_app.models.training.RoundEntity;
import com.mpx90.training_app.models.training.RoundTypeEntity;
import com.mpx90.training_app.models.training.RoutineEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface RoundMapper extends GenericMapper<Round, RoundEntity> {

    RoundMapper INSTANCE = Mappers.getMapper(RoundMapper.class);

    // Mapeo de Entity a DTO
    @Mapping(source = "routine.id", target = "routineId")
    @Mapping(source = "roundType.id", target = "roundTypeId")
    @Mapping(source = "roundPosition", target = "roundPosition")
    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "mapCreatedAt")
    Round toDto(RoundEntity entity);

    // Mapeo de DTO a Entity
    @Mapping(source = "routineId", target = "routine", qualifiedByName = "mapRoutineIdToEntity")
    @Mapping(source = "roundTypeId", target = "roundType", qualifiedByName = "mapRoundTypeIdToEntity")
    @Mapping(source = "roundPosition", target = "roundPosition")
    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "mapCreatedAt")
    RoundEntity toEntity(Round dto);

    // Método auxiliar para mapear `routineId` a `RoutineEntity`
    @Named("mapRoutineIdToEntity")
    default RoutineEntity mapRoutineIdToEntity(Long routineId) {
        return (routineId == null) ? null : RoutineEntity.builder().id(routineId).build();
    }

    // Método auxiliar para mapear `roundTypeId` a `RoundTypeEntity`
    @Named("mapRoundTypeIdToEntity")
    default RoundTypeEntity mapRoundTypeIdToEntity(Long roundTypeId) {
        return (roundTypeId == null) ? null : RoundTypeEntity.builder().id(roundTypeId).build();
    }

    // Método auxiliar para evitar sobrescribir `createdAt`
    @Named("mapCreatedAt")
    default LocalDateTime mapCreatedAt(LocalDateTime createdAt) {
        return (createdAt != null) ? createdAt : LocalDateTime.now();
    }
}
