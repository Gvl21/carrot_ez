package com.morecommit.carrotEz.entity;

import com.morecommit.carrotEz.dto.ArticleDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity @Getter @Setter @ToString
@NoArgsConstructor

public class Article extends BaseEntity{
    @Id
    @Column(name = "article_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "article_title")
    private String title;

    @Column(name = "article_content")
    private String content;

    private boolean articleStatus;

    @Column(name = "article_category")
    private String category;

    @Column(name = "article_area")
    private String area;

    // 영속성 컨텍스트 변경감지기능 활용 : 트랜잭션 종료시 업데이트 쿼리 수행
    public void updateArticle(ArticleDto articleDto){


    }
}
