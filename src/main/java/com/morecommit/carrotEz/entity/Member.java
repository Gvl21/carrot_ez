package com.morecommit.carrotEz.entity;

import com.morecommit.carrotEz.constant.Role;
import com.morecommit.carrotEz.dto.request.member.MemberRegisterRequestDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity @Getter @Setter
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nickname;

    // 유일한 값이 들어갈 수 있게 속성 지정. 로그인 시 사용
    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String category;

    private String area;

    private String memberImageUrl;



    public static Member createMember(MemberRegisterRequestDto memberRegisterRequestDto,
                                      PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setNickname(memberRegisterRequestDto.getNickname());
        member.setEmail(memberRegisterRequestDto.getEmail());
        member.setPassword(memberRegisterRequestDto.getPassword());
        member.setArea(memberRegisterRequestDto.getArea());
        member.setCategory(memberRegisterRequestDto.getCategory());

        String encodedPassword = passwordEncoder.encode(memberRegisterRequestDto.getPassword());
        member.setPassword(encodedPassword);

        member.setRole(Role.USER);

        return member;
    }
}
