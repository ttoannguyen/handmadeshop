package com.nttoan.handmadeshop.application.identity.mapper;

import com.nttoan.handmadeshop.application.identity.command.LoginCommand;
import com.nttoan.handmadeshop.application.identity.command.RegisterUserCommand;
import com.nttoan.handmadeshop.application.identity.dto.request.LoginRequest;
import com.nttoan.handmadeshop.application.identity.dto.request.RegisterRequest;
import com.nttoan.handmadeshop.application.identity.dto.response.LoginResponse;
import com.nttoan.handmadeshop.application.identity.dto.response.UserResponse;
import com.nttoan.handmadeshop.domain.identity.user.entity.User;

public class UserAppMapper {

    public static RegisterUserCommand toCommand(RegisterRequest req){
        return new RegisterUserCommand(
            req.getUsername(),
            req.getEmail(),
            req.getPassword(),
            req.getFullName(),
            req.getDateOfBirth()
        );
    }

    public static UserResponse toResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .build();
    }

    public static LoginCommand toLoginCommand(LoginRequest req){
        return new LoginCommand(
            req.getUsername(),
            req.getPassword()
        );
    }

    public static LoginResponse toLoginResponse(String token){
        return LoginResponse.builder().accessToken(token).build();
    }
}