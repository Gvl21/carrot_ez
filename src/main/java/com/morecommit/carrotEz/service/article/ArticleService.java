package com.morecommit.carrotEz.service.article;

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

}
