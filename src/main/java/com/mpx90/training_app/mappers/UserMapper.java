package com.mpx90.training_app.mappers;

import com.mpx90.training_app.models.UserEntity;
import com.mpx90.training_app.dto.core.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UserMapper extends GenericMapper<User, UserEntity> {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // Mapeo de Entity a DTO
    @Mapping(source = "uuid", target = "uuid", qualifiedByName = "mapUuidToString")
    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "mapCreatedAt")
    User toDto(UserEntity entity);

    // Mapeo de DTO a Entity con manejo seguro de UUID y fechas
    @Mapping(source = "uuid", target = "uuid", qualifiedByName = "mapStringToUuid")
    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "mapCreatedAt")
    UserEntity toEntity(User dto);

    @Named("mapUuidToString")
    default String mapUuidToString(UUID uuid) {
        return (uuid != null) ? uuid.toString() : null;
    }

    @Named("mapStringToUuid")
    default UUID mapStringToUuid(String uuid) {
        return (uuid != null && !uuid.isEmpty()) ? UUID.fromString(uuid) : UUID.randomUUID();
    }

    @Named("mapCreatedAt")
    default LocalDateTime mapCreatedAt(LocalDateTime createdAt) {
        return (createdAt != null) ? createdAt : LocalDateTime.now();
    }
}
