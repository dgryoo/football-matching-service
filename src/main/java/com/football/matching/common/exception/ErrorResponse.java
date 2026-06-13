package com.football.matching.common.exception;

import java.util.Map;

public record ErrorResponse (
  Integer statusCode, String message, String errorCode, Map<String, String> additionalInfo
) {
}
