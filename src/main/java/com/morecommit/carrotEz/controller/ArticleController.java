package com.morecommit.carrotEz.controller;

import com.morecommit.carrotEz.dto.request.board.ArticleRequestDto;
import com.morecommit.carrotEz.dto.response.board.ArticleResponseDto;
import com.morecommit.carrotEz.service.article.ArticleServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleServiceImpl articleServiceImpl;

    @PostMapping("/article/new")
    public ResponseEntity<? super ArticleResponseDto> newArticle(@Valid @RequestBody ArticleRequestDto dto,
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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(stringBuilder.toString());
        }
        try {
            // 엔티티에서 db에 저장
            ResponseEntity<? super ArticleResponseDto> response = articleServiceImpl.saveArticle(dto, email);
            return response;

        } catch (IllegalStateException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("service error");
        }
    }
}
