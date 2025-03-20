package com.mpx90.training_app.services;

import com.mpx90.training_app.models.UserEntity;
import com.mpx90.training_app.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenValidationService {

    private final JwtUtils jwtUtils;

    @Autowired
    public TokenValidationService(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    public boolean isTokenValid(final String token, final UserEntity user) {
        final String username = extractUsernameSafely(token);

        if(username == null) {
            return false;
        }

        System.out.println("username: " + username);

        if(!username.equals(user.getEmail())) {
            return false;
        }

        return !jwtUtils.isTokenExpired(token);
    }

    private String extractUsernameSafely(String token) {
        try {
            return jwtUtils.extractUsername(token);
        } catch (Exception e) {
            return null;
        }
    }
}
