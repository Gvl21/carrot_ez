package com.morecommit.carrotEz.entity;

import com.morecommit.carrotEz.constant.ArticleStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class Article extends BaseEntity{
    @Id
    @Column(name = "article_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "article_title")
    private String title;

    @Column(name = "article_content")
    private String content;

    private ArticleStatus articleStatus;

    @Column(name = "article_category")
    private String category;

    @Column(name = "article_area")
    private String area;

}
