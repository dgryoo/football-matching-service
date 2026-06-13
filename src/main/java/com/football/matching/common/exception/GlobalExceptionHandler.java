package com.football.matching.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getStatusCode(), e.getMessage(), e.getErrorCode(),
                e.getAdditionalInfo());
        return ResponseEntity.status(e.getStatusCode()).body(errorResponse);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("Unhandled exception occurred", e);
        // Sentry 등 에러 시스템 연동
        ErrorResponse errorResponse = new ErrorResponse(500, "알 수 없는 오류입니다.", null, null);
        return ResponseEntity.status(500).body(errorResponse);
    }
}
