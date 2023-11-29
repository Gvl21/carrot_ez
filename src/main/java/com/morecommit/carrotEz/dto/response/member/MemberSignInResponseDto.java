package com.morecommit.carrotEz.dto.response.member;

import com.morecommit.carrotEz.common.ResponseCode;
import com.morecommit.carrotEz.common.ResponseMessage;
import com.morecommit.carrotEz.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class MemberSignInResponseDto extends ResponseDto {
    private String token;
    private int expirationTime;
    public MemberSignInResponseDto(String token) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.token = token;
        this.expirationTime = 3600;
    }
    public static ResponseEntity<MemberSignInResponseDto> success(String token){
        MemberSignInResponseDto result = new MemberSignInResponseDto(token);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    public static ResponseEntity<ResponseDto> signInFailed(){
        ResponseDto result = new ResponseDto(ResponseCode.SIGN_IN_FAIL, ResponseMessage.SIGN_IN_FAIL);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

}
