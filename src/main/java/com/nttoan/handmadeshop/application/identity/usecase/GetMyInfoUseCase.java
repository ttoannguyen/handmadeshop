package com.nttoan.handmadeshop.application.identity.usecase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.nttoan.handmadeshop.application.common.exception.AppException;
import com.nttoan.handmadeshop.application.common.exception.ErrorCode;
import com.nttoan.handmadeshop.domain.identity.user.entity.User;
import com.nttoan.handmadeshop.domain.identity.user.repository.UserRepository;

@Service
public class GetMyInfoUseCase {
    private final UserRepository userRepository;
    private static final Logger log = LoggerFactory.getLogger(GetMyInfoUseCase.class);

    public GetMyInfoUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        String username = authentication.getName();
        log.info("Username: {}", username);

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    }

}
