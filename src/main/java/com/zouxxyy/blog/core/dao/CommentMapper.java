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

    // 得到评论数量
    int getCommentCount();

    // 得到回复数量
    int getReplyCount();

    // 得到评论列表
    List<Comment> getCommentByStartAndLimit(Integer start, Integer limit);

    // 得到回复列表
    List<Comment> getReplyByStartAndLimit(Integer start, Integer limit);

    // 根据评论的ID找它的回复内容，这个目前存在漏洞，以后一条评论可以有多个回复， 暂时弃用
    String getCommentReplyByCommentId(Integer commentId);

    // 对id进行批量审核
    int checkByIds(Integer[] ids);

    // 根据pid返回评论，暂时弃用
    Comment getCommentByCommentPid(Long commentPid);

    void deleteByCommentId(Integer commentId);

    String getCommentContentByCommentId(Integer commentId);
}