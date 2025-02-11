package com.mpx90.training_app.mappers;

import com.mpx90.training_app.dto.core.Tag;
import com.mpx90.training_app.models.training.TagEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface TagMapper extends GenericMapper<Tag, TagEntity> {

    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);

    // Mapeo de Entity a DTO con manejo de fechas
    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "mapCreatedAt")
    Tag toDto(TagEntity entity);

    // Mapeo de DTO a Entity asegurando que createdAt no sea nulo
    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "mapCreatedAt")
    TagEntity toEntity(Tag dto);

    @Named("mapCreatedAt")
    default LocalDateTime mapCreatedAt(LocalDateTime createdAt) {
        return createdAt != null ? createdAt : LocalDateTime.now();
    }
}
