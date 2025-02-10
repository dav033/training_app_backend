package com.mpx90.training_app.mappers;

import com.mpx90.training_app.models.training.RoundTypeEntity;
import com.mpx90.training_app.dto.core.RoundType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface RoundTypeMapper extends GenericMapper<RoundType, RoundTypeEntity> {

    RoundTypeMapper INSTANCE = Mappers.getMapper(RoundTypeMapper.class);

    // Mapeo de Entity a DTO con manejo de fechas
    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "mapCreatedAt")
    RoundType toDto(RoundTypeEntity entity);

    // Mapeo de DTO a Entity asegurando que createdAt no sea nulo
    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "mapCreatedAt")
    RoundTypeEntity toEntity(RoundType dto);

    @Named("mapCreatedAt")
    default LocalDateTime mapCreatedAt(LocalDateTime createdAt) {
        return createdAt != null ? createdAt : LocalDateTime.now();
    }
}
