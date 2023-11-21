package com.morecommit.carrotEz.dto.member;

import com.morecommit.carrotEz.common.ResponseCode;
import com.morecommit.carrotEz.common.ResponseMessage;
import com.morecommit.carrotEz.dto.response.ResponseDto;
import com.morecommit.carrotEz.entity.Member;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class GetMemberResponseDto extends ResponseDto {

    private String email;
    private String nickname;
//    private String profileImage;

    public GetMemberResponseDto(Member member) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.email = member.getEmail();
        this.nickname = member.getNickname();
//        this.profileImage = member.getProfileImage();
    }

    public static ResponseEntity<GetMemberResponseDto> success(Member member){
        GetMemberResponseDto result = new GetMemberResponseDto(member);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
public static ResponseEntity<ResponseDto> notExistUser(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
}
}
