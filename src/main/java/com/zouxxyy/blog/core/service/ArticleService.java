package com.zouxxyy.blog.core.service;

import com.zouxxyy.blog.core.entity.Article;
import com.zouxxyy.blog.core.util.PageResult;

public interface ArticleService {

    int getArticleCount();

    PageResult getArticlePage(Integer page, Integer limit);

    Article getArticleById(Integer articleId);

    String saveArticle(Article article, String articleTags);

    Boolean deleteBatch(Integer[] ids);
}
