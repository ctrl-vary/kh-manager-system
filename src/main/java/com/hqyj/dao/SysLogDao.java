package com.hqyj.dao;

import com.hqyj.pojo.SysLog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @program: shiyou
 * @description:
 * @Author: Sherlock
 * @Date 2021/3/26 22:10
 */
@Mapper
public interface SysLogDao {
    @Insert("INSERT INTO sys_log(content,user_name,role,ip) VALUES(#{content},#{userName},#{role},#{ip})")
    void insert(SysLog sysLog);
    @Select("SELECT id,content,user_name as userName,role,ip,time FROM sys_log")
    List<SysLog> getSysLog();
    @Delete("DELETE FROM sys_log WHERE id=#{id}")
    void delete(Integer id);
}
