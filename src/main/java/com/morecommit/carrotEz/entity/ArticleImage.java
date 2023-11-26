package com.morecommit.carrotEz.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @NoArgsConstructor @AllArgsConstructor
public class ArticleImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long articleNumber;

    private String image;

    public ArticleImage(Long articleNumber, String image){
        this.articleNumber = articleNumber;
        this.image = image;
    }

}
