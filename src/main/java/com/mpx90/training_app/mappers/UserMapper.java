package com.mpx90.training_app.mappers;

import com.mpx90.training_app.models.UserEntity;
import com.mpx90.training_app.dto.core.User;
import com.mpx90.training_app.models.payment.PaymentEntity;
import com.mpx90.training_app.models.payment.SubscriptionEntity;
import com.mpx90.training_app.models.payment.UserAccessEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // Mapeo de Entity a DTO
    @Mapping(source = "payments", target = "paymentIds", qualifiedByName = "mapPaymentIds")
    @Mapping(source = "subscriptions", target = "subscriptionIds", qualifiedByName = "mapSubscriptionIds")
    @Mapping(source = "userAccesses", target = "userAccessIds", qualifiedByName = "mapUserAccessIds")
    User toDto(UserEntity entity);

    // Mapeo de DTO a Entity
    @Mapping(target = "payments", ignore = true)
    @Mapping(target = "subscriptions", ignore = true)
    @Mapping(target = "userAccesses", ignore = true)
    UserEntity toEntity(User dto);

    // Métodos auxiliares para mapear solo los IDs de las relaciones
    @Named("mapPaymentIds")
    default List<Long> mapPaymentIds(List<PaymentEntity> payments) {
        return payments != null ? payments.stream().map(PaymentEntity::getId).collect(Collectors.toList()) : null;
    }

    @Named("mapSubscriptionIds")
    default List<Long> mapSubscriptionIds(List<SubscriptionEntity> subscriptions) {
        return subscriptions != null ? subscriptions.stream().map(SubscriptionEntity::getId).collect(Collectors.toList()) : null;
    }

    @Named("mapUserAccessIds")
    default List<Long> mapUserAccessIds(List<UserAccessEntity> userAccesses) {
        return userAccesses != null ? userAccesses.stream().map(UserAccessEntity::getId).collect(Collectors.toList()) : null;
    }
}
