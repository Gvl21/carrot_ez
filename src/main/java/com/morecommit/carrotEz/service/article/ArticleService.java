package com.morecommit.carrotEz.service.article;

import com.morecommit.carrotEz.dto.request.board.ArticleRequestDto;
import com.morecommit.carrotEz.dto.response.board.ArticleResponseDto;
import org.springframework.http.ResponseEntity;

public interface ArticleService {
    ResponseEntity<? super ArticleResponseDto> saveArticle(ArticleRequestDto dto, String email);
}
