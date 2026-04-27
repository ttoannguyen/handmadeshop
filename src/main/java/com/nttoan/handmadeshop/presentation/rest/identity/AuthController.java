package com.nttoan.handmadeshop.presentation.rest.identity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttoan.handmadeshop.application.identity.command.RegisterUserCommand;
import com.nttoan.handmadeshop.application.identity.dto.request.RegisterRequest;
import com.nttoan.handmadeshop.application.identity.dto.response.UserResponse;
import com.nttoan.handmadeshop.application.identity.mapper.UserAppMapper;
import com.nttoan.handmadeshop.application.identity.usecase.RegisterUserUseCase;
import com.nttoan.handmadeshop.domain.identity.user.entity.User;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final RegisterUserUseCase registerUseCase;

    public AuthController(RegisterUserUseCase registerUserUseCase) {
        this.registerUseCase = registerUserUseCase;
    }

    @PostMapping("/register")
    public UserResponse register(@RequestBody RegisterRequest request) {
        RegisterUserCommand commad = UserAppMapper.toCommand(request);
        User user = registerUseCase.execute(commad);
        return UserAppMapper.toResponse(user);
    }
}
