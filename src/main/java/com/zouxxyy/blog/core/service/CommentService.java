package com.zouxxyy.blog.core.service;

import com.zouxxyy.blog.core.util.PageResult;

public interface CommentService {

    /**
     * 获取评论数量
     * @return
     */
    int getCommentCount();

    /**
     * 获取评论列表
     * @param page
     * @param limit
     * @return
     */
    PageResult getCommentPage(Integer page, Integer limit);

    /**
     * 审核
     * @param ids
     * @return
     */
    Boolean check(Integer[] ids);

    /**
     * 回复
     * @param commentId
     * @param replyBody
     * @return
     */
    Boolean reply(Long commentId, String replyBody);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    Boolean deleteBatch(Integer[] ids);
}
