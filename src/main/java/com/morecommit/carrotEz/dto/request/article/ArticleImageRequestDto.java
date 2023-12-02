package com.morecommit.carrotEz.dto.request.article;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ArticleImageRequestDto {
    private String image;
    private Long articleId;
    private Long id;
}
