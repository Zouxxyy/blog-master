package com.zouxxyy.blog.core.dao;

import com.zouxxyy.blog.core.entity.Log;

import java.util.List;

public interface LogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Log record);

    int insertSelective(Log record);

    Log selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Log record);

    int updateByPrimaryKey(Log record);
    
    ///////

    int getLogCount();

    List<Log> getLogByStartAndLimit(Integer start, Integer limit);

    int deleteLogByIds(Integer[] tagIds);

}