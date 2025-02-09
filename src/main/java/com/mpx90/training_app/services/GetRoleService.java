package com.mpx90.training_app.services;


import com.mpx90.training_app.enums.Role;
import com.mpx90.training_app.models.UserEntity;
import com.mpx90.training_app.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetRoleService {

    private final UserRepository userRepository;

    public GetRoleService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Role getUserRole(UUID uuid) {

        final UserEntity user = userRepository.findByUuid(uuid).orElseThrow(() -> new IllegalArgumentException("User not found"));

        return user.getRole();
    }
}
