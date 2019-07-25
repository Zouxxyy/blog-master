package com.zouxxyy.blog.core.service;

import com.zouxxyy.blog.core.util.PageResult;

public interface LogService {

    /**
     * 根据页码和每页个数，返回日志查询结果
     * @param page
     * @param limit
     * @return
     */
    PageResult getLogPage(Integer page, Integer limit);
}
