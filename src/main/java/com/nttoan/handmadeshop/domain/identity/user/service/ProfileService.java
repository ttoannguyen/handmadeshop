package com.nttoan.handmadeshop.domain.identity.user.service;

import com.nttoan.handmadeshop.domain.identity.user.dto.response.ProfileResponse;

public interface ProfileService {
    ProfileResponse getProfile(String username);
}
