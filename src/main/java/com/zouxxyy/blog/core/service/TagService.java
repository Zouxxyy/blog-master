package com.zouxxyy.blog.core.service;


import com.zouxxyy.blog.core.util.PageResult;

public interface TagService {

    /**
     * 获取标签数量
     * @return
     */
    int getTagCount();

    /**
     * 根据页码和每页个数，返回标签查询结果
     * @param page
     * @param limit
     * @return
     */
    PageResult getTagPage(Integer page, Integer limit);

    /**
     * 保存标签
     * @param tagName
     * @return
     */
    Boolean saveTag(String tagName);


    /**
     * 更新标签
     * @param tagId
     * @param tagName
     * @return
     */
    Boolean updateTag(Integer tagId, String tagName);

    /**
     * 删除数组中的标签
     * @param ids
     * @return
     */
    Boolean deleteBatch(Integer[] ids);

}

