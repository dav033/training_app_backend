package com.mpx90.training_app.mappers;

import com.mpx90.training_app.models.training.RoutineEntity;
import com.mpx90.training_app.models.training.TrainingEntity;
import com.mpx90.training_app.dto.core.Training;
import com.mpx90.training_app.models.training.TrainingTagEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

import com.mpx90.training_app.models.training.RoutineEntity;
import com.mpx90.training_app.models.training.TrainingEntity;
import com.mpx90.training_app.dto.core.Training;
import com.mpx90.training_app.models.training.TrainingTagEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TrainingMapper {

    TrainingMapper INSTANCE = Mappers.getMapper(TrainingMapper.class);

    // Mapeo de Entity a DTO
    @Mapping(source = "routines", target = "routineIds", qualifiedByName = "mapRoutineIds")
    @Mapping(source = "trainingTags", target = "trainingTagIds", qualifiedByName = "mapTrainingTagIds")
    Training toDto(TrainingEntity entity);

    // Mapeo de DTO a Entity
    @Mapping(target = "routines", ignore = true)       // Se ignora el mapeo directo, normalmente lo manejarías con lógica de servicio
    @Mapping(target = "trainingTags", ignore = true)   // Igual aquí, para evitar problemas de referencias circulares
    TrainingEntity toEntity(Training dto);

    // Métodos auxiliares para extraer solo los IDs de las relaciones
    @Named("mapRoutineIds")
    default List<Long> mapRoutineIds(Set<RoutineEntity> routines) {
        return routines != null
                ? routines.stream().map(RoutineEntity::getId).collect(Collectors.toList())
                : null;
    }

    @Named("mapTrainingTagIds")
    default List<Long> mapTrainingTagIds(Set<TrainingTagEntity> trainingTags) {
        return trainingTags != null
                ? trainingTags.stream().map(TrainingTagEntity::getId).collect(Collectors.toList())
                : null;
    }
}

