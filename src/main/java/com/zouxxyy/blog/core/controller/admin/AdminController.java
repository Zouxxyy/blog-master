package com.zouxxyy.blog.core.controller.admin;

import com.zouxxyy.blog.core.entity.Comment;
import com.zouxxyy.blog.core.entity.User;
import com.zouxxyy.blog.core.service.*;
import com.zouxxyy.blog.core.util.PageResult;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


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

    @Resource
    private LogService logService;

    public static String LoginIp=""; // logIp

    // 登陆界面
    @RequestMapping({"/login"})
    public String login() {
        return  "admin/login";
    }

    // 输入用户名、密码登陆
    @PostMapping("/login")
    public String login(@RequestParam("userName") String userName,
                        @RequestParam("password") String passWord,
                        HttpSession session,
                        HttpServletRequest httpServletRequest) {
        if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(passWord)) {
            session.setAttribute("errorMsg", "用户或密码不能为空");
        }

        User user = userService.login(userName, passWord);
        if(user != null) {

            // 将昵称和id存入session域中，用于登陆拦截器
            session.setAttribute("loginUser", user.getUserNickname());
            session.setAttribute("loginUserId", user.getUserId());
            // 获取访问IP，用于日志记录
            LoginIp = httpServletRequest.getRemoteAddr();
            logService.addLog("登陆", user.getUserNickname() + " 登陆");
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
        request.setAttribute("categoryCount", categoryService.getCategoryCount());
        request.setAttribute("articleCount", articleService.getArticleCount());
        request.setAttribute("tagCount", tagService.getTagCount());
        request.setAttribute("commentCount", commentService.getCommentCount());

        PageResult commentPageResult = commentService.getCommentPage(1, 10);
        request.setAttribute("commentList", commentPageResult.getList());

        PageResult articlePageResult = articleService.getArticlePage(1, 10);
        request.setAttribute("articleList", articlePageResult.getList());

        return "admin/index";

    }

    // 进入修改密码页面
    @GetMapping("/profile")
    public String profile(HttpServletRequest request) {
        Integer loginUserId = (int) request.getSession().getAttribute("loginUserId");
        User user = userService.getUserByUserId(loginUserId);
        request.setAttribute("path", "profile");
        request.setAttribute("userName", user.getUserName());
        request.setAttribute("userNickName", user.getUserNickname());
        return "admin/profile";
    }

    // 修改名称和昵称
    @PostMapping("/profile/name")
    @ResponseBody
    public String nameUpdate(HttpServletRequest request, @RequestParam("userName") String userName,
                             @RequestParam("userNickName") String userNickName) {
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(userNickName)) {
            return "参数不能为空";
        }
        Integer loginUserId = (int) request.getSession().getAttribute("loginUserId");
        if (userService.updateName(loginUserId, userName, userNickName)) {
            logService.addLog("修改基本信息", "登陆名： " + userName +  " , 昵称： " + userNickName);
            return "success";
        } else {
            return "修改失败";
        }
    }

    // 修改密码
    @PostMapping("/profile/password")
    @ResponseBody
    public String passwordUpdate(HttpServletRequest request, @RequestParam("originalPassword") String originalPassword,
                                 @RequestParam("newPassword") String newPassword) {
        if (StringUtils.isEmpty(originalPassword) || StringUtils.isEmpty(newPassword)) {
            return "参数不能为空";
        }
        Integer loginUserId = (int) request.getSession().getAttribute("loginUserId");
        if (userService.updatePassword(loginUserId, originalPassword, newPassword)) {
            logService.addLog("修改密码", "新密码： " + newPassword);
            //修改成功后清空session中的数据，前端控制跳转至登录页
            request.getSession().removeAttribute("loginUserId");
            request.getSession().removeAttribute("loginUser");
            request.getSession().removeAttribute("errorMsg");
            return "success";
        } else {
            return "密码错误";
        }
    }

    // 安全退出
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("loginUserId");
        request.getSession().removeAttribute("loginUser");
        request.getSession().removeAttribute("errorMsg");
        return "admin/login";
    }
}
