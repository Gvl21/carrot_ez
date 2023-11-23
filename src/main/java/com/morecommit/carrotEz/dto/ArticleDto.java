package com.morecommit.carrotEz.dto;

import com.morecommit.carrotEz.entity.Article;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class  ArticleDto {
    @NotBlank(message = "필수 입력 값 입니다.")
    private String title;

    @NotBlank(message = "필수 입력 값 입니다.")
    private String content;


    private String category;


    private String area;

    private static ModelMapper modelMapper = new ModelMapper();


    // 게시글 생성 메서드 Dto -> entity 두 객체간 매핑
    public Article createArticle() {
        Article article = modelMapper.map(this, Article.class);
        // true시 모집중이라는 뜻 <- 한번에 이해가 힘들긴하다 ENUM이 나으려나
        article.setArticleStatus(true);
        return article;
    }

    // 게시글 수정 시에 사용할 메서드 entity -> Dto
    public  ArticleDto of(Article article) {return modelMapper.map(article, ArticleDto.class);}




}
