package com.morecommit.carrotEz.service.article;

import com.morecommit.carrotEz.dto.request.article.ArticleReplyRequestDto;
import com.morecommit.carrotEz.dto.request.article.ArticleRequestDto;
import com.morecommit.carrotEz.dto.request.article.PatchArticleRequestDto;
import com.morecommit.carrotEz.dto.response.article.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ArticleService {
    ResponseEntity<? super ArticleResponseDto> saveArticle(ArticleRequestDto dto, String email, List<MultipartFile> file);
    ResponseEntity<? super ArticleResponseDto> saveArticle(ArticleRequestDto dto, String email);

    ResponseEntity<? super GetArticleAllResponseDto> getArticleList();

    ResponseEntity<? super GetArticleResponseDto> getArticle(Long ArticleId);

    ResponseEntity<? super ArticleReplyResponseDto> saveReply(ArticleReplyRequestDto dto, Long articleId, String email);

    ResponseEntity<? super GetArticleReplyResponseDto> getArticleReply(Long articleId);

    ResponseEntity<? super GetArticleAllResponseDto> getArticleListToMain();

    ResponseEntity<? super PatchArticleResponseDto> patchArticleUpdate(PatchArticleRequestDto requestBody, Long articleId, String email, List<MultipartFile>file);
}
