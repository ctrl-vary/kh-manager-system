package com.hqyj.dao;
import com.hqyj.pojo.UserInfo;
import com.hqyj.pojo.kh;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface KhDao {
    //添加客户
    @Insert("insert into kh (name,gender,url,phone,email,bdate,edu,idn,region,company,managerid,manager,tips) value (#{name},#{gender},#{url},#{phone},#{email},#{bdate},#{edu},#{idn},#{region},#{company},#{managerid},#{manager},#{tips})")
    int addKh(kh kh);

    //分页查询
    @Select("select * from kh")
    List<kh> select();

    //根据编号查询
    @Select("select * from kh where id=#{id}")
    List<kh> findById(kh kh);

    //根据姓名查询
    @Select("select * from kh where name=#{name}")
    List<kh> findByName(kh kh);

    //根据经理编号查询
    @Select("select * from kh where managerid=#{managerid}")
    List<kh> findByManagerId(kh kh);

    @Select("select * from kh where managerid=#{jlId}")
    List<kh> getKhByJl(Integer jlId);

    //根据经理姓名查询
    @Select("select * from kh where manager=#{manager}")
    List<kh> findByManager(kh kh);

    //根据Id查询
    @Select("select * from kh where id=#{id}")
    kh selectById(kh kh);

    //修改
    @Update("update kh set name=#{name},gender=#{gender},url=#{url}," +
            "phone=#{phone},email=#{email},bdate=#{bdate},edu=#{edu}," +
            "idn=#{idn},region=#{region},company=#{company},managerid=#{managerid}," +
            "manager=#{manager},tips=#{tips} where id=#{id}")
    int update(kh kh);
    @Update("update kh set name=#{name},gender=#{gender},phone=#{phone},email=#{email},region=#{region} where id=#{id}")
    int updateLinkMan(kh kh);
    //删除
    @Delete("delete from kh where id=#{id}")
    int del(kh kh);

    //客户生日分页查询
    @Select("select id,name,gender,email,bdate,send from kh")
    List<kh> selectSr();
}
