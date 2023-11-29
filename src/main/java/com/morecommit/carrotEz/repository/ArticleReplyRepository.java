package com.morecommit.carrotEz.repository;

import com.morecommit.carrotEz.entity.ArticleReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleReplyRepository extends JpaRepository<ArticleReply, Long> {
    List<ArticleReply> findByArticleIdOrderByRegTimeAsc(Long articleId);
}
