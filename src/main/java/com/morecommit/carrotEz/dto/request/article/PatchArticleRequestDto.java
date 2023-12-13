package com.morecommit.carrotEz.dto.request.article;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
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

    @Nullable
    private List<ArticleImageRequestDto> imageUrls;

    @NotBlank
    private String category;

    @NotBlank
    private String area;
}
