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

    @Override
    public User getUserByUserId(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public Boolean updateName(Integer loginUserId, String userName, String userNickName) {
        User user = userMapper.selectByPrimaryKey(loginUserId);
        user.setUserName(userName);
        user.setUserNickname(userNickName);
        return userMapper.updateByPrimaryKeySelective(user) > 0;
    }

    @Override
    public Boolean updatePassword(Integer loginUserId, String originalPassword, String newPassword) {
        User user = userMapper.selectByPrimaryKey(loginUserId);
        // 将密码转成md5
        String originalPasswordMd5 = MD5Util.MD5Encode(originalPassword, "UTF-8");
        String newPasswordMd5 = MD5Util.MD5Encode(newPassword, "UTF-8");
        if (user.getUserPass().equals(originalPasswordMd5)) {
            user.setUserPass(newPasswordMd5);
            return userMapper.updateByPrimaryKeySelective(user) > 0;
        }
        else {
            return false;
        }
    }
}
