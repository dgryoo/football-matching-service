package com.football.matching.auth.presentation;

import com.football.matching.auth.application.AuthService;
import com.football.matching.member.application.MemberCommandService;
import com.football.matching.member.domain.Member;
import com.football.matching.member.presentation.MemberResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthController {

    private final AuthService authService;
    private final MemberCommandService memberCommandService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public MemberResponse signUp(@Valid @RequestBody SignupRequest request) {
        Member member = memberCommandService.register(request.email(), request.password(), request.nickname());
        return MemberResponse.from(member);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request.email(), request.password());
    }

}
