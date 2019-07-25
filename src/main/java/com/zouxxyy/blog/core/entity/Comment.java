package com.zouxxyy.blog.core.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Comment {
    private Long commentId;

    private Long commentPid;

    private Long commentArticleId;

    private String commentAuthorName;

    private String commentAuthorEmail;

    private String commentAuthorIp;

    private String commentContent;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date commentCreateTime;

    private Byte commentStatus;

    private Integer commentLikeCount;

    // 以下新加的

    private String commentReplyContent;

    private String commentArticleTitle;

    private String commentPContent; // 父内容


    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getCommentPid() {
        return commentPid;
    }

    public void setCommentPid(Long commentPid) {
        this.commentPid = commentPid;
    }

    public Long getCommentArticleId() {
        return commentArticleId;
    }

    public void setCommentArticleId(Long commentArticleId) {
        this.commentArticleId = commentArticleId;
    }

    public String getCommentAuthorName() {
        return commentAuthorName;
    }

    public void setCommentAuthorName(String commentAuthorName) {
        this.commentAuthorName = commentAuthorName == null ? null : commentAuthorName.trim();
    }

    public String getCommentAuthorEmail() {
        return commentAuthorEmail;
    }

    public void setCommentAuthorEmail(String commentAuthorEmail) {
        this.commentAuthorEmail = commentAuthorEmail == null ? null : commentAuthorEmail.trim();
    }

    public String getCommentAuthorIp() {
        return commentAuthorIp;
    }

    public void setCommentAuthorIp(String commentAuthorIp) {
        this.commentAuthorIp = commentAuthorIp == null ? null : commentAuthorIp.trim();
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent == null ? null : commentContent.trim();
    }

    public Date getCommentCreateTime() {
        return commentCreateTime;
    }

    public void setCommentCreateTime(Date commentCreateTime) {
        this.commentCreateTime = commentCreateTime;
    }

    public Byte getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(Byte commentStatus) {
        this.commentStatus = commentStatus;
    }

    public Integer getCommentLikeCount() {
        return commentLikeCount;
    }

    public void setCommentLikeCount(Integer commentLikeCount) {
        this.commentLikeCount = commentLikeCount;
    }

    public String getCommentReplyContent() {
        return commentReplyContent;
    }

    public void setCommentReplyContent(String commentReplyContent) {
        this.commentReplyContent = commentReplyContent;
    }

    public String getCommentArticleTitle() {
        return commentArticleTitle;
    }

    public void setCommentArticleTitle(String commentArticleTitle) {
        this.commentArticleTitle = commentArticleTitle;
    }

    public String getCommentPContent() {
        return commentPContent;
    }

    public void setCommentPContent(String commentPContent) {
        this.commentPContent = commentPContent;
    }
}