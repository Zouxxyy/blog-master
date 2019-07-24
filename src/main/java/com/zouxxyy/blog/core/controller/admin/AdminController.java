package com.zouxxyy.blog.core.controller.admin;

import com.zouxxyy.blog.core.entity.User;
import com.zouxxyy.blog.core.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private UserService userService;

    @Resource
    private CategoryService categoryService;

    @Resource
    private TagService tagService;

    @Resource
    private ArticleService articleService;

    @Resource
    private CommentService commentService;

    // 登陆界面
    @RequestMapping({"/login"})
    public String login() {
        return  "admin/login";
    }

    // 输入用户名、密码登陆
    @PostMapping("/login")
    public String login(@RequestParam("userName") String userName,
                        @RequestParam("password") String passWord,
                        HttpSession session) {
        if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(passWord)) {
            session.setAttribute("errorMsg", "用户或密码不能为空");
        }

        User user = userService.login(userName, passWord);
        if(user != null) {
            // 将昵称和id存入session域中
            session.setAttribute("loginUser", user.getUserNickname());
            session.setAttribute("loginUserId", user.getUserId());
            // 登陆成功，进入首页
            return "redirect:/admin/index";
        }
        else {
            session.setAttribute("errorMsg", "用户名或者密码错误");
            return "admin/login";
        }
    }

    // 管理台首页，显示各种数据的统计信息
    @GetMapping({"", "/", "/index", "/index.html"})
    public String index(HttpServletRequest request) {

        request.setAttribute("path", "index");
//        request.setAttribute("categoryCount", categoryService.getTotalCategories());
//        request.setAttribute("blogCount", blogService.getTotalBlogs());
//        request.setAttribute("linkCount", linkService.getTotalLinks());
//        request.setAttribute("tagCount", tagService.getTotalTags());
//        request.setAttribute("commentCount", CommentService.getTotalComments());
        request.setAttribute("categoryCount", categoryService.getCategoryCount());
        request.setAttribute("articleCount", articleService.getArticleCount());
        request.setAttribute("tagCount", tagService.getTagCount());
        request.setAttribute("commentCount", commentService.getCommentCount());

        return "admin/index";

    }
}
