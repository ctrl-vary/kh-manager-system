package com.hqyj.controller;
import com.hqyj.pojo.UserInfo;
import com.hqyj.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
public class AdminController {

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

    @RequestMapping("/admin-edit")
    public String adminedit(){
        return "admin-edit";
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
