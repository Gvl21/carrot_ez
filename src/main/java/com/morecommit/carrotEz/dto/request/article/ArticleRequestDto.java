package com.morecommit.carrotEz.dto.request.article;

import com.morecommit.carrotEz.entity.Article;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ArticleRequestDto {
    @NotBlank(message = "필수 입력 값 입니다.")
    private String title;

    @NotBlank(message = "필수 입력 값 입니다.")
    private String content;

    private String category;

    private String area;


    private List<MultipartFile> articleImageList = new ArrayList<>();




    // 게시글 생성 메서드 Dto -> entity 두 객체간 매핑
    public static Article createArticle(ArticleRequestDto dto) {
        Article article = new Article();
        article.setTitle(dto.getTitle());
        article.setContent(dto.getContent());
        article.setArea(dto.getArea());
        article.setCategory(dto.getCategory());
        article.setArticleStatus(true);
        article.setReplyCount(0);
        return article;
    }

}
