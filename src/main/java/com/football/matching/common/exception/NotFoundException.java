package com.football.matching.common.exception;

import java.util.Map;

public class NotFoundException extends CustomException {

    public NotFoundException(String message, String errorCode, Map<String, String> additionalInfo) {
        super(404, message, errorCode, additionalInfo);
    }
}
