package com.nttoan.handmadeshop.application.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> {
    private final String code;
    private final String message;
    private final int status;
    private final T data;
    private final Object error;

    public BaseResponse(String code, String message, int status, T data, Object error) {
        this.code = code;
        this.message = message;
        this.status = status;
        this.data = data;
        this.error = error;
    }

    // success
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>("SUCCESS", "Success", 200, data, null);
    }

    public static <T> BaseResponse<T> success(T data, String message) {
        return new BaseResponse<>("SUCCESS", message, 200, data, null);
    }

    public static <T> BaseResponse<T> created(T data) {
        return new BaseResponse<>("SUCCESS", "Created", 201, data, null);
    }

    // not found
    public static <T> BaseResponse<T> notFound(String message) {
        return new BaseResponse<>("NOT_FOUND", message, 404, null, null);
    }

    // validation error
    public static <T> BaseResponse<T> validationError(String message, Object error) {
        return new BaseResponse<>("VALIDATION_ERROR", message, 400, null, error);
    }

    // generic error
    public static <T> BaseResponse<T> error(String code, String message, int status, Object error) {
        return new BaseResponse<>(code, message, status, null, error);
    }

    public static <T> BaseResponse<T> error(String code, String message, int status) {
        return new BaseResponse<>(code, message, status, null, null);
    }

    public static <T> BaseResponse<T> error(String code, String message, HttpStatus status) {
        return new BaseResponse<>(code, message, status.value(), null, null);
    }

    // getters
    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public Object getError() {
        return error;
    }
}
