package com.morecommit.carrotEz.repository;

import com.morecommit.carrotEz.entity.ArticleImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleImageRepository extends JpaRepository<ArticleImage, Long> {

    List<ArticleImage> findByArticleId(Long articleId);
    List<ArticleImage> deleteByArticleId(Long articleId);

}
