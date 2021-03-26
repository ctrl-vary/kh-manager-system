package com.hqyj.service;

import com.hqyj.pojo.UserInfo;
import com.hqyj.pojo.kh;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

public interface KhService {
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

}
