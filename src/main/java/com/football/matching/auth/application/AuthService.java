package com.football.matching.auth.application;

import com.football.matching.auth.presentation.JwtTokenResponse;
import com.football.matching.auth.presentation.LoginResponse;
import com.football.matching.common.exception.UnauthorizedException;
import com.football.matching.member.domain.Member;
import com.football.matching.member.domain.MemberRepository;
import com.football.matching.member.presentation.MemberResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginResponse login(String email, String password) {
        Member member = memberRepository
                .findByEmail(email)
                .orElseThrow(() -> new UnauthorizedException("이메일 또는 비밀번호가 올바르지 않습니다.", null, null));

        if (!passwordEncoder.matches(password, member.getPassword()))
            throw new UnauthorizedException("이메일 또는 비밀번호가 올바르지 않습니다.", null, null);

        JwtTokenResponse jwtTokenResponse = jwtTokenProvider.createToken(member);

        return new LoginResponse(jwtTokenResponse, MemberResponse.from(member));
    }
}
