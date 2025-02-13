package com.mpx90.training_app.mappers;

import com.mpx90.training_app.dto.core.Routine;
import com.mpx90.training_app.models.training.RoutineEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface RoutineMapper extends GenericMapper<Routine, RoutineEntity> {

    RoutineMapper INSTANCE = Mappers.getMapper(RoutineMapper.class);

    @Mapping(source = "training.id", target = "trainingId")
    @Mapping(source = "price", target = "price", qualifiedByName = "bigDecimalToDouble")
    Routine toDto(RoutineEntity entity);

    @Mapping(target = "training", ignore = true)
    @Mapping(source = "price", target = "price", qualifiedByName = "doubleToBigDecimal")
    RoutineEntity toEntity(Routine dto);

    // Actualiza la entidad existente:
    // - Se ignoran los valores null en el DTO para que no se sobrescriban campos existentes.
    // - Se ignoran el id y la relación training para evitar modificaciones indeseadas.
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "training", ignore = true)
    @Mapping(source = "price", target = "price", qualifiedByName = "doubleToBigDecimal")
    void updateEntity(Routine dto, @MappingTarget RoutineEntity entity);

    @Named("bigDecimalToDouble")
    default Double bigDecimalToDouble(BigDecimal price) {
        return price != null ? price.doubleValue() : null;
    }

    @Named("doubleToBigDecimal")
    default BigDecimal doubleToBigDecimal(Double price) {
        return price != null ? BigDecimal.valueOf(price) : null;
    }
}
