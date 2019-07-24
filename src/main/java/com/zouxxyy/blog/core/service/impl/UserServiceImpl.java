package com.zouxxyy.blog.core.service.impl;

import com.zouxxyy.blog.core.dao.UserMapper;
import com.zouxxyy.blog.core.entity.User;
import com.zouxxyy.blog.core.service.UserService;
import com.zouxxyy.blog.core.util.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User login(String userName, String password) {
        // 进行md5加密
        String passwordMd5 = MD5Util.MD5Encode(password, "UTF-8");
        return userMapper.login(userName, passwordMd5);
    }
}
