package com.nttoan.handmadeshop.application.common.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nttoan.handmadeshop.application.common.dto.BaseResponse;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

        // ===== BUSINESS =====
        @ExceptionHandler(AppException.class)
        public ResponseEntity<BaseResponse<?>> handleAppException(AppException ex) {

                ErrorCode errorCode = ex.getErrorCode();

                log.warn("AppException: {}", errorCode.getMessage());

                return ResponseEntity
                                .status(errorCode.getHttpStatus())
                                .body(BaseResponse.error(errorCode.getCode(), errorCode.getMessage(),
                                                errorCode.getHttpStatus()));
        }

        // ===== VALIDATION =====
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<BaseResponse<Map<String, String>>> handleValidation(
                        MethodArgumentNotValidException ex) {

                Map<String, String> errors = new HashMap<>();

                for (var error : ex.getBindingResult().getAllErrors()) {
                        String field = ((FieldError) error).getField();
                        String message = error.getDefaultMessage();
                        errors.put(field, message);
                }

                log.warn("Validation error: {}", errors);

                return ResponseEntity
                                .status(ErrorCode.INVALID_REQUEST.getHttpStatus())
                                .body(BaseResponse.validationError("Validation failed", errors));
        }

        // ===== AUTH =====
        @ExceptionHandler(AuthenticationException.class)
        public ResponseEntity<BaseResponse<Void>> handleAuth(AuthenticationException ex) {

                log.warn("Authentication error: {}", ex.getMessage());

                return ResponseEntity
                                .status(ErrorCode.UNAUTHENTICATED.getHttpStatus())
                                .body(BaseResponse.error(
                                                ErrorCode.UNAUTHENTICATED.getCode(),
                                                ErrorCode.UNAUTHENTICATED.getMessage(),
                                                ErrorCode.UNAUTHENTICATED.getHttpStatus()));
        }

        @ExceptionHandler(AccessDeniedException.class)
        public ResponseEntity<BaseResponse<Void>> handleAccessDenied(AccessDeniedException ex) {

                log.warn("Access denied: {}", ex.getMessage());

                return ResponseEntity
                                .status(ErrorCode.UNAUTHORIZED.getHttpStatus())
                                .body(BaseResponse.error(
                                                ErrorCode.UNAUTHORIZED.getCode(),
                                                ErrorCode.UNAUTHORIZED.getMessage(),
                                                ErrorCode.UNAUTHORIZED.getHttpStatus()));
        }

        // ===== FALLBACK =====
        @ExceptionHandler(Exception.class)
        public ResponseEntity<BaseResponse<Void>> handleUnknown(Exception ex) {

                log.error("Unexpected error", ex);

                return ResponseEntity
                                .status(ErrorCode.UNCATEGORIZED_EXCEPTION.getHttpStatus())
                                .body(BaseResponse.error(
                                                ErrorCode.UNCATEGORIZED_EXCEPTION.getCode(),
                                                ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage(),
                                                ErrorCode.UNCATEGORIZED_EXCEPTION.getHttpStatus()));
        }
}