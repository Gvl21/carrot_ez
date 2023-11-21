package com.morecommit.carrotEz.service;

import com.morecommit.carrotEz.dto.ArticleDto;
import com.morecommit.carrotEz.entity.Article;
import com.morecommit.carrotEz.entity.Member;
import com.morecommit.carrotEz.repository.ArticleRepository;
import com.morecommit.carrotEz.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
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

    // 게시글 수정
   // public Long updateArticle(ArticleDto articleDto){
        // 게시글 조회
    //    Article article = articleRepository.findAllByTitle(articleDto.getTitle());
   //     return article.getId();
   // }


    // 유효성 검사(로그인 회원 DB회원)
public boolean validateMember(Long ArticleId, String email){
    Member member = memberRepository.findByEmail(email);
    Article article = articleRepository.findBy()
}
