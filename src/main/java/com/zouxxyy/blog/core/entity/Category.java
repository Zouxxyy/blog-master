package com.zouxxyy.blog.core.entity;

public class Category {
    private Integer categoryId;

    private String categoryName;

    // 该分类的文章数
    private Integer categoryArticleCount;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public Integer getCategoryArticleCount() {
        return categoryArticleCount;
    }

    public void setCategoryArticleCount(Integer categoryArticleCount) {
        this.categoryArticleCount = categoryArticleCount;
    }

}