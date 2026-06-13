package com.football.matching.common.exception;

import java.util.Map;

public class ForbiddenException extends CustomException {

    public ForbiddenException(String message, String errorCode, Map<String, String> additionalInfo) {
        super(403, message, errorCode, additionalInfo);
    }
}
