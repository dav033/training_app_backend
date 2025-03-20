package com.mpx90.training_app.services;

import com.mpx90.training_app.dto.core.User;
import com.mpx90.training_app.mappers.UserMapper;
import com.mpx90.training_app.models.UserEntity;
import com.mpx90.training_app.repositories.UserRepository;
import com.mpx90.training_app.services.base.BaseService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AuthService extends BaseService<User, Long, UserEntity, UserRepository> {

    public AuthService(UserRepository repository, @Qualifier("userMapperImpl") UserMapper mapper) {
        super(repository, mapper);
    }

    public User register(User user) {
        return mapper.toDto(repository.save(mapper.toEntity(user)));
    }
}
