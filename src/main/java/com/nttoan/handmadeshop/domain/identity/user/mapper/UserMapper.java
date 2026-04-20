package com.nttoan.handmadeshop.domain.identity.user.mapper;

import org.mapstruct.Mapper;

import com.nttoan.handmadeshop.domain.identity.user.dto.response.ProfileResponse;
import com.nttoan.handmadeshop.domain.identity.user.dto.response.RegisterResponse;
import com.nttoan.handmadeshop.domain.identity.user.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {
    RegisterResponse toRegisterResponse(UserEntity entity);

    ProfileResponse toProfileResponse(UserEntity entity);
}
