package com.mpx90.training_app.mappers;

import com.mpx90.training_app.models.training.TagEntity;
import com.mpx90.training_app.dto.core.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TagMapper {

    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);

    // Mapeo de Entity a DTO
    Tag toDto(TagEntity entity);

    // Mapeo de DTO a Entity
    TagEntity toEntity(Tag dto);
}
