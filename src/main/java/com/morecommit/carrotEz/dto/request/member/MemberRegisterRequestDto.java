package com.morecommit.carrotEz.dto.request.member;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
public class MemberRegisterRequestDto {


    // 닉네임
    @NotBlank(message = "필수 입력 값 입니다.")
    private String nickname;

    // 이메일
     @Email(message = "이메일 형식으로 입력해주세요.")
     @NotEmpty
    private String email;

     // 비밀번호
    // Null 체크, 문자열 길이 0 체크
     @NotEmpty(message = "필수 입력 값 입니다.")
    // 최소 최대 길이 검사
     @Length(min = 4, max = 16, message = "최소 4자, 최대 16자로 입력해주세요")
    private String password;

     private String area;

     private String category;


}
