package com.mpx90.training_app.mappers;

import com.mpx90.training_app.dto.core.RoutineTag;
import com.mpx90.training_app.models.training.RoutineEntity;
import com.mpx90.training_app.models.training.RoutineTagEntity;
import com.mpx90.training_app.models.training.TagEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoutineTagMapper extends GenericMapper<RoutineTag, RoutineTagEntity> {

    RoutineTagMapper INSTANCE = Mappers.getMapper(RoutineTagMapper.class);

    // Mapeo de Entity a DTO
    @Mapping(source = "routine.id", target = "routineId")
    @Mapping(source = "tag.id", target = "tagId")
    RoutineTag toDto(RoutineTagEntity entity);

    // Mapeo de DTO a Entity con métodos auxiliares para evitar referencias directas
    @Mapping(source = "routineId", target = "routine", qualifiedByName = "mapRoutineIdToEntity")
    @Mapping(source = "tagId", target = "tag", qualifiedByName = "mapTagIdToEntity")
    RoutineTagEntity toEntity(RoutineTag dto);

    @Named("mapRoutineIdToEntity")
    default RoutineEntity mapRoutineIdToEntity(Long routineId) {
        if (routineId == null) {
            return null;
        }
        RoutineEntity routine = new RoutineEntity();
        routine.setId(routineId);
        return routine;
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
