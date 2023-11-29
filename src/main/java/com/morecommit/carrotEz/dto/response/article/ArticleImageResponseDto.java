package com.morecommit.carrotEz.dto.response.article;

import com.morecommit.carrotEz.entity.ArticleImage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter  @NoArgsConstructor
public class ArticleImageResponseDto {
    private Long id;

    private Long articleId;

    private String image;

    public static ArticleImageResponseDto fromEntity(ArticleImage articleImage) {
        ArticleImageResponseDto dto = new ArticleImageResponseDto();
        dto.setId(articleImage.getId());
        dto.setArticleId(articleImage.getArticleId());
        dto.setImage(articleImage.getImage());
        return dto;
    }

    public static List<ArticleImageResponseDto> fromEntityList(List<ArticleImage> articleImages) {
        List<ArticleImageResponseDto> dto = new ArrayList<>();
        for (ArticleImage articleImage : articleImages) {
            dto.add(fromEntity(articleImage));
        }
        return dto;
    }
}
