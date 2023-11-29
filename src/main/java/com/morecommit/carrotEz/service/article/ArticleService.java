package com.morecommit.carrotEz.service.article;

<<<<<<< HEAD
import com.morecommit.carrotEz.dto.ArticleDto;
import com.morecommit.carrotEz.entity.Article;
import com.morecommit.carrotEz.repository.ArticleRepository;
import com.morecommit.carrotEz.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@RequiredArgsConstructor
@Service
@Transactional
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;

    public Long saveArticle(ArticleDto articleDto) {
        Article article = articleDto.createArticle();
        articleRepository.save(article);
        return article.getId();
    }

=======
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
>>>>>>> 808a345302450730cb8721ee5730f5d470ad638c
}
