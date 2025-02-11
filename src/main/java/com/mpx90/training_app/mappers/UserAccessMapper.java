package com.mpx90.training_app.mappers;

import com.mpx90.training_app.dto.core.UserAccess;
import com.mpx90.training_app.models.UserEntity;
import com.mpx90.training_app.models.payment.UserAccessEntity;
import com.mpx90.training_app.models.training.RoutineEntity;
import com.mpx90.training_app.models.training.TrainingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserAccessMapper extends GenericMapper<UserAccess, UserAccessEntity> {

    UserAccessMapper INSTANCE = Mappers.getMapper(UserAccessMapper.class);

    // Mapeo de Entity a DTO
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "training.id", target = "trainingId")
    @Mapping(source = "routine.id", target = "routineId")
    UserAccess toDto(UserAccessEntity entity);

    // Mapeo de DTO a Entity con métodos auxiliares para evitar referencias directas
    @Mapping(source = "userId", target = "user", qualifiedByName = "mapUserIdToEntity")
    @Mapping(source = "trainingId", target = "training", qualifiedByName = "mapTrainingIdToEntity")
    @Mapping(source = "routineId", target = "routine", qualifiedByName = "mapRoutineIdToEntity")
    UserAccessEntity toEntity(UserAccess dto);

    @Named("mapUserIdToEntity")
    default UserEntity mapUserIdToEntity(Long userId) {
        if (userId == null) {
            return null;
        }
        UserEntity user = new UserEntity();
        user.setId(userId);
        return user;
    }

    @Named("mapTrainingIdToEntity")
    default TrainingEntity mapTrainingIdToEntity(Long trainingId) {
        if (trainingId == null) {
            return null;
        }
        TrainingEntity training = new TrainingEntity();
        training.setId(trainingId);
        return training;
    }

    @Named("mapRoutineIdToEntity")
    default RoutineEntity mapRoutineIdToEntity(Long routineId) {
        if (routineId == null) {
            return null;
        }
        RoutineEntity routine = new RoutineEntity();
        routine.setId(routineId);
        return routine;
    }
}
