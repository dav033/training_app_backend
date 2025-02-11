package com.mpx90.training_app.mappers;

import com.mpx90.training_app.dto.core.Subscription;
import com.mpx90.training_app.models.UserEntity;
import com.mpx90.training_app.models.payment.PaymentEntity;
import com.mpx90.training_app.models.payment.SubscriptionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper extends GenericMapper<Subscription, SubscriptionEntity> {

    SubscriptionMapper INSTANCE = Mappers.getMapper(SubscriptionMapper.class);

    // Mapping from Entity to DTO
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "payment.id", target = "paymentId")
    Subscription toDto(SubscriptionEntity entity);

    // Mapping from DTO to Entity con métodos auxiliares para evitar referencias directas
    @Mapping(source = "userId", target = "user", qualifiedByName = "mapUserIdToEntity")
    @Mapping(source = "paymentId", target = "payment", qualifiedByName = "mapPaymentIdToEntity")
    SubscriptionEntity toEntity(Subscription dto);

    @Named("mapUserIdToEntity")
    default UserEntity mapUserIdToEntity(Long userId) {
        if (userId == null) {
            return null;
        }
        UserEntity user = new UserEntity();
        user.setId(userId);
        return user;
    }

    @Named("mapPaymentIdToEntity")
    default PaymentEntity mapPaymentIdToEntity(Long paymentId) {
        if (paymentId == null) {
            return null;
        }
        PaymentEntity payment = new PaymentEntity();
        payment.setId(paymentId);
        return payment;
    }
}
