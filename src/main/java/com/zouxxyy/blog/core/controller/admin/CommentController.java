package com.zouxxyy.blog.core.controller.admin;

import com.zouxxyy.blog.core.service.CommentService;
import com.zouxxyy.blog.core.util.PageResult;
import com.zouxxyy.blog.core.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class CommentController {

    @Resource
    CommentService commentService;

    // 评论管理页面
    @GetMapping("/comments")
    public String list(HttpServletRequest request) {
        request.setAttribute("path", "comments");
        return "admin/comment";
    }

    @GetMapping("/comments/list")
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {

        // 前端发送 page 当前页码， limit 每页个数
        int page = Integer.parseInt(params.get("page").toString());
        int limit = Integer.parseInt(params.get("limit").toString());

        PageResult pageResult = commentService.getCommentPage(page, limit);

        // 后台返回数据
        return new Result<>(200, "SUCCESS", pageResult);
    }

    // 批量审核
    @PostMapping("/comments/checkDone")
    @ResponseBody
    public Result checkDone(@RequestBody Integer[] ids) {

        if (ids.length < 1) {
            return new Result<>(500, "参数异常！", null);
        }
        if (commentService.check(ids)) {
            return new Result<>(200, "SUCCESS", null);
        } else {
            return new Result<>(500, "删除失败！", null);
        }
    }

    @PostMapping("/comments/reply")
    @ResponseBody
    public Result checkDone(@RequestParam("commentId") Long commentId,
                            @RequestParam("replyBody") String replyBody) {
        if (commentId == null || commentId < 1 || StringUtils.isEmpty(replyBody)) {
            return new Result<>(500, "参数异常！", null);
        }
        if (commentService.reply(commentId, replyBody)) {
            return new Result<>(200, "SUCCESS", null);
        } else {
            return new Result<>(500, "删除失败！", null);
        }
    }

    @PostMapping("/comments/delete")
    @ResponseBody
    public Result delete(@RequestBody Integer[] ids) {
        if (ids.length < 1) {
            return new Result<>(500, "参数异常！", null);
        }
        if (commentService.deleteBatch(ids)) {
            return new Result<>(200, "SUCCESS", null);
        } else {
            return new Result<>(500, "删除失败！", null);
        }
    }

}
