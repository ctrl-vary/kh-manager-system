package com.hqyj.service;

import com.hqyj.pojo.UserInfo;
import com.hqyj.pojo.kh;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

public interface KhService {
    //发送生日祝福方法（女）
    public HashMap<String, Object> sendZf(kh kh, HttpServletRequest request);
    //发送生日祝福方法（男）
    public HashMap<String, Object> sendZf1(kh kh, HttpServletRequest request);
    //excel方法
    public void excelWrite(HttpServletResponse response);

    //添加
    String add(kh kh);

    //查询
    HashMap<String,Object> select(kh kh);

    //查询生日
    HashMap<String,Object> selectSr(kh kh);

    //根据Id查询
    kh selectById(kh kh);

    //修改
    String update(kh kh);

    //删除
    String del(kh kh);

    List<kh> getKhByJl(Integer jlId);
    String updateLinkMan(kh kh);

    HashMap<String,Object> khUpdate(kh kh);

}
