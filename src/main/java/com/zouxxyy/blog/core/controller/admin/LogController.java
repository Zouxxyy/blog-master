package com.zouxxyy.blog.core.controller.admin;

import com.zouxxyy.blog.core.service.LogService;
import com.zouxxyy.blog.core.util.PageResult;
import com.zouxxyy.blog.core.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class LogController {

    @Resource
    private LogService logService;

    @GetMapping("/log")
    public String logPage(HttpServletRequest request) {
        request.setAttribute("path", "log");
        return "admin/log";
    }

    /**
     * 日志列表
     */
    @RequestMapping(value = "/log/list", method = RequestMethod.GET)
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {

        // 前端发送 page 当前页码， limit 每页个数
        int page = Integer.parseInt(params.get("page").toString());
        int limit = Integer.parseInt(params.get("limit").toString());

        PageResult pageResult = logService.getLogPage(page, limit);

        // 后台返回数据
        return new Result<>(200, "SUCCESS", pageResult);
    }
}
