package com.morecommit.carrotEz.controller;

import com.morecommit.carrotEz.dto.response.member.GetMemberDetailDto;
import com.morecommit.carrotEz.dto.response.member.GetMemberResponseDto;
import com.morecommit.carrotEz.dto.request.member.MemberRegisterRequestDto;
import com.morecommit.carrotEz.dto.request.member.MemberSignInRequestDto;
import com.morecommit.carrotEz.dto.response.member.MemberSignInResponseDto;
import com.morecommit.carrotEz.entity.Member;
import com.morecommit.carrotEz.service.member.MemberService;
import com.morecommit.carrotEz.service.file.FileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final FileService fileService;

    @PostMapping("/members/new")
    public ResponseEntity memberForm(@Valid @ModelAttribute MemberRegisterRequestDto memberRegisterRequestDto,
                                     @RequestPart(value = "profileImage", required = false) MultipartFile file,
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
            Member member = Member.createMember(memberRegisterRequestDto, passwordEncoder);


            // 엔티티에서 db에 저장
            memberService.saveMember(member, file);
        } catch (IllegalStateException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("service error");
        }
        return ResponseEntity.status(HttpStatus.OK).body("MEMBER");
    }

    @PostMapping("/members/new/image")
    public String upload(@RequestParam("file") MultipartFile file) {
        String url = fileService.upload(file);
        return url;
    }

    @PostMapping("/members/signIn")
    public ResponseEntity<? super MemberSignInResponseDto> signInForm(@Valid @RequestBody MemberSignInRequestDto requestBody) {
        ResponseEntity<? super MemberSignInResponseDto> response = memberService.signIn(requestBody);
        return response;
    }


    // 유저 정보 받아오기
    @GetMapping("/members/info")
    public ResponseEntity<? super GetMemberResponseDto> getSignInMember(
            @AuthenticationPrincipal String email
    ){
        ResponseEntity<? super GetMemberResponseDto> response = memberService.getSignInMember(email);
        return response;
    }

    @GetMapping("members/{email}")
    public ResponseEntity<? super GetMemberDetailDto> getMemberDetail(
            @PathVariable("email") String email){
        return memberService.getMemberDetail(email);
    }

}
