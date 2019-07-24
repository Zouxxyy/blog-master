package com.zouxxyy.blog.core.dao;

import com.zouxxyy.blog.core.entity.Category;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CategoryMapper {
    int deleteByPrimaryKey(Integer categoryId);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer categoryId);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    // 以上是逆向工程生成的

    int getCategoryCount();

    List<Category> getCategoryByStartAndLimit(Integer start, Integer limit);

    Category selectByCategoryName(String categoryName);

    int deleteCategoryByIds(Integer[] categoryIds);

}