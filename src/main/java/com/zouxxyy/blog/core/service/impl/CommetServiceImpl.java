package com.zouxxyy.blog.core.service.impl;

import com.zouxxyy.blog.core.dao.CommentMapper;
import com.zouxxyy.blog.core.entity.Comment;
import com.zouxxyy.blog.core.service.CommentService;
import com.zouxxyy.blog.core.util.PageResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class CommetServiceImpl implements CommentService {

    @Resource
    CommentMapper commentMapper;

    @Override
    public int getCommentCount() {
        return commentMapper.getCommentCount();
    }

    @Override
    public PageResult getCommentPage(Integer page, Integer limit) {
        List<Comment> commentList = commentMapper.getCommentByStartAndLimit((page - 1) * limit, limit);
        int count = commentMapper.getCommentCount(); // 获取总数
        PageResult pageResult = new PageResult(commentList, count, limit, page);
        return pageResult;
    }

    @Override
    public Boolean check(Integer[] ids) {
        return commentMapper.checkByIds(ids) > 0;
    }

    @Override
    public Boolean reply(Long commentId, String replyBody) {
        Comment replyComment = commentMapper.getCommentByCommentPid(commentId);
        if(replyComment == null) {
            // 为空就创建新回复
            Comment newReplyComment = new Comment();
            newReplyComment.setCommentContent(replyBody);
            newReplyComment.setCommentCreateTime(new Date());
            newReplyComment.setCommentPid(commentId);
            newReplyComment.setCommentStatus((byte)2);
            return commentMapper.insertSelective(newReplyComment) > 0;

        }
        else {
            // 不为空，修改即可
            replyComment.setCommentContent(replyBody);
            replyComment.setCommentCreateTime(new Date());
            return commentMapper.updateByPrimaryKeySelective(replyComment) > 0;
        }
    }

    @Override
    public Boolean deleteBatch(Integer[] ids) {
        for (Integer id : ids) {
            commentMapper.deleteByCommentId(id);
        }
        return true;
    }
}
