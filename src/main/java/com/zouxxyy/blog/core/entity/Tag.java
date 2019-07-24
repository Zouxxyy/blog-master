package com.zouxxyy.blog.core.entity;

public class Tag {
    private Integer tagId;

    private String tagName;

    // 该标签的文章数
    private Integer tagArticleCount;

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName == null ? null : tagName.trim();
    }

    public Integer getTagArticleCount() {
        return tagArticleCount;
    }

    public void setTagArticleCount(Integer tagArticleCount) {
        this.tagArticleCount = tagArticleCount;
    }
}