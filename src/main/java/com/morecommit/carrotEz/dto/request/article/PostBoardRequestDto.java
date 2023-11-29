package com.morecommit.carrotEz.dto.request.article;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @NoArgsConstructor
public class PostBoardRequestDto {

    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotNull
    private List<String> boardImageList;
    // 빈 배열 가능, 해당 필드는 반드시 존재 해야 함


}
