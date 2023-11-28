package com.morecommit.carrotEz.dto.response.article;

import com.morecommit.carrotEz.common.ResponseCode;
import com.morecommit.carrotEz.common.ResponseMessage;
import com.morecommit.carrotEz.dto.response.ResponseDto;
import com.morecommit.carrotEz.entity.Article;
import com.morecommit.carrotEz.entity.ArticleImage;
import com.morecommit.carrotEz.entity.Member;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class GetArticleResponseDto extends ResponseDto {
    private Long articleId;
    private String title;
    private String content;
    private List<String> articleImageList;
    private LocalDateTime regTime;
    private LocalDateTime updateTime;
    private String createdBy;
    private String nickname;
    private String memberImageUrl;
    public GetArticleResponseDto(String code, String message, Article article, List<ArticleImage> articleImageList, Member member) {
        super(code, message);

        this.articleId = article.getId();

    }

    public static ResponseEntity<? super GetArticleResponseDto> notExistBoard() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.NOT_EXISTED_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    public static ResponseEntity<? super GetArticleResponseDto> success(Article article, List<ArticleImage> articleImageList, Member member) {
        GetArticleResponseDto result = new GetArticleResponseDto(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, article, articleImageList, member);
        return null;
    }
}
