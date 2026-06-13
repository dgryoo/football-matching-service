package com.football.matching.common.exception;

import java.util.Map;

public class BadRequestException extends CustomException {

    public BadRequestException(String message, String errorCode, Map<String, String> additionalInfo) {
        super(400, message, errorCode, additionalInfo);
    }
}
