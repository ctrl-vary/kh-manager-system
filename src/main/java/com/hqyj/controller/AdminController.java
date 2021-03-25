package com.hqyj.controller;
import com.hqyj.pojo.UserInfo;
import com.hqyj.service.UserInfoService;
import com.hqyj.util.MdFive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
import java.util.HashMap;
import java.util.Random;

@Controller
public class AdminController {

    //创建加密工具类对象
    @Autowired
    MdFive mdfive;

    @Autowired
    UserInfoService userInfoService;
    //访问 管理员列表页面
    @RequestMapping("/admin-list")
    public String adminlist(UserInfo user, ModelMap m){
        HashMap<String, Object> map=userInfoService.selectByJointime(user);
        //把数据传到前端
        m.put("info",map);
        //将查询条件回显

        return "admin-list";
    }

    @RequestMapping("/admin-cate")
    public String admincate(){
        return "admin-cate";
    }

    @RequestMapping("/admin-role")
    public String adminrole(){
        return "admin-role";
    }

    @RequestMapping("/admin-rule")
    public String adminrule(){
        return "admin-rule";
    }

    @RequestMapping("/admin-add")
    public String adminadd(){
        return "admin-add";
    }

    //处理添加客户请求
    @RequestMapping("/addAdmin")
    @ResponseBody
    public HashMap<String,Object> addAdmin(UserInfo user){
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
        String info = userInfoService.addAdmin(user);
        map.put("info",info);
        return map;
    }

    @RequestMapping("/admin-edit")
    public String adminedit(UserInfo user, ModelMap m){
        System.out.println("----------------------------------------------");
        System.out.println(user.getUserId());
        //根据userId查询
        UserInfo user1=userInfoService.selectByUserId(user);
        System.out.println("----------------------------");
        System.out.println(user1);

        //把数据传到前端
        m.addAttribute("user",user1);

        return "admin-edit";
    }

    @RequestMapping("/edit3")
    @ResponseBody
    public HashMap<String,Object> edit3(UserInfo user){
        System.out.println("edit---------------------------------------");
        HashMap<String,Object> map=new HashMap<String,Object>();
        String info=userInfoService.updateAdmin(user);
        System.out.println(info+"---------------------------------------");
        map.put("info",info);
        return map;
    }

    @RequestMapping("/role-edit")
    public String roleedit(){
        return "role-edit";
    }

    @RequestMapping("/role-add")
    public String roleadd(){
        return "role-add";
    }

    @RequestMapping("/rule-edit")
    public String ruleedit(){
        return "rule-edit";
    }

    @RequestMapping("/rule-add")
    public String ruleadd(){
        return "rule-add";
    }

    @RequestMapping("/sys-log")
    public String syslog(){
        return "sys-log";
    }

    @RequestMapping("/sys-set")
    public String sysset(){
        return "sys-set";
    }


}
