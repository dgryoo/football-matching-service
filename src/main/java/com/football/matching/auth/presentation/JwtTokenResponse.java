package com.football.matching.auth.presentation;

import java.time.LocalDateTime;

public record JwtTokenResponse(
        String grantType, String accessToken, String refreshToken, LocalDateTime accessTokenExpiresAt,
        LocalDateTime refreshTokenExpiredAt
) {
}
