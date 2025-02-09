package com.mpx90.training_app.mappers;

import com.mpx90.training_app.models.training.RoutineEntity;
import com.mpx90.training_app.dto.core.Routine;
import com.mpx90.training_app.models.training.TrainingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface RoutineMapper {

    RoutineMapper INSTANCE = Mappers.getMapper(RoutineMapper.class);

    @Mapping(source = "training.id", target = "trainingId")
    @Mapping(source = "price", target = "price", qualifiedByName = "bigDecimalToDouble")
    Routine toDto(RoutineEntity entity);

    @Mapping(target = "training", source = "trainingId", qualifiedByName = "mapTrainingIdToEntity")
    @Mapping(source = "price", target = "price", qualifiedByName = "doubleToBigDecimal")
    RoutineEntity toEntity(Routine dto);

    @Named("mapTrainingIdToEntity")
    default TrainingEntity mapTrainingIdToEntity(Long trainingId) {
        if (trainingId == null || trainingId < 0) {
            return null; // Si no hay trainingId o es negativo, training será null
        }
        TrainingEntity training = new TrainingEntity();
        training.setId(trainingId);
        return training;
    }

    @Named("bigDecimalToDouble")
    default Double bigDecimalToDouble(BigDecimal price) {
        return price != null ? price.doubleValue() : null;
    }

    @Named("doubleToBigDecimal")
    default BigDecimal doubleToBigDecimal(Double price) {
        return price != null ? BigDecimal.valueOf(price) : null;
    }
}