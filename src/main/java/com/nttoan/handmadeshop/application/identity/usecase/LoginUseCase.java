package com.nttoan.handmadeshop.application.identity.usecase;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nttoan.handmadeshop.application.identity.command.LoginCommand;
import com.nttoan.handmadeshop.domain.identity.user.entity.User;
import com.nttoan.handmadeshop.domain.identity.user.repository.UserRepository;
import com.nttoan.handmadeshop.infrastructure.security.JwtService;

@Service
public class LoginUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    // private final JwtProvider
    public LoginUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String execute(LoginCommand command) {
        User user = userRepository.findByEmail(command.email())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(command.password(), user.getPasswordHash()))
            throw new RuntimeException("Invalid password");
        return jwtService.generateToken(user.getId(), user.getEmail(), user.getRole().name());
    }
}
