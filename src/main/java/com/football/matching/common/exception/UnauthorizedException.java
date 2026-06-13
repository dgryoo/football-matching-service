package com.football.matching.common.exception;

import java.util.Map;

public class UnauthorizedException extends CustomException {

    public UnauthorizedException(String message, String errorCode, Map<String, String> additionalInfo) {
        super(401, message, errorCode, additionalInfo);
    }
}
