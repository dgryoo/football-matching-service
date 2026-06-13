package com.football.matching.auth.presentation;

import com.football.matching.member.presentation.MemberResponse;

public record LoginResponse(
        JwtTokenResponse jwtToken, MemberResponse member
) {
}
