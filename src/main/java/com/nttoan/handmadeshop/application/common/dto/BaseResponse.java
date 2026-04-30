package com.nttoan.handmadeshop.application.common.dto;

public class BaseResponse<T> {
     private final T data;
    private final String code;
    private final String message;

    public BaseResponse(T data, String code, String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }

    // ===== SUCCESS =====

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(data, null, "Success");
    }

    public static <T> BaseResponse<T> success(T data, String message) {
        return new BaseResponse<>(data, null, message);
    }

    public static <T> BaseResponse<T> ok() {
        return new BaseResponse<>(null, null, "Success");
    }

    // ===== ERROR =====

    public static <T> BaseResponse<T> error(String code, String message) {
        return new BaseResponse<>(null, code, message);
    }

    // ===== GETTERS =====

    public T getData() {
        return data;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
