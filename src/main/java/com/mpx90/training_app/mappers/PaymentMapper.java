package com.mpx90.training_app.mappers;

import com.mpx90.training_app.models.payment.PaymentEntity;
import com.mpx90.training_app.dto.core.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    // Mapeo de Entity a DTO
    @Mapping(source = "user.id", target = "userId")
    Payment toDto(PaymentEntity entity);

    // Mapeo de DTO a Entity
    @Mapping(source = "userId", target = "user.id")
    PaymentEntity toEntity(Payment dto);
}
