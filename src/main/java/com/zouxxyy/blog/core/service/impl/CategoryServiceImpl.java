package com.zouxxyy.blog.core.service.impl;

import com.zouxxyy.blog.core.dao.ArticleMapper;
import com.zouxxyy.blog.core.dao.CategoryMapper;
import com.zouxxyy.blog.core.entity.Category;
import com.zouxxyy.blog.core.service.CategoryService;
import com.zouxxyy.blog.core.util.PageResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private ArticleMapper articleMapper;

    @Override
    public int getCategoryCount() {
        return categoryMapper.getCategoryCount();
    }

    @Override
    public PageResult getCategoryPage(Integer page, Integer limit) {
        List<Category> categoryList = categoryMapper.getCategoryByStartAndLimit((page - 1) * limit, limit);
        int count = categoryMapper.getCategoryCount(); // 获取总数
        PageResult pageResult = new PageResult(categoryList, count, limit, page);
        return pageResult;
    }

    @Override
    public Boolean saveCategory(String categoryName) {
        Category temp = categoryMapper.selectByCategoryName(categoryName);
        // 判断该分类名是否已经存在
        if (temp == null) {
            Category category = new Category();
            category.setCategoryName(categoryName);
            return categoryMapper.insertSelective(category) > 0;
        }
        return false;
    }

    @Override
    public Boolean updateCategory(Integer categoryId, String categoryName) {

        Category temp = categoryMapper.selectByCategoryName(categoryName);
        // 判断该分类名是否已经存在
        if (temp == null) {
            Category category = categoryMapper.selectByPrimaryKey(categoryId);
            category.setCategoryName(categoryName);
            return categoryMapper.updateByPrimaryKeySelective(category) > 0;
        }
        return false;
    }

    @Override
    public String deleteBatch(Integer[] ids) {
        if (ids.length < 1) {
            return "参数异常！";
        }
        // 判断是否有包含这些ID的文章
        for(Integer id : ids) {
            Integer count = articleMapper.getArticleCountByCid(id);
            if(count > 0)
                return "不能删除含有文章的分类";
        }

        //删除分类数据
        categoryMapper.deleteCategoryByIds(ids);
        return "SUCCESS";
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryMapper.getCategoryByStartAndLimit(null, null);
    }

    @Override
    public List<String> getBatchNames(Integer[] ids) {
        return categoryMapper.getCategoryNamesByIds(ids);
    }

}
