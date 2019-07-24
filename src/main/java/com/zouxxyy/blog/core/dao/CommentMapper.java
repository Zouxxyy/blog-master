package com.zouxxyy.blog.core.dao;

import com.zouxxyy.blog.core.entity.Comment;

import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(Long commentId);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Long commentId);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    //

    int getCommentCount();

    List<Comment> getCommentByStartAndLimit(Integer start, Integer limit);

    // 根据评论的ID找它的回复内容
    String getCommentReplyByCommentId(Integer commentId);

    int checkByIds(Integer[] ids);

    Comment getCommentByCommentPid(Long commentPid);

    void deleteByCommentId(Integer commentId);
}