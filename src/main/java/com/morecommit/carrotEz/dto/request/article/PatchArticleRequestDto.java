package com.morecommit.carrotEz.dto.request.article;

import com.morecommit.carrotEz.entity.ArticleImage;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PatchArticleRequestDto {
    @NotBlank
    private String title;
    @NotBlank
    private String content;

    @NotNull
    private List<ArticleImageRequestDto> imageUrls;

    @NotBlank
    private String category;

    @NotBlank
    private String area;
}
