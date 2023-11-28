package com.morecommit.carrotEz.service.article;

import com.morecommit.carrotEz.dto.request.article.ArticleRequestDto;
import com.morecommit.carrotEz.dto.response.article.ArticleResponseDto;
import com.morecommit.carrotEz.dto.response.article.GetArticleAllResponseDto;
import com.morecommit.carrotEz.dto.response.article.GetArticleResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ArticleService {
    ResponseEntity<? super ArticleResponseDto> saveArticle(ArticleRequestDto dto, String email, List<MultipartFile> file);
    ResponseEntity<? super ArticleResponseDto> saveArticle(ArticleRequestDto dto, String email);

    ResponseEntity<? super GetArticleAllResponseDto> getArticleList();

    ResponseEntity<? super GetArticleResponseDto> getArticle(Long ArticleId);
}
