package com.nttoan.handmadeshop.presentation.rest.identity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nttoan.handmadeshop.application.identity.command.LoginCommand;
import com.nttoan.handmadeshop.application.identity.command.RegisterUserCommand;
import com.nttoan.handmadeshop.application.identity.dto.request.LoginRequest;
import com.nttoan.handmadeshop.application.identity.dto.request.RegisterRequest;
import com.nttoan.handmadeshop.application.identity.dto.response.LoginResponse;
import com.nttoan.handmadeshop.application.identity.dto.response.UserResponse;
import com.nttoan.handmadeshop.application.identity.mapper.UserAppMapper;
import com.nttoan.handmadeshop.application.identity.usecase.LoginUseCase;
import com.nttoan.handmadeshop.application.identity.usecase.RegisterUserUseCase;
import com.nttoan.handmadeshop.domain.identity.user.entity.User;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final RegisterUserUseCase registerUseCase;
    private final LoginUseCase loginUseCase;
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    public AuthController(RegisterUserUseCase registerUserUseCase, LoginUseCase loginUseCase) {
        this.registerUseCase = registerUserUseCase;
        this.loginUseCase = loginUseCase;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public UserResponse register(@Valid @RequestBody RegisterRequest request) {
        RegisterUserCommand command = UserAppMapper.toCommand(request);
        User user = registerUseCase.execute(command);
        return UserAppMapper.toResponse(user);
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        LoginCommand command = UserAppMapper.toLoginCommand(request);
        log.info("[AuthController] login attempt username={}", command.username());
        String token = loginUseCase.execute(command);
        return UserAppMapper.toLoginResponse(token);
    }
}
