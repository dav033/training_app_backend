package com.mpx90.training_app.mappers;

import com.mpx90.training_app.models.training.RoutineEntity;
import com.mpx90.training_app.dto.core.Routine;
import com.mpx90.training_app.models.training.RoundEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RoutineMapper extends GenericMapper<Routine, RoutineEntity> {

    RoutineMapper INSTANCE = Mappers.getMapper(RoutineMapper.class);

    @Mapping(source = "training.id", target = "trainingId")
    @Mapping(source = "price", target = "price", qualifiedByName = "bigDecimalToDouble")
    Routine toDto(RoutineEntity entity);

    @Mapping(target = "training", ignore = true)
    @Mapping(source = "price", target = "price", qualifiedByName = "doubleToBigDecimal")
    RoutineEntity toEntity(Routine dto);

    @Named("bigDecimalToDouble")
    default Double bigDecimalToDouble(BigDecimal price) {
        return price != null ? price.doubleValue() : null;
    }

    @Named("doubleToBigDecimal")
    default BigDecimal doubleToBigDecimal(Double price) {
        return price != null ? BigDecimal.valueOf(price) : null;
    }
}
