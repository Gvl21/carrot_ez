package com.morecommit.carrotEz.dto.response.article;

import com.morecommit.carrotEz.common.ArticleList;
import com.morecommit.carrotEz.common.ResponseCode;
import com.morecommit.carrotEz.common.ResponseMessage;
import com.morecommit.carrotEz.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetArticleAllResponseDto extends ResponseDto {

    private final List<ArticleList> articleList;

    private GetArticleAllResponseDto(String code, String message, List<ArticleList> articleListWithMemberInfo) {
        super(code, message);
        this.articleList = articleListWithMemberInfo;
    }

    public static ResponseEntity<GetArticleAllResponseDto> success(List<ArticleList> articleListWithMemberInfo) {
        GetArticleAllResponseDto result = new GetArticleAllResponseDto(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, articleListWithMemberInfo);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
