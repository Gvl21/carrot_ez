package com.morecommit.carrotEz.dto.response.article;

import com.morecommit.carrotEz.common.ReplyListItem;
import com.morecommit.carrotEz.common.ResponseCode;
import com.morecommit.carrotEz.common.ResponseMessage;
import com.morecommit.carrotEz.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetArticleReplyResponseDto extends ResponseDto {
    private final List<ReplyListItem> replyList;
    public GetArticleReplyResponseDto(String code, String message, List<ReplyListItem> replyList) {
        super(code, message);
        this.replyList = replyList;
    }
    public static ResponseEntity<GetArticleReplyResponseDto> success(List<ReplyListItem> replyList) {
        GetArticleReplyResponseDto result = new GetArticleReplyResponseDto(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, replyList);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<? super GetArticleReplyResponseDto> notExistBoard() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.NOT_EXISTED_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

}
