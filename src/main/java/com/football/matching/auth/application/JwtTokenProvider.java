package com.football.matching.auth.application;

import com.football.matching.auth.presentation.JwtTokenResponse;
import com.football.matching.member.domain.Member;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


@Component
public class JwtTokenProvider {
    private static final String GRANT_TYPE = "Bearer";
    private static final long ACCESS_TOKEN_EXPIRE_MILLIS = 1000L * 60 * 30;
    private static final long REFRESH_TOKEN_EXPIRE_MILLIS = 1000L * 60 * 60 * 24 * 14;
    private static final String SECRET_KEY = "temp-secret-temp-secret-temp-secret-temp-secret-temp-secret-temp-secret";
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    public JwtTokenResponse createToken(Member member) {
        Date now = new Date();

        Date accessTokenExpireDate = new Date(now.getTime() + ACCESS_TOKEN_EXPIRE_MILLIS);
        Date refreshTokenExpireDate = new Date(now.getTime() + REFRESH_TOKEN_EXPIRE_MILLIS);

        String accessToken = Jwts.builder()
                .subject(member.getUuid())
                .issuedAt(now)
                .expiration(accessTokenExpireDate)
                .signWith(key)
                .compact();

        String refreshToken = Jwts.builder()
                .subject(member.getUuid())
                .issuedAt(now)
                .expiration(refreshTokenExpireDate)
                .signWith(key)
                .compact();

        return new JwtTokenResponse(
                GRANT_TYPE, accessToken, refreshToken, toLocalDateTime(accessTokenExpireDate),
                toLocalDateTime(refreshTokenExpireDate));
    }

    private LocalDateTime toLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }
}
