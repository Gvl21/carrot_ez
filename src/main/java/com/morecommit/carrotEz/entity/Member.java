package com.morecommit.carrotEz.entity;

import com.morecommit.carrotEz.constant.Address;
import com.morecommit.carrotEz.constant.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class Member {
    @Id
    @Column(name = "member_id")
    @GeneratedValue
    private Long id;

    private String nickname;

    // 유일한 값이 들어갈 수 있게 속성 지정. 로그인 시 사용
    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Address address;
    @Enumerated(EnumType.STRING)
    private Role role;

}
