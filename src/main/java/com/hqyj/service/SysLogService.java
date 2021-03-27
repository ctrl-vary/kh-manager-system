package com.hqyj.service;

import com.hqyj.dao.SysLogDao;
import com.hqyj.pojo.SysLog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

/**
 * @program: shiyou
 * @description:
 * @Author: Sherlock
 * @Date 2021/3/26 22:22
 */
public interface SysLogService {
    void insert(SysLog sysLog);
    HashMap<String,Object> getSysLog(int pageNum);
    void delete(Integer id);
}
