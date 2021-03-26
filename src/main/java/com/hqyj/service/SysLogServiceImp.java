package com.hqyj.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.dao.SysLogDao;
import com.hqyj.pojo.SysLog;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @program: shiyou
 * @description:
 * @Author: Sherlock
 * @Date 2021/3/26 22:23
 */
@Service
public class SysLogServiceImp implements SysLogService{
    @Resource
    SysLogDao sysLogDao;
    @Override
    public void insert(SysLog sysLog) {
        sysLogDao.insert(sysLog);
    }

    @Override
    public HashMap<String,Object> getSysLog(int pageNum) {
        PageHelper.startPage(pageNum,15);
        List<SysLog> sysLogList=sysLogDao.getSysLog();
        PageInfo<SysLog> sysLogPageInfo=new PageInfo<>(sysLogList);
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("data",sysLogList);
        hashMap.put("page",sysLogPageInfo.getPages());
        return hashMap;
    }

    @Override
    public void delete(Integer id) {
        sysLogDao.delete(id);
    }
}
