package com.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.admin.entity.Article;
import com.admin.repository.ArticleRepository;
import com.admin.repository.ConstantRepository;

@Service
public class ArticleService {
	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private ConstantRepository constantRepository;
	
	@Transactional
	public Map<String, Object> getAllArticles() {
		Map<String, Object> map = new HashMap<>();
		List<Article> articles = articleRepository.findAll();
		for (Article article : articles) {
			map.put(article.getArticleId(), article);
		}
		return map;
	}
	
	@Transactional
	public Page<Article> getArticle(Integer page, Integer size) {
		Page<Article> articles= articleRepository.findAll(PageRequest.of(page, size));
		for (Article article : articles.getContent()) {
			article.setArticleName(constantRepository.findByConstantId(article.getArticleId()).get(0).getConstantName());
		}
		return articles;
	}
	
	@Transactional
	public void updateArticle(Article article) {
		Article tbArticle = articleRepository.getOne(article.getId());
		tbArticle.setArticleContent(article.getArticleContent());
		tbArticle.setContentUrl(article.getContentUrl());
		articleRepository.updateEntity(tbArticle);
	}
	
	@Transactional
	public void deleteArticle(Long id) {
		articleRepository.deleteById(id);
	}
	
	@Transactional
	public void addArticle(Article article) {
		articleRepository.createEntity(article);
	}
	
}
