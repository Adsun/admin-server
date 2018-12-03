package com.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.admin.constant.ResultConstant;
import com.admin.entity.Article;
import com.admin.service.ArticleService;

@RestController
@RequestMapping("/article")
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	
	@GetMapping
	public ResultConstant getArticle(@RequestParam Integer number, @RequestParam Integer size) {
		return ResultConstant.ofSuccess(articleService.getArticle(number-1, size));
	}
	
	@PostMapping
	public ResultConstant editArticle(@RequestBody Article article) {
		if (article.getId() != null) {
			articleService.updateArticle(article);
		} else {
			articleService.addArticle(article);
		}
		return ResultConstant.ofSuccess();
	}
	@DeleteMapping
	public ResultConstant deleteArticle(Long id) {
		articleService.deleteArticle(id);
		return ResultConstant.ofSuccess();
	}
}
