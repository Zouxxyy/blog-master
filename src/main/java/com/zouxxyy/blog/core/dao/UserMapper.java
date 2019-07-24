package com.zouxxyy.blog.core.dao;

import com.zouxxyy.blog.core.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {

    /**
     * 登陆
     * @param userName
     * @param password
     * @return
     */
    User login(@Param("userName") String userName, @Param("password") String password);

    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKeyWithBLOBs(User record);

    int updateByPrimaryKey(User record);
}