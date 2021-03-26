package com.hqyj.controller;

import com.hqyj.pojo.UserInfo;
import com.hqyj.pojo.kh;
import com.hqyj.service.KhService;
import com.hqyj.service.UserInfoService;
import com.hqyj.util.MdFive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
import java.util.HashMap;
import java.util.Random;

@Controller
public class MemberControler {


    //创建加密工具类对象
    @Autowired
    MdFive mdfive;

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
        HashMap<String, Object> map=userInfoService.selectByJs(userInfo);
        //        //把数据传到前端
        m.put("info",map);
        //将查询条件回显
        //m.put("vv",userInfo.getConValue());
        return "member-list";
    }

    @RequestMapping("/edit2")
    @ResponseBody
    public HashMap<String,Object> edit2(UserInfo user){

        HashMap<String,Object> map=new HashMap<String,Object>();
        String info=userInfoService.updatemember(user);

        map.put("info",info);
        return map;
    }
    //访问 编辑用户列表页面的页面
    @RequestMapping("/member-edit")
    public String memberedit(UserInfo user, ModelMap m){

        //根据userId查询
        UserInfo u=userInfoService.selectByUserId(user);

        //把数据传到前端
        m.addAttribute("user",u);
        return "member-edit";
    }

    //访问 添加用户列表页面的页面
    @RequestMapping("/member-add")
    public String memberadd(){

        return "member-add";
    }
    //处理添加客户请求
    @RequestMapping("/addMember")
    @ResponseBody
    public HashMap<String,Object> addMember(UserInfo user){
        HashMap<String,Object> map = new HashMap<String,Object>();
        //访问注册方法
        //自动生成一个盐值
        Random rd=new Random();
        String salt=rd.nextInt(10000)+"";

        Date date = new java.sql.Date(new java.util.Date().getTime());
        //加密用户输入的密码
        String pwd = mdfive.encrypt(user.getUserPwd(),salt);
        //把加过密码的传到数据层中
        user.setUserPwd(pwd);
        //存入盐值
        user.setSalt(salt);
        user.setJoinTime(date);
        user.setJs("客户经理");
        String info = userInfoService.addMember(user);
        map.put("info",info);
        return map;
    }

    //访问 密码修改的页面
    @RequestMapping("/member-password")
    public String memberpassword(UserInfo user, ModelMap m){

        //根据userId查询
        UserInfo u=userInfoService.selectByUserId(user);

        //把数据传到前端
        m.addAttribute("user",u);
        return "member-password";
    }

    //处理删除的ajax请求
    @RequestMapping("/delMember")
    @ResponseBody
    public HashMap<String,Object> delAdmin(UserInfo user){
        HashMap<String,Object> map=new HashMap<String,Object>();
        String info=userInfoService.delAdmin(user);
        map.put("info",info);
        return map;
    }

    //访问 删除用户的页面
    @RequestMapping("/member-del")
    public String memberdel(UserInfo user, ModelMap m){

        //根据userId查询
        UserInfo u=userInfoService.selectByUserId(user);

        //把数据传到前端
        m.addAttribute("user",u);
        return "member-del";
    }

    //访问 统计报表的页面
    @RequestMapping("/tj-echart")
    public String memberechart(UserInfo user, ModelMap m){

        //根据userId查询
        UserInfo u=userInfoService.selectByUserId(user);

        //把数据传到前端
        m.addAttribute("user",u);
        return "tj-echart";
    }




}
