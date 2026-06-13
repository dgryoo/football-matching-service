package com.football.matching.common.exception;

import java.util.Map;

public class ResourceConflictException extends CustomException {

    public ResourceConflictException(String message, String errorCode, Map<String, String> additionalInfo) {
        super(409, message, errorCode, additionalInfo);
    }
}
