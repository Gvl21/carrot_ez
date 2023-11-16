package com.morecommit.carrotEz.repository;

import com.morecommit.carrotEz.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}
