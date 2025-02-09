package com.mpx90.training_app.mappers;

import com.mpx90.training_app.models.training.RoundTypeEntity;
import com.mpx90.training_app.dto.core.RoundType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoundTypeMapper {

    RoundTypeMapper INSTANCE = Mappers.getMapper(RoundTypeMapper.class);

    // Mapeo de Entity a DTO
    RoundType toDto(RoundTypeEntity entity);

    // Mapeo de DTO a Entity
    RoundTypeEntity toEntity(RoundType dto);
}
