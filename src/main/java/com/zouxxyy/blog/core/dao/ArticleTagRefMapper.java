package com.zouxxyy.blog.core.dao;

import com.zouxxyy.blog.core.entity.ArticleTagRef;

public interface ArticleTagRefMapper {
    int deleteByPrimaryKey(ArticleTagRef key);

    int insert(ArticleTagRef record);

    int insertSelective(ArticleTagRef record);

    int deleteByArticleID(Integer articleId);

    int deleteByTagIds(Integer[] tagIds);

    int getArticleCountByTid(Integer tagId);
}