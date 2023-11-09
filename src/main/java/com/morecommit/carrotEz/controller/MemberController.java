package com.morecommit.carrotEz.controller;

import com.morecommit.carrotEz.dto.MemberDto;
import com.morecommit.carrotEz.entity.Member;
import com.morecommit.carrotEz.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor

public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

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
        try {
            // 에러 있으면 다시 회원가입 페이지로 돌아감
            Member member = Member.createMember(memberDto, passwordEncoder);
            // 엔티티에서 db에 저장
            memberService.saveMember(member);
        } catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "member/memberForm";
        }
        return "redirect:/";
    }

    // 로그인
    @GetMapping("/members/login")
    public String loginMember(){
        return "/member/memberLoginForm";
    }

    @GetMapping("/members/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg",
                "아이디와 비밀번호를 확인 해주세요");
        return "/member/memberLoginForm";
    }
}
