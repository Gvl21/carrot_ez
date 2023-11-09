package com.morecommit.carrotEz.controller;

import com.morecommit.carrotEz.dto.MemberDto;
import com.morecommit.carrotEz.repository.MemberRepository;
import com.morecommit.carrotEz.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    private final MemberRepository memberRepository;

    @GetMapping("/members/new")
    public String memberForm(Model model){
        model.addAttribute("memberDto", new MemberDto());
        return "member/memberForm";
    }


}
