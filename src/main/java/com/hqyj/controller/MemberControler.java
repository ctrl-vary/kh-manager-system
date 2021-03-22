package com.hqyj.controller;

import com.hqyj.pojo.UserInfo;
import com.hqyj.pojo.kh;
import com.hqyj.service.KhService;
import com.hqyj.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
public class MemberControler {

    @Autowired
    KhService khService;

    @Autowired
    UserInfoService userInfoService;

    //获取图片上传的路径
    @Value("${file.address}")
    String fileAddress;

    //用户访问的图片路径
    @Value("${file.staticPath}")
    String upload;

    //访问用户管理-用户列表页面
    @RequestMapping("/member-list")
    public String showM(UserInfo userInfo, ModelMap m){
        HashMap<String, Object> map=userInfoService.select(userInfo);
        //        //把数据传到前端
        m.put("info",map);
        //将查询条件回显
        m.put("vv",userInfo.getConValue());
        return "member-list";
    }

//    @RequestMapping("/edit2")
//    @ResponseBody
//    public HashMap<String,Object> edit2(UserInfo user){
//
//        HashMap<String,Object> map=new HashMap<String,Object>();
//        String info=userInfoService.memberupdate(user);
//
//        map.put("info",info);
//        return map;
//    }
    //访问 编辑用户列表页面的页面
    @RequestMapping("/member-edit")
    public String memberedit(UserInfo user, ModelMap m){

        //根据userId查询
        UserInfo u=userInfoService.selectByUserId(user);

        //把数据传到前端
        m.addAttribute("user",u);
        return "member-edit";
    }



}
