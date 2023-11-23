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

    private final String email;
    private final String nickname;
    private final String memberImageUrl;

    public GetMemberResponseDto(Member member) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.email = member.getEmail();
        this.nickname = member.getNickname();
        this.memberImageUrl = member.getMemberImageUrl();
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
