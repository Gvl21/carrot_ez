package com.morecommit.carrotEz.dto.request.article;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
public class ArticleReplyRequestDto {
    @NotBlank(message = "필수 입력 값 입니다.")
    private String content;
}
