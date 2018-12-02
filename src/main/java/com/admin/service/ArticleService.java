package com.admin.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.entity.Article;
import com.admin.repository.ArticleRepository;

@Service
public class ArticleService {
	@Autowired
	private ArticleRepository articleRepository;
	
	public Map<String, Object> getAllArticles() {
		Map<String, Object> map = new HashMap<>();
		List<Article> articles = articleRepository.findAll();
		for (Article article : articles) {
			map.put(article.getArticleId(), article);
		}
		return map;
	}
}
