package com.morecommit.carrotEz.controller;

import com.morecommit.carrotEz.dto.request.article.ArticleReplyRequestDto;
import com.morecommit.carrotEz.dto.request.article.ArticleRequestDto;
import com.morecommit.carrotEz.dto.response.ResponseDto;
import com.morecommit.carrotEz.dto.response.article.*;
import com.morecommit.carrotEz.entity.ArticleReply;
import com.morecommit.carrotEz.service.article.ArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;


    // 게시글 생성 api
    @PostMapping("/article/new")
    public ResponseEntity<? super ArticleResponseDto> newArticle(@Valid @ModelAttribute ArticleRequestDto dto,
                                                                 @RequestPart(value = "articleImageList", required = false) List<MultipartFile> file,
                                                                  @AuthenticationPrincipal String email,
                                                                  BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            StringBuilder stringBuilder = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                String defaultMessage = fieldError.getDefaultMessage();
                stringBuilder.append(defaultMessage);
                System.out.println("바인딩");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("바인딩 과정 에러");
        }
        try {
            // 멀티파트 파일의 배열이 비어있거나 null인 경우에 이미지가 없다고 간주하고 서비스의 다른 메소드로 유도
            if (file == null || file.isEmpty()){
                return articleService.saveArticle(dto, email);
            }
            // 엔티티에서 db에 저장
            return articleService.saveArticle(dto, email, file);

        } catch (IllegalStateException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("service error");
        }
    }

    @GetMapping("/article/{articleId}/reply")
    public ResponseEntity<? super GetArticleReplyResponseDto> getArticleReply(
            @PathVariable("articleId") Long articleId){
        return articleService.getArticleReply(articleId);
    }
    @PostMapping("/article/{articleId}/reply")
    public ResponseEntity<? super ArticleReplyResponseDto> articleReply(
            @RequestBody @Valid ArticleReplyRequestDto dto,
            @PathVariable("articleId") Long articleId,
            @AuthenticationPrincipal String email){
        return articleService.saveReply(dto, articleId, email);
    }


    @GetMapping("/article/findFriend-list")
    public ResponseEntity<? super GetArticleAllResponseDto> getArticleList(){
        return articleService.getArticleList();
    }

    @GetMapping("/article/main-list")
    public ResponseEntity<? super GetArticleAllResponseDto> getArticleListToMain(){
        return articleService.getArticleListToMain();
    }

    @GetMapping("/article/{articleId}")
    public ResponseEntity<? super GetArticleResponseDto> getArticle(
            @PathVariable("articleId") Long articleId){
        return articleService.getArticle(articleId);
    }

    @PatchMapping("/article/{articleId}/update")
    public ResponseEntity<? super PatchArticleResponseDto> patchArticleUpdate(
            @RequestBody @Valid PatchArticleResponseDto requestBody,
            @PathVariable("articleId") Integer articleId,
            @AuthenticationPrincipal String email
    ){
        ResponseEntity<? super PatchArticleResponseDto> response = articleService.patchArticleUpdate(requestBody, articleId, email);
        return articleService.patchArticleUpdate(requestBody, articleId, email);
    }




}
