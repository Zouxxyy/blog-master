package com.zouxxyy.blog.core.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class Article {
    private Integer articleId;

    private Integer articleUserId;

    private Integer articleCategoryId;

    private String articleTitle;

    private Long articleViewCount;

    private Integer articleCommentCount;

    private Integer articleLikeCount;

    private Byte articleStatus;

    private Byte articleEnableComment;

    // 返回json数据日期格式设定
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date articleUpdateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date articleCreateTime;

    private String articleContent;

    // 以下是新加的

    private List<Tag> tagList;

    private Category articleCategory;





    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getArticleUserId() {
        return articleUserId;
    }

    public void setArticleUserId(Integer articleUserId) {
        this.articleUserId = articleUserId;
    }

    public Integer getArticleCategoryId() {
        return articleCategoryId;
    }

    public void setArticleCategoryId(Integer articleCategoryId) {
        this.articleCategoryId = articleCategoryId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle == null ? null : articleTitle.trim();
    }

    public Long getArticleViewCount() {
        return articleViewCount;
    }

    public void setArticleViewCount(Long articleViewCount) {
        this.articleViewCount = articleViewCount;
    }

    public Integer getArticleCommentCount() {
        return articleCommentCount;
    }

    public void setArticleCommentCount(Integer articleCommentCount) {
        this.articleCommentCount = articleCommentCount;
    }

    public Integer getArticleLikeCount() {
        return articleLikeCount;
    }

    public void setArticleLikeCount(Integer articleLikeCount) {
        this.articleLikeCount = articleLikeCount;
    }

    public Byte getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(Byte articleStatus) {
        this.articleStatus = articleStatus;
    }

    public Byte getArticleEnableComment() {
        return articleEnableComment;
    }

    public void setArticleEnableComment(Byte articleEnableComment) {
        this.articleEnableComment = articleEnableComment;
    }

    public Date getArticleUpdateTime() {
        return articleUpdateTime;
    }

    public void setArticleUpdateTime(Date articleUpdateTime) {
        this.articleUpdateTime = articleUpdateTime;
    }

    public Date getArticleCreateTime() {
        return articleCreateTime;
    }

    public void setArticleCreateTime(Date articleCreateTime) {
        this.articleCreateTime = articleCreateTime;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent == null ? null : articleContent.trim();
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public Category getArticleCategory() {
        return articleCategory;
    }

    public void setArticleCategory(Category articleCategory) {
        this.articleCategory = articleCategory;
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleId=" + articleId +
                ", articleUserId=" + articleUserId +
                ", articleCategoryId=" + articleCategoryId +
                ", articleTitle='" + articleTitle + '\'' +
                ", articleViewCount=" + articleViewCount +
                ", articleCommentCount=" + articleCommentCount +
                ", articleLikeCount=" + articleLikeCount +
                ", articleStatus=" + articleStatus +
                ", articleEnableComment=" + articleEnableComment +
                ", articleUpdateTime=" + articleUpdateTime +
                ", articleCreateTime=" + articleCreateTime +
                ", articleContent='" + articleContent + '\'' +
                ", tagList=" + tagList +
                ", articleCategory=" + articleCategory +
                '}';
    }
}