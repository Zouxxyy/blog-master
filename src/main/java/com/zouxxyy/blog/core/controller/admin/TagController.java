package com.zouxxyy.blog.core.controller.admin;

import com.zouxxyy.blog.core.service.LogService;
import com.zouxxyy.blog.core.service.TagService;
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
public class TagController {

    @Resource
    private TagService tagService;

    @Resource
    private LogService logService;

    @GetMapping("/tags")
    public String tagPage(HttpServletRequest request) {
        request.setAttribute("path", "tags");
        return "admin/tag";
    }

    /**
     * 标签列表
     */
    @RequestMapping(value = "/tags/list", method = RequestMethod.GET)
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {

        // 前端发送 page 当前页码， limit 每页个数
        int page = Integer.parseInt(params.get("page").toString());
        int limit = Integer.parseInt(params.get("limit").toString());

        PageResult pageResult = tagService.getTagPage(page, limit);

        // 后台返回数据
        return new Result<>(200, "SUCCESS", pageResult);
    }

    /**
     * 标签添加
     */
    @RequestMapping(value = "/tags/save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(@RequestParam("tagName") String tagName) {
        if (StringUtils.isEmpty(tagName)) {
            return new Result<>(500, "请输入标签名称！", null);
        }
        if (tagService.saveTag(tagName)) {
            logService.addLog("添加标签", tagName);
            return new Result<>(200, "SUCCESS", null);
        } else {
            return new Result<>(500, "标签名称重复", null);
        }
    }

    /**
     * 标签修改
     */
    @RequestMapping(value = "/tags/update", method = RequestMethod.POST)
    @ResponseBody
    public Result update(@RequestParam("tagId") Integer tagId,
                         @RequestParam("tagName") String tagName) {
        if (StringUtils.isEmpty(tagName)) {
            return new Result<>(500, "请输入标签名称！", null);
        }
        if (tagService.updateTag(tagId, tagName)) {
            logService.addLog("修改标签", tagName);
            return new Result<>(200, "SUCCESS", null);
        } else {
            return new Result<>(500, "标签名称重复", null);
        }
    }

    /**
     * 标签删除
     */
    @RequestMapping(value = "/tags/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@RequestBody Integer[] ids) {
        if (ids.length < 1) {
            return new Result<>(500, "参数异常！", null);
        }

        List<String> logDetails = tagService.getBatchNames(ids);
        if (tagService.deleteBatch(ids)) {
            logService.addLog("删除标签", String.join("， ", logDetails));
            return new Result<>(200, "SUCCESS", null);

        } else {
            return new Result<>(500, "删除失败", null);
        }
    }
}
