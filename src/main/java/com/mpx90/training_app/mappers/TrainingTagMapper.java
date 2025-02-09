package com.mpx90.training_app.mappers;

import com.mpx90.training_app.models.training.TrainingTagEntity;
import com.mpx90.training_app.dto.core.TrainingTag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TrainingTagMapper {

    TrainingTagMapper INSTANCE = Mappers.getMapper(TrainingTagMapper.class);

    // Mapeo de Entity a DTO
    @Mapping(source = "training.id", target = "trainingId")
    @Mapping(source = "tag.id", target = "tagId")
    TrainingTag toDto(TrainingTagEntity entity);

    // Mapeo de DTO a Entity
    @Mapping(source = "trainingId", target = "training.id")
    @Mapping(source = "tagId", target = "tag.id")
    TrainingTagEntity toEntity(TrainingTag dto);
}
