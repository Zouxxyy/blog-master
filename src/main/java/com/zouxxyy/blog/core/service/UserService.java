package com.zouxxyy.blog.core.service;

import com.zouxxyy.blog.core.entity.User;

public interface UserService {

    /**
     * 登陆
     * @param userName
     * @param password
     * @return
     */
    User login(String userName, String password);
}
