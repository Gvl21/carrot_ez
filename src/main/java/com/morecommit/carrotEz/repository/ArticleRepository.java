package com.morecommit.carrotEz.repository;

import com.morecommit.carrotEz.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    // 멤버 아이디로 게시글을 찾는 쿼리 메소드
    //Article findByArticleId(Long articleId);

    Article findByTitle(String title);
    //Article findAllById(String title);

    List<Article> findAll();

    List<Article> findByOrderByRegTimeDesc();
}
