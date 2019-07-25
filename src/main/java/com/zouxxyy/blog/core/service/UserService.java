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

    /**
     * 根据ID得到user
     * @param userId
     * @return
     */
    User getUserByUserId(Integer userId);


    /**
     * 更新用户名和昵称
     * @param loginUserId
     * @param userName
     * @param userNickName
     * @return
     */
    Boolean updateName(Integer loginUserId, String userName, String userNickName);

    /**
     * 修改当前登录用户的密码
     *
     * @param loginUserId
     * @param originalPassword
     * @param newPassword
     * @return
     */
    Boolean updatePassword(Integer loginUserId, String originalPassword, String newPassword);
}
