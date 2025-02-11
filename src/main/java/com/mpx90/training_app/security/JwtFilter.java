package com.mpx90.training_app.security;

import com.mpx90.training_app.enums.Role;
import com.mpx90.training_app.models.UserEntity;
import com.mpx90.training_app.repositories.UserRepository;
import com.mpx90.training_app.services.GetRoleService;
import com.mpx90.training_app.services.TokenValidationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final TokenValidationService tokenValidationService;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final GetRoleService getRoleService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String jwtToken = authHeader.substring(7);
        final String userEmail = jwtUtils.extractUsername(jwtToken);

        final Optional<UserEntity> userOptional = userRepository.findByEmail("david.theran03@gmail.com");

        System.out.println("userOptional: " + userOptional);

        if (userOptional.isPresent() && tokenValidationService.isTokenValid(jwtToken, userOptional.get())) {
            UserEntity user = userOptional.get();

            Role role = getRoleService.getUserRole(user.getUuid());

            List<SimpleGrantedAuthority> authorities = role.getPermissions().stream()
                    .map(permission -> new SimpleGrantedAuthority(permission.name()))
                    .toList();

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userEmail, null, authorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}

