package com.morecommit.carrotEz.controller;

import com.morecommit.carrotEz.dto.MemberDto;
import com.morecommit.carrotEz.repository.MemberRepository;
import com.morecommit.carrotEz.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    @PostMapping("/members/new")
    public String memberForm(@Valid MemberDto memberDto,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "member/memberForm";
        }
        return null;
    }


}
