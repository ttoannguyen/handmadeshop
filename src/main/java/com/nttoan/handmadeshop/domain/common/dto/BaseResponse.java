package com.nttoan.handmadeshop.domain.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {

    private T data;
    private String code;
    private String message;

    protected static final String SUCCESS_MESSAGE = "Success";

    public static <T> BaseResponse<T> success(T data) {
        return BaseResponse.<T>builder()
                .data(data)
                .code(null)
                .message(SUCCESS_MESSAGE)
                .build();
    }

    public static <T> BaseResponse<T> of(T data) {
        return BaseResponse.<T>builder()
                .data(data)
                .message(SUCCESS_MESSAGE)
                .code(null)
                .build();
    }

    public static <T> BaseResponse<T> of(T data, String code) {
        return BaseResponse.<T>builder()
                .data(data)
                .message(SUCCESS_MESSAGE)
                .code(code)
                .build();
    }

    public static <T> BaseResponse<T> withMessage(T data, String message) {
        return BaseResponse.<T>builder()
                .data(data)
                .message(message)
                .code(null)
                .build();
    }

    public static <T> BaseResponse<T> ok() {
        return BaseResponse.<T>builder()
                .data(null)
                .message(SUCCESS_MESSAGE)
                .code(null)
                .build();
    }

    public static <T> BaseResponse<T> ok(String message) {
        return BaseResponse.<T>builder()
                .data(null)
                .message(message)
                .code(null)
                .build();
    }

    public static <T> BaseResponse<T> error(String exceptionCode, String exceptionMessage) {
        return BaseResponse.<T>builder()
                .data(null)
                .message(exceptionMessage)
                .code(exceptionCode)
                .build();
    }
}