package com.hqyj.controller;

import com.alibaba.fastjson.JSONObject;
import com.hqyj.pojo.Auth;
import com.hqyj.pojo.Permission;
import com.hqyj.pojo.Role;
import com.hqyj.pojo.RoleInfo;
import com.hqyj.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import javax.annotation.Resource;
import java.util.*;

/**
 * @program: shiyou
 * @description:
 * @Author: Sherlock
 * @Date 2021/3/23 20:33
 */
@Controller
@RequestMapping("/auth")
public class AuthController {
    @Resource
    AuthService authService;

    /**
     * 获取角色展示信息
     * @param
     */
    @GetMapping("/admin-role")
    public String getRolesAuth(ModelMap m){
        List<Integer> rolesId=authService.getRolesId();
        List<Auth> authList=new ArrayList<>();
        int dataNum=rolesId.size();
        for (Integer a: rolesId) {
            Auth auth=authService.getRoleAuthInfo(a);
            authList.add(auth);
        }

        m.put("info",authList);
        m.put("dataNum",dataNum);
        return "admin-role";
    }

    /**
     * 获取角色具体修改信息
     */
    @GetMapping("/role-edit")
    public String getRolesAuthList(Integer roleId,ModelMap m){
        List<Auth> authList=authService.getRoleAuth(roleId);
        HashMap<String,Object> map=new HashMap<>();
        Role role=authService.getRoleById(roleId);
        //将role的名称和描述返回给model
        m.addAttribute("info",authList);
        m.addAttribute("name",role.getName());
        m.addAttribute("des",role.getDes());
        return "role-edit";
    }
    /**
     * 添加角色
     * @param
     */
    @PostMapping("/addRole")
    public HashMap<String,Object> addRole(RoleInfo roleInfo){
        HashMap<String,Object> map = new HashMap<String,Object>();
        authService.addRoleAnd(roleInfo);
        String info="添加成功";
        map.put("info",info);;
        return map;
    }
    /**
     * 更新角色
     * @param authList
     */
    @PostMapping("/updateRole")
    public String updateRole(List<Auth> authList){
        if(authList.size()<=0){return "更新成功";}
        Auth auth=authList.get(0);
        Integer roleId=auth.getId();

        Role role=new Role();
        role.setName(auth.getRole());
        role.setId(auth.getId());
        role.setDes(auth.getDes());
        authService.updateRole(role);

       List<Integer> delId=getDelId(authList,roleId);
        for (Integer i: delId) {
            authService.deleteRoleAuth(roleId,i);
        }
        return "更新成功";
    }
    /**
     * 删除角色
     * @param roleId
     */
    @GetMapping("/deleteRole")
    public String deleteRole(Integer roleId){
        authService.deleteRole(roleId);
        return "删除成功";
    }
//////////////////////////////// 权限 ///////////////////////////////////////
    /**
     * 获取权限展示信息
     */
    @GetMapping("/admin-rule")
    public String  getPermissionInfo(ModelMap m){
        List<Permission> permissionList=authService.getPermissions();
        int size=permissionList.size();
        for (Permission p: permissionList) {
            p.setBrRole(authService.getPermissionInfo(p.getId()));
        }
//       HashMap<String,Object> hashMap=new HashMap<>();
        m.put("info",permissionList);
        m.put("dataNum",size);
        return "admin-rule";
    }

    /**
     * 获取某个权限编辑表单的信息
     * @param pId
     * @param m
     * @return
     */
    @GetMapping("/rule-edit")
    public String getPermissionById(Integer pId,ModelMap m){
        Permission p=authService.getPermissionById(pId);
        System.out.println(p.getName()+"---------------------");
        System.out.println(p.getRule()+"---------------------");
        m.addAttribute("info",p);
        return "rule-edit";
    }

    /**
     * 修改权限信息
     * @param p
     * @param
     */
    @PostMapping("/updatePermission")
    @ResponseBody
    public HashMap<String,Object> updatePermission(Permission p){
        System.out.println(p+"p---------------------------");
        HashMap<String,Object> map = new HashMap<String,Object>();
        authService.updatePermission(p);
        String info="修改成功";
        map.put("info",info);;
        return map;
    }

    /**
     * 删除权限
     * @param pId
     */
    @PostMapping("/deletePermission")
    @ResponseBody
    public HashMap<String,Object> deletePermission(Integer pId){
        System.out.println(pId+"pid++++++++++++++++++++++++++++++");
        HashMap<String,Object> map = new HashMap<String,Object>();
        authService.deletePermission(pId);
        String info="删除成功";
        map.put("info",info);;
        return map;
    }

    /**
     * 添加
     * @param p
     * @return
     */
    @PostMapping("/addPermission")
    @ResponseBody
    public HashMap<String,Object> addPermission(Permission p){
        HashMap<String,Object> map = new HashMap<String,Object>();
        authService.addPermission(p);
        String info="添加成功";
        map.put("info",info);;
        return map;
    }

    /**
     * 获取到需要取消与角色关系的权限,不用管
     * @param authList
     * @param roleId
     * @return
     */
    public List<Integer> getDelId(List<Auth> authList,Integer roleId){
        List<Auth> authList0=authService.getRoleAuth(roleId);
        Set<Integer> ids=new HashSet<>();
        for (Auth a: authList  ) { ids.add(a.getpId()); }
        List<Integer> delId=new ArrayList<>();
        for (Auth b: authList0) {
            if(!ids.contains(b.getpId())){delId.add(b.getpId());}
        }
        return delId;
    }
}
