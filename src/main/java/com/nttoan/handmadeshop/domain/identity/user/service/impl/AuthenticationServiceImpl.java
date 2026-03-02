package com.nttoan.handmadeshop.domain.identity.user.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nttoan.handmadeshop.domain.common.dto.BaseResponse;
import com.nttoan.handmadeshop.domain.identity.user.dto.request.RegisterRequest;
import com.nttoan.handmadeshop.domain.identity.user.dto.response.RegisterResponse;
import com.nttoan.handmadeshop.domain.identity.user.entity.Role;
import com.nttoan.handmadeshop.domain.identity.user.entity.UserEntity;
import com.nttoan.handmadeshop.domain.identity.user.repository.UserRepository;
import com.nttoan.handmadeshop.domain.identity.user.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public BaseResponse<RegisterResponse> register(RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            return BaseResponse.error("Username already exists", "USERNAME_EXISTS");

        }
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            return BaseResponse.error("Email already exists", "EMAIL_EXISTS");
        }

        UserEntity user = new UserEntity();

        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setFullname(registerRequest.getFullname());
        user.setPasswordHash(passwordEncoder.encode(registerRequest.getPassword()));
        user.setDateOfBirth(registerRequest.getDateOfBirth());
        user.setRole(Role.USER);
        user.setEnabled(true);

        userRepository.save(user);

        RegisterResponse response = RegisterResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullname(user.getFullname())
                .dateOfBirth(user.getDateOfBirth())
                .role(user.getRole())
                .enabled(user.isEnabled())
                .build();

        return BaseResponse.success(response);
    }

}
