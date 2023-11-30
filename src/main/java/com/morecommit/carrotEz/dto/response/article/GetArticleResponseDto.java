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

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
public class GetArticleResponseDto extends ResponseDto {
    private final Long articleId;
    private final String title;
    private final String content;
    private final String area;
    private final List<ArticleImage> articleImageList;
    private final String regTime;
    private final String updateTime;
    private final String createdBy;
    private final String nickname;
    private final int replyCount;
    private final String memberImageUrl;
    public GetArticleResponseDto(String code, String message, Article article, List<ArticleImage> articleImageList, Member member) {
        super(code, message);

        this.articleId = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.area = article.getArea();
        this.replyCount = article.getReplyCount();
        this.articleImageList = articleImageList;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.regTime = article.getRegTime().format(formatter);
        this.updateTime = article.getUpdateTime().format(formatter);
        this.createdBy = article.getCreatedBy();
        this.nickname = member.getNickname();
        this.memberImageUrl = member.getMemberImageUrl();
    }

    public static ResponseEntity<? super GetArticleResponseDto> notExistBoard() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.NOT_EXISTED_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    public static ResponseEntity<? super GetArticleResponseDto> success(Article article, List<ArticleImage> articleImageList, Member member) {
        GetArticleResponseDto result = new GetArticleResponseDto(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, article, articleImageList, member);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
