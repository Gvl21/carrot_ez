package com.morecommit.carrotEz.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class SignInResponseDto {
    private String token;
    private int expirationTime;

    private SignInResponseDto(String token){
        this.token = token;
        this.expirationTime = 3600;

    }
    public static ResponseEntity<SignInResponseDto> success(String token){
        SignInResponseDto result = new SignInResponseDto(token);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 로그인 시도시 실패했을 때 보낼 body
    public static ResponseEntity failed() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("이메일 혹은 비밀번호가 일치하지 않습니다. 다시 제출해주세요.");
    }
}
