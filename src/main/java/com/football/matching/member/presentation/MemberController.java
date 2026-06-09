package com.football.matching.member.presentation;

import com.football.matching.member.application.MemberCommandService;
import com.football.matching.member.application.MemberQueryService;
import com.football.matching.member.domain.Member;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    public MemberController(MemberCommandService memberCommandService,  MemberQueryService memberQueryService) {
        this.memberCommandService = memberCommandService;
        this.memberQueryService = memberQueryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MemberResponse registerMember(@Valid @RequestBody RegisterMemberRequest memberRequest) {
        Member member = memberCommandService.register(memberRequest.email(), memberRequest.password(), memberRequest.nickname());
        return MemberResponse.from(member);
    }

    @GetMapping("{memberId}")
    @ResponseStatus(HttpStatus.OK)
    public MemberResponse findMemberById(@PathVariable Long memberId) {
        Member member = memberQueryService.findByIdOrElseThrow(memberId);
        return MemberResponse.from(member);
    }

    @PatchMapping("{memberId}")
    @ResponseStatus(HttpStatus.OK)
    public MemberResponse changeNickName(
            @PathVariable Long memberId,
            @RequestBody ChangeNickNameRequest changeNickNameRequest) {
        memberCommandService.changeNickname(memberId, changeNickNameRequest.nickName());
        Member member = memberQueryService.findByIdOrElseThrow(memberId);
        return MemberResponse.from(member);
    }

    @PatchMapping("{memberId}/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword(
            @PathVariable Long memberId,
            @RequestBody ChangePasswordRequest changePasswordRequest) {
        memberCommandService.changePassword(memberId, changePasswordRequest.password());
    }

    @DeleteMapping("{memberId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void withdraw(@PathVariable Long memberId) {
        memberCommandService.withdraw(memberId);
    }
}
