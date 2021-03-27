package com.hqyj.dao;

import com.hqyj.pojo.KhSatisfaction;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @program: shiyou
 * @description:
 * @Author: Sherlock
 * @Date 2021/3/25 10:24
 */
@Mapper
public interface KhSatisfactionDao {
    //根据经理id获取客服记录
    @Select("SELECT id,kh_id as khId,kh_name as khName,link_man as linkMan," +
            "time,cost,des,satisfaction,type " +
            "FROM kh_satisfaction WHERE jl_id=#{jlId}")
    List<KhSatisfaction> getSatisfactionByJlId(Integer jlId);
    @Select("SELECT id,kh_id as khId,kh_name as khName,link_man as linkMan," +
            "time,cost,des,satisfaction,type " +
            " FROM kh_satisfaction WHERE id=#{sId}")
    KhSatisfaction getSatisfactionBysId(Integer sId);
    @Delete("DELETE FROM kh_satisfaction WHERE id=#{sId}")
    void delete(Integer sId);
    @Insert("INSERT INTO " +
            "kh_satisfaction(kh_id,jl_id,kh_name,link_man,time,cost,satisfaction,type,des) " +
            "value(#{khId},#{jlId},#{khName},#{linkMan},#{time},#{cost},#{satisfaction},#{type},#{des})")
    void insert(KhSatisfaction khSatisfaction);
    @Update("UPDATE kh_satisfaction set kh_id=#{khId},kh_name=#{khName},link_man=#{linkMan}," +
            "time=#{time},cost=#{cost},satisfaction=#{satisfaction},type=#{type},des=#{des} " +
            "WHERE jl_id=#{jlId}")
    void update(KhSatisfaction khSatisfaction);
}
