package com.mpx90.training_app.mappers;

import com.mpx90.training_app.dto.core.TrainingTag;
import com.mpx90.training_app.models.training.TagEntity;
import com.mpx90.training_app.models.training.TrainingEntity;
import com.mpx90.training_app.models.training.TrainingTagEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TrainingTagMapper extends GenericMapper<TrainingTag, TrainingTagEntity> {

    TrainingTagMapper INSTANCE = Mappers.getMapper(TrainingTagMapper.class);

    // Mapeo de Entity a DTO
    @Mapping(source = "training.id", target = "trainingId")
    @Mapping(source = "tag.id", target = "tagId")
    TrainingTag toDto(TrainingTagEntity entity);

    // Mapeo de DTO a Entity con métodos auxiliares para evitar referencias directas
    @Mapping(source = "trainingId", target = "training", qualifiedByName = "mapTrainingIdToEntity")
    @Mapping(source = "tagId", target = "tag", qualifiedByName = "mapTagIdToEntity")
    TrainingTagEntity toEntity(TrainingTag dto);

    @Named("mapTrainingIdToEntity")
    default TrainingEntity mapTrainingIdToEntity(Long trainingId) {
        if (trainingId == null) {
            return null;
        }
        TrainingEntity training = new TrainingEntity();
        training.setId(trainingId);
        return training;
    }

    @Named("mapTagIdToEntity")
    default TagEntity mapTagIdToEntity(Long tagId) {
        if (tagId == null) {
            return null;
        }
        TagEntity tag = new TagEntity();
        tag.setId(tagId);
        return tag;
    }
}
