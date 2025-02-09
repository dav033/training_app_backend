package com.mpx90.training_app.services.crud;

import com.mpx90.training_app.dto.core.User;
import com.mpx90.training_app.mappers.UserMapper;
import com.mpx90.training_app.models.UserEntity;
import com.mpx90.training_app.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public User create(User user) {
        UserEntity entity = userMapper.toEntity(user);
        return userMapper.toDto(userRepository.save(entity));
    }

    public List<User> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElse(null);
    }

    public User update(Long id, User user) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    UserEntity updatedEntity = userMapper.toEntity(user);
                    updatedEntity.setId(existingUser.getId());
                    return userMapper.toDto(userRepository.save(updatedEntity));
                })
                .orElse(null);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
