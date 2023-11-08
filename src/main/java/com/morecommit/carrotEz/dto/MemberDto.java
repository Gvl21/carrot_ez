package com.morecommit.carrotEz.dto;


import lombok.*;
@Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
public class MemberDto {

    // @NotBlank(message = "필수 입력 값 입니다.")
    private String name;

    // 값이 e-mail 형식인지 검사
    // @Email(message = "이메일 형식으로 입력해주세요.")
    // @NotEmpty
    private String email;

    // Null 체크, 문자열 길이 0 체크
    // @NotEmpty(message = "필수 입력 값 입니다.")
    // 최소 최대 길이 검사
    // @Length(min = 4, max = 16)
    private String password;


    // @NotEmpty(message = "필수 입력 값 입니다.")
    private String address;
}
