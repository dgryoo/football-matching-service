package com.football.matching.member.application;


import com.football.matching.common.exception.BadRequestException;
import com.football.matching.common.exception.NotFoundException;
import com.football.matching.member.domain.Member;
import com.football.matching.member.domain.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MemberCommandService {

    private final MemberRepository memberRepository;

    public MemberCommandService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member register(String email, String password, String nickname) {
        validateDuplicateEmail(email);

        Member member = Member.register(email, password, nickname);
        return memberRepository.save(member);
    }

    public void changeNickname(Long memberId, String nickname) {
        Member member = findByIdOrElseThrow(memberId);
        member.changeNickname(nickname);
    }

    public void changePassword(Long memberId, String password) {
        Member member = findByIdOrElseThrow(memberId);
        member.changePassword(password);
    }

    public void withdraw(Long memberId) {
        Member member = findByIdOrElseThrow(memberId);
        member.withdraw();
    }

    private void validateDuplicateEmail(String email) {
        if (memberRepository.existsByEmail(email)) throw new BadRequestException("이미 존재하는 이메일입니다.", null, null);
    }

    private Member findByIdOrElseThrow(Long memberId) {
        return memberRepository
                .findById(memberId)
                .orElseThrow(() -> new NotFoundException("회원을 찾을 수 없습니다.", null, null));
    }

}
