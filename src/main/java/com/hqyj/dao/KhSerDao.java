package com.hqyj.dao;

import com.hqyj.pojo.KhSer;
import com.hqyj.pojo.UserInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface KhSerDao {
    //分页查询
    @Select("select * from KhSer")
    List<KhSer> select();

    //添加客户
    @Insert("insert into khser (ser_date,ser_time,ser_place,ser_kh_name,ser_kh_phone,ser_type,content) value (#{ser_date},#{ser_time},#{ser_place},#{ser_kh_name},#{ser_kh_phone},#{ser_type},#{content})")
    int addKhser(KhSer khser);

    //删除
    @Delete("delete from khser where ser_kh_name=#{ser_kh_name}")
    int delKhser(KhSer khser);
}
