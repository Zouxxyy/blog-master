package com.zouxxyy.blog.core.dao;

import com.zouxxyy.blog.core.entity.Article;

import java.util.List;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer articleId);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer articleId);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);

    // 以上逆向工程生成的

    int getArticleCount();

    List<Article> getArticleByStartAndLimit(Integer start, Integer limit);

    int getArticleCountByCid(Integer categoryId);

    String getArticleTitleByAid(Integer articleId);
}