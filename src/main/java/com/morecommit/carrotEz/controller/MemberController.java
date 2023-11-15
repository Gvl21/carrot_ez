package com.morecommit.carrotEz.controller;

import com.morecommit.carrotEz.dto.MemberDto;
import com.morecommit.carrotEz.entity.Member;
import com.morecommit.carrotEz.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
//    private final PasswordEncoder passwordEncoder;

    @PostMapping("/members/new")
    public ResponseEntity memberForm(@Valid @RequestBody MemberDto memberDto,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder stringBuilder = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                String defaultMessage = fieldError.getDefaultMessage();
                stringBuilder.append(defaultMessage);
                System.out.println("바인딩");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(stringBuilder.toString());
        }
        try {
            // 에러 있으면 다시 회원가입 페이지로 돌아감
            Member member = Member.createMember(memberDto/*, passwordEncoder*/);

            // 엔티티에서 db에 저장
            memberService.saveMember(member);
        } catch (IllegalStateException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("service error");
        }
        return ResponseEntity.status(HttpStatus.OK).body("MEMBER");
    }


    @GetMapping("/members/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg",
                "아이디와 비밀번호를 확인 하라고오오오오오");
        return "/member/memberLoginForm";
    }
}
