package com.nttoan.handmadeshop.domain.identity.user.mapper;

import org.mapstruct.Mapper;

import com.nttoan.handmadeshop.domain.identity.user.dto.request.RegisterRequest;
import com.nttoan.handmadeshop.domain.identity.user.dto.response.RegisterResponse;
import com.nttoan.handmadeshop.domain.identity.user.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity toUserEntity(RegisterRequest registerRequest);

    RegisterResponse toRegisterResponse(UserEntity entity);
}
