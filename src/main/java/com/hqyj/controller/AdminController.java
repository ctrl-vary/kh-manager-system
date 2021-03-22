package com.hqyj.controller;
import com.hqyj.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminController {
    //访问 管理员列表页面
    @RequestMapping("/admin-list")
    public String adminlist(){
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


}
