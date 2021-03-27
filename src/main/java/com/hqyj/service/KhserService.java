package com.hqyj.service;

import com.hqyj.pojo.KhSer;
import com.hqyj.pojo.UserInfo;

import java.util.HashMap;

public interface KhserService {
    //查询
    HashMap<String,Object> select(KhSer khSer);
    //添加
    String add(KhSer khSer);
    //删除
    String delKhser(KhSer khser);
}
