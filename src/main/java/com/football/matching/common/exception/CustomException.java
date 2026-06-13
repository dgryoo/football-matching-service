package com.football.matching.common.exception;

import lombok.Getter;

import java.util.Map;

@Getter
public abstract class CustomException extends RuntimeException {

    private final Integer statusCode;

    private final String errorCode;

    private final Map<String, String> additionalInfo;

    public CustomException(int statusCode, String message, String errorCode, Map<String, String> additionalInfo) {
        super(message);
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.additionalInfo = additionalInfo;
    }
}
