package com.nttoan.handmadeshop.application.common.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    UNCATEGORIZED_EXCEPTION("9999", "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),

    USER_NOT_FOUND("1001", "User not found", HttpStatus.NOT_FOUND),
    INVALID_PASSWORD("1002", "Invalid password", HttpStatus.BAD_REQUEST),
    EMAIL_ALREADY_EXISTS("1003", "Email already exists", HttpStatus.BAD_REQUEST),

    UNAUTHENTICATED("1004", "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED("1005", "Forbidden", HttpStatus.FORBIDDEN),

    INVALID_REQUEST("1006", "Invalid request", HttpStatus.BAD_REQUEST);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

    ErrorCode(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getCode() { return code; }
    public String getMessage() { return message; }
    public HttpStatus getHttpStatus() { return httpStatus; }
}