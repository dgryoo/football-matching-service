package com.football.matching.member.domain;

import com.football.matching.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String nickname;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    private Member(String email, String password, String nickname) {
        validateEmail(email);
        validatePassword(password);
        validateNickname(nickname);

        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.status = MemberStatus.ACTIVE;
    }

    public static Member register(String email, String password, String nickname) {
        return new Member(email, password, nickname);
    }

    public void changeNickname(String nickname) {
        ensureActive();
        validateNickname(nickname);
        this.nickname = nickname;
    }

    public void changePassword(String password) {
        ensureActive();
        validatePassword(password);
        this.password = password;
    }

    public void withdraw() {
        ensureActive();
        this.status = MemberStatus.WITHDRAWN;
    }

    private void ensureActive() {
        if (this.status != MemberStatus.ACTIVE) {
            throw new IllegalStateException("활성 회원만 변경할 수 있습니다.");
        }
    }

    private void validateEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("이메일은 필수입니다.");
        }

        if (!email.contains("@")) {
            throw new IllegalArgumentException("올바른 이메일 형식이 아닙니다.");
        }
    }

    private void validatePassword(String password) {
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("비밀번호는 필수입니다.");
        }

        if (password.length() < 8) {
            throw new IllegalArgumentException("비밀번호는 8자 이상이어야 합니다.");
        }
    }

    private void validateNickname(String nickname) {
        if (nickname == null || nickname.isBlank()) {
            throw new IllegalArgumentException("닉네임은 필수입니다.");
        }

        if (nickname.length() > 20) {
            throw new IllegalArgumentException("닉네임은 20자를 초과할 수 없습니다.");
        }
    }
}
