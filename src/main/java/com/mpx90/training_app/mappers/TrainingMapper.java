package com.mpx90.training_app.mappers;

import com.mpx90.training_app.dto.core.Training;
import com.mpx90.training_app.models.training.TrainingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface TrainingMapper extends GenericMapper<Training, TrainingEntity> {

    TrainingMapper INSTANCE = Mappers.getMapper(TrainingMapper.class);

    // Mapeo de Entity a DTO
    @Mapping(source = "price", target = "price", qualifiedByName = "bigDecimalToDouble")
    Training toDto(TrainingEntity entity);

    // Mapeo de DTO a Entity
    @Mapping(source = "price", target = "price", qualifiedByName = "doubleToBigDecimal")
    TrainingEntity toEntity(Training dto);

    @Named("bigDecimalToDouble")
    default Double bigDecimalToDouble(BigDecimal price) {
        return (price != null) ? price.doubleValue() : null;
    }

    @Named("doubleToBigDecimal")
    default BigDecimal doubleToBigDecimal(Double price) {
        return (price != null) ? BigDecimal.valueOf(price) : null;
    }
}
