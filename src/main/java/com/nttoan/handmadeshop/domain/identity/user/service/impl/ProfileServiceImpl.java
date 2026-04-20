package com.nttoan.handmadeshop.domain.identity.user.service.impl;

import org.springframework.stereotype.Service;

import com.nttoan.handmadeshop.domain.identity.user.dto.response.ProfileResponse;
import com.nttoan.handmadeshop.domain.identity.user.entity.UserEntity;
import com.nttoan.handmadeshop.domain.identity.user.mapper.UserMapper;
import com.nttoan.handmadeshop.domain.identity.user.repository.UserRepository;
import com.nttoan.handmadeshop.domain.identity.user.service.ProfileService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public ProfileResponse getProfile(String username) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toProfileResponse(user);
    }
}
