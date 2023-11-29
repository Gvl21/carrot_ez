package com.morecommit.carrotEz.entity;

import com.morecommit.carrotEz.dto.request.article.ArticleReplyRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity @NoArgsConstructor
@Getter @AllArgsConstructor
public class ArticleReply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private Long id;

    private Long articleId;

    private String content;

    public ArticleReply(ArticleReplyRequestDto dto, Long articleId) {
        this.articleId = articleId;
        this.content = dto.getContent();
    }
}
