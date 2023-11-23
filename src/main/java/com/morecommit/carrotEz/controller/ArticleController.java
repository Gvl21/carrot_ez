package com.morecommit.carrotEz.controller;

import com.morecommit.carrotEz.dto.ArticleDto;
import com.morecommit.carrotEz.entity.Article;
import com.morecommit.carrotEz.entity.Member;
import com.morecommit.carrotEz.service.ArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping("/article/new")
    public ResponseEntity articleForm(@Valid @RequestBody ArticleDto articleDto,
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

            articleService.saveArticle(articleDto);

        } catch (IllegalStateException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("service error");
        }
        return ResponseEntity.status(HttpStatus.OK).body("저장 완료");
    }
}
