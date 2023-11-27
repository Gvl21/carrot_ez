package com.morecommit.carrotEz.dto.request.board;

import com.morecommit.carrotEz.entity.Article;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ArticleRequestDto {
    @NotBlank(message = "필수 입력 값 입니다.")
    private String title;

    @NotBlank(message = "필수 입력 값 입니다.")
    private String content;

    private String category;

    private String area;

    @NotNull
    private List<MultipartFile> articleImageList;

    private static ModelMapper modelMapper = new ModelMapper();


    // 게시글 생성 메서드 Dto -> entity 두 객체간 매핑
    public Article createArticle() {
        Article article = modelMapper.map(this, Article.class);
        article.setArticleStatus(true);
        return article;
    }

    // 게시글 수정 시에 사용할 메서드 entity -> Dto
    public ArticleRequestDto of(Article article) {return modelMapper.map(article, ArticleRequestDto.class);}




}
