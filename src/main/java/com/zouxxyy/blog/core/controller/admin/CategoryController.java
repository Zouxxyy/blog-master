package com.zouxxyy.blog.core.controller.admin;

import com.zouxxyy.blog.core.service.CategoryService;
import com.zouxxyy.blog.core.service.LogService;
import com.zouxxyy.blog.core.util.PageResult;
import com.zouxxyy.blog.core.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @Resource
    private LogService logService;

    @GetMapping("/categories")
    public String categoryPage(HttpServletRequest request) {
        request.setAttribute("path", "categories");
        return "admin/category";
    }

    /**
     * 分类列表
     */
    @RequestMapping(value = "/categories/list", method = RequestMethod.GET)
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {

        // 前端发送 page 当前页码， limit 每页个数
        int page = Integer.parseInt(params.get("page").toString());
        int limit = Integer.parseInt(params.get("limit").toString());

        PageResult pageResult = categoryService.getCategoryPage(page, limit);

        // 后台返回数据
        return new Result<>(200, "SUCCESS", pageResult);
    }

    /**
     * 分类添加
     */
    @RequestMapping(value = "/categories/save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(@RequestParam("categoryName") String categoryName) {
        if (StringUtils.isEmpty(categoryName)) {
            return new Result<>(500, "请输入分类名称！", null);
        }
        if (categoryService.saveCategory(categoryName)) {
            logService.addLog("添加分类", categoryName);
            return new Result<>(200, "SUCCESS", null);
        } else {
            return new Result<>(500, "分类名称重复", null);
        }
    }

    /**
     * 分类修改
     */
    @RequestMapping(value = "/categories/update", method = RequestMethod.POST)
    @ResponseBody
    public Result update(@RequestParam("categoryId") Integer categoryId,
                         @RequestParam("categoryName") String categoryName) {
        if (StringUtils.isEmpty(categoryName)) {
            return new Result<>(500, "请输入分类名称！", null);
        }
        if (categoryService.updateCategory(categoryId, categoryName)) {
            logService.addLog("修改分类", categoryName);
            return new Result<>(200, "SUCCESS", null);
        } else {
            return new Result<>(500, "分类名称重复", null);
        }
    }

    /**
     * 分类删除
     */
    @RequestMapping(value = "/categories/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@RequestBody Integer[] ids) {

        // 这个应该放在删除之前。。。找这个bug不容易
        List<String> logDetails = categoryService.getBatchNames(ids);
        String msg = categoryService.deleteBatch(ids);
        if (msg.equals("SUCCESS")) {
            logService.addLog("删除分类", String.join("， ", logDetails));
            return new Result<>(200, "SUCCESS", null);

        } else {
            return new Result<>(500, msg, null);
        }
    }
}
