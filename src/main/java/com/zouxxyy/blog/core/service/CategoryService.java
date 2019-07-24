package com.zouxxyy.blog.core.service;

import com.zouxxyy.blog.core.entity.Category;
import com.zouxxyy.blog.core.util.PageResult;

import java.util.List;

public interface CategoryService {

    /**
     * 获取分类数量
     * @return
     */
    int getCategoryCount();

    /**
     * 根据页码和每页个数，返回分类查询结果
     * @param page
     * @param limit
     * @return
     */
    PageResult getCategoryPage(Integer page, Integer limit);

    /**
     * 保存分类
     * @param categoryName
     * @return
     */
    Boolean saveCategory(String categoryName);


    /**
     * 更新分类
     * @param categoryId
     * @param categoryName
     * @return
     */
    Boolean updateCategory(Integer categoryId, String categoryName);

    /**
     * 删除数组中的分类
     * @param ids
     * @return
     */
    String deleteBatch(Integer[] ids);

    /**
     * 获取全部的类别
     * @return
     */
    List<Category> getAllCategories();

}
