package com.zouxxyy.blog.core.util;

import com.zouxxyy.blog.core.entity.Log;
import java.util.Date;
import com.zouxxyy.blog.core.controller.admin.AdminController;

public class LogCreator {
    public static Log createLog(String type, String detail) {
        Log log = new Log();
        log.setDetail(detail);
        log.setIp(AdminController.LoginIp);
        log.setTime(new Date());
        log.setType(type);
        return log;
    }
}
