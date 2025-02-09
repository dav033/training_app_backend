package com.mpx90.training_app.mappers;

import com.mpx90.training_app.dto.core.Subscription;
import com.mpx90.training_app.models.payment.SubscriptionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

        SubscriptionMapper INSTANCE = Mappers.getMapper(SubscriptionMapper.class);

        // Mapping from Entity to DTO
        @Mapping(source = "user.id", target = "userId")
        @Mapping(source = "payment.id", target = "paymentId")
        Subscription toDto(SubscriptionEntity entity);

        // Mapping from DTO to Entity
        @Mapping(source = "userId", target = "user.id")
        @Mapping(source = "paymentId", target = "payment.id")
        SubscriptionEntity toEntity(Subscription dto);
}