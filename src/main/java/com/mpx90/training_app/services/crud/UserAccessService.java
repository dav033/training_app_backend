package com.mpx90.training_app.services.crud;


import com.mpx90.training_app.dto.core.UserAccess;
import com.mpx90.training_app.mappers.UserAccessMapper;
import com.mpx90.training_app.models.payment.UserAccessEntity;
import com.mpx90.training_app.repositories.UserAccessRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserAccessService {
    private final UserAccessRepository userAccessRepository;
    private final UserAccessMapper userAccessMapper;

    public UserAccessService(UserAccessRepository userAccessRepository, UserAccessMapper userAccessMapper) {
        this.userAccessRepository = userAccessRepository;
        this.userAccessMapper = userAccessMapper;
    }

    public UserAccess create(UserAccess userAccess) {
        UserAccessEntity entity = userAccessMapper.toEntity(userAccess);
        return userAccessMapper.toDto(userAccessRepository.save(entity));
    }

    public List<UserAccess> findAll() {
        return userAccessRepository.findAll().stream()
                .map(userAccessMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserAccess findById(Long id) {
        return userAccessRepository.findById(id)
                .map(userAccessMapper::toDto)
                .orElse(null);
    }

    public UserAccess update(Long id, UserAccess userAccess) {
        return userAccessRepository.findById(id)
                .map(existing -> {
                    UserAccessEntity updatedEntity = userAccessMapper.toEntity(userAccess);
                    updatedEntity.setId(existing.getId());
                    return userAccessMapper.toDto(userAccessRepository.save(updatedEntity));
                })
                .orElse(null);
    }

    public void delete(Long id) {
        userAccessRepository.deleteById(id);
    }
}

