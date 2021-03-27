package com.hqyj.dao;

import com.hqyj.pojo.UserInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserInfo_Copy1Dao {
    //添加被删除了的member
    @Insert("insert into UserInfo_copy1 (userId,userName,email,js,period,joinTime) value (#{userId},#{userName},#{email},#{js},'已删除',#{joinTime})")
    int addMemberCopy(UserInfo user);

    //根据userId查询
    @Select("select * from UserInfo_copy1 where userId=#{userId}")
    UserInfo selectByUserId(UserInfo user);

    //分页查询
    @Select("select * from UserInfo_copy1")
    List<UserInfo> select(UserInfo user);

    //删除
    @Delete("delete from UserInfo_copy1 where userId=#{userId}")
    int delrecover(UserInfo user);
}
