package com.morecommit.carrotEz.controller;

import com.morecommit.carrotEz.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/member")
    public String memberTest(){


        return "멤버 컨트롤러 테스트";
    }


}
