package com.morecommit.carrotEz.entity;

import com.morecommit.carrotEz.constant.Role;
import com.morecommit.carrotEz.dto.MemberDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;

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


    public static Member createMember(MemberDto memberDto ,
                                      PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setNickname(memberDto.getNickname());
        member.setEmail(memberDto.getEmail());
        member.setPassword(memberDto.getPassword());
        member.setArea(memberDto.getArea());
        member.setCategory(memberDto.getCategory());

        String encodedPassword = passwordEncoder.encode(memberDto.getPassword());
        member.setPassword(encodedPassword);

        member.setRole(Role.USER);

        return member;
    }
}
