package com.admin.repository;

import org.springframework.stereotype.Repository;

import com.admin.entity.Article;

@Repository
public interface ArticleRepository extends BaseRepository<Article, Long> {
   
}
