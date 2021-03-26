package com.hqyj.dao;

import com.hqyj.pojo.UserInfo;
import org.apache.catalina.util.ServerInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 关于userInfo的数据库操作
 */
@Mapper //这个接口是执行的mybatis的数据库操作
public interface UserInfoDao {

    //通过邮箱查询
    @Select("select username from userInfo where email=#{email}")
    String selectByEmail(UserInfo user);

    //登录
    @Select("select * from userInfo where userName=#{userName} and userPwd=#{userPwd} and js=#{js}")
    UserInfo login(UserInfo user);

    //验证用户名是否重名
    @Select("select count(*) from userInfo where userName=#{userName} and js=#{js}")
    int valName(UserInfo user);

    //验证邮箱是否重复
    @Select("select count(*) from userInfo where email=#{email}")
    int valEmail(UserInfo user);

    //注册
    @Insert("insert into userInfo (userName,userPwd,salt,email,js) value (#{userName},#{userPwd},#{salt},#{email},#{js})")
    int zhuce(UserInfo user);


    //分页查询
    @Select("select * from userInfo")
    List<UserInfo> select();

    //根据userId查询
    @Select("select * from userInfo where userId=#{userId}")
    UserInfo selectByUserId(UserInfo user);

    //查询用户名是否存在，若存在就取出其盐值
    @Select("select * from userInfo where userName=#{userName} and js=#{js} ")
    UserInfo selectByName(UserInfo user);

    //修改
    @Update("update userInfo set userName=#{userName} where userId=#{userId}")
    int update(UserInfo user);

    //修改admin
    @Update("update userInfo set userName=#{userName},email=#{email},js=#{js},userPwd=#{userPwd} where userId=#{userId}")
    int updateAdmin(UserInfo user);

    //修改member(客户经理)
    @Update("update userInfo set userName=#{userName},email=#{email} where userId=#{userId}")
    int updatemember(UserInfo user);

    //删除
    @Delete("delete from userInfo where userId=#{userId}")
    int del(UserInfo user);

    //根据编号查询
    @Select("select * from userInfo where userId=#{userId}")
    List<UserInfo> findByUserId(UserInfo user);

    //根据用户名查询
    @Select("select * from userInfo where userName=#{userName}")
    List<UserInfo> findByUserName(UserInfo user);

    //根据userId查询(只有客户经理的数据)
    @Select("select * from userInfo where js='客户经理'")
    List<UserInfo> selectByJs(UserInfo user);

    //修改密码
    @Update("update userInfo set userPwd=#{userPwd} where userId=#{userId}")
    int updatePwd(UserInfo user);

    //修改头像
    @Update("update userInfo set url=#{url} where userId=#{userId}")
    int updateHead(UserInfo user);

    //添加admin
    @Insert("insert into UserInfo (userName,email,salt,js,userPwd,joinTime) value (#{userName},#{email},#{salt},#{js},#{userPwd},#{joinTime})")
    int addAdmin(UserInfo user);

    //添加member
    @Insert("insert into UserInfo (userName,email,salt,js,userPwd,joinTime) value (#{userName},#{email},#{salt},#{js},#{userPwd},#{joinTime})")
    int addMember(UserInfo user);

    //修改密码(member)
    @Update("update userInfo set userPwd=#{userPwd} where userName=#{userName} and userId=#{userId}")
    int updateMemberPwd(UserInfo user);
}







