package com.zouxxyy.blog.core.service.impl;


import com.zouxxyy.blog.core.dao.LogMapper;
import com.zouxxyy.blog.core.entity.Log;
import com.zouxxyy.blog.core.service.LogService;
import com.zouxxyy.blog.core.util.PageResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.zouxxyy.blog.core.util.LogCreator;

@Service
public class LogServiceImpl implements LogService {
    @Resource
    private LogMapper logMapper;


    @Override
    public PageResult getLogPage(Integer page, Integer limit) {
        List<Log> logList = logMapper.getLogByStartAndLimit((page - 1) * limit, limit);
        int count = logMapper.getLogCount(); // 获取总数
        PageResult pageResult = new PageResult(logList, count, limit, page);
        return pageResult;
    }

    @Override
    public void addLog(String type, String detail) {
        Log log = LogCreator.createLog(type, detail);
        logMapper.insertSelective(log);
    }

    @Override
    public Boolean deleteBatch(Integer[] ids) {
        return logMapper.deleteLogByIds(ids) > 0;
    }
}
