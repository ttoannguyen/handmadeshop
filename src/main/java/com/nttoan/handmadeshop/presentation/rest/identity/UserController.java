package com.nttoan.handmadeshop.presentation.rest.identity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttoan.handmadeshop.application.common.dto.BaseResponse;
import com.nttoan.handmadeshop.application.identity.dto.response.UserResponse;
import com.nttoan.handmadeshop.application.identity.mapper.UserAppMapper;
import com.nttoan.handmadeshop.application.identity.usecase.GetMyInfoUseCase;
import com.nttoan.handmadeshop.domain.identity.user.entity.User;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final GetMyInfoUseCase getMyInfoUseCase;
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/me")
    public BaseResponse<UserResponse> myInfo() {
        log.info("/api/v1/users/me");
        User user = getMyInfoUseCase.execute();
        return BaseResponse.success(UserAppMapper.toResponse(user));
    }

}
