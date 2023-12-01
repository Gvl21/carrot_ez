package com.morecommit.carrotEz.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
// 이미지
@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ArticleImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long articleId;

    private String image;

    public ArticleImage(String image){
        this.image = image;
//        this.articleId = articleId;
    }

}
