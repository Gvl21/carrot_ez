package com.morecommit.carrotEz.service.article;

import com.morecommit.carrotEz.dto.request.board.ArticleRequestDto;
import com.morecommit.carrotEz.dto.response.board.ArticleResponseDto;
import com.morecommit.carrotEz.dto.response.board.GetArticleAllResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ArticleService {
    ResponseEntity<? super ArticleResponseDto> saveArticle(ArticleRequestDto dto, String email, List<MultipartFile> file);

    ResponseEntity<? super GetArticleAllResponseDto> getArticleList();
}
