package com.morecommit.carrotEz.dto.response.board;

import com.morecommit.carrotEz.common.ResponseCode;
import com.morecommit.carrotEz.common.ResponseMessage;
import com.morecommit.carrotEz.dto.response.ResponseDto;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class ArticleResponseDto extends ResponseDto {
    private ArticleResponseDto(){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }
    public static ResponseEntity<ArticleResponseDto> success(){
        ArticleResponseDto result = new ArticleResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    // 존재하지 않는 사용자
    public static ResponseEntity<ResponseDto> notExistUser(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);

    }

}
