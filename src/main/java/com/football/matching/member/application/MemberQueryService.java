package com.football.matching.member.application;

import com.football.matching.common.exception.NotFoundException;
import com.football.matching.member.domain.Member;
import com.football.matching.member.domain.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MemberQueryService {
    private final MemberRepository memberRepository;

    public MemberQueryService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member findByIdOrElseNull(Long memberId) {
        return memberRepository.findById(memberId).orElse(null);
    }

    public Member findByIdOrElseThrow(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException("회원을 찾을 수 없습니다.", null, null));
    }

    public Member findByEmailOrElseNull(String email) {
        return memberRepository.findByEmail(email)
                .orElse(null);
    }

    public Member findByEmailOrElseThrow(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("회원을 찾을 수 없습니다.", null, null));
    }
}
