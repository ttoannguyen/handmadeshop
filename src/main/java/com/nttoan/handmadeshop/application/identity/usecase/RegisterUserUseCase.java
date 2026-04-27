package com.nttoan.handmadeshop.application.identity.usecase;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nttoan.handmadeshop.application.identity.command.RegisterUserCommand;
import com.nttoan.handmadeshop.domain.identity.user.entity.Role;
import com.nttoan.handmadeshop.domain.identity.user.entity.User;
import com.nttoan.handmadeshop.domain.identity.user.repository.UserRepository;

@Service
public class RegisterUserUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterUserUseCase(
        UserRepository userRepository,
        PasswordEncoder passwordEncoder
    ){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User execute(RegisterUserCommand cmd ){
        if(userRepository.findByEmail(cmd.email()).isPresent()){
            throw new RuntimeException("Email already exists");
        }
        String hashedPassword = passwordEncoder.encode(cmd.password());

        User user = new User(
            cmd.username(),
            cmd.email(),
            hashedPassword,
            cmd.fullName(),
            cmd.dateOfBirth(),
            Role.USER
        );

        return userRepository.save(user);
    }
}
