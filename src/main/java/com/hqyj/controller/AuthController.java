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
    @GetMapping("/getRolesAuthList")
    public String getRolesAuthList(Integer roleId,ModelMap m){
        List<Auth> authList=authService.getRoleAuth(roleId);
        HashMap<String,Object> map=new HashMap<>();
        Role role=authService.getRoleById(roleId);
        //将role的名称和描述返回给model
        m.addAttribute("info",authList);
        m.addAttribute("name",role.getName());
        m.addAttribute("des",role.getDes());
        return "user/role-edit";
    }
    /**
     * 添加角色
     * @param
     */
    @PostMapping("/addRole")
    public String addRole(RoleInfo roleInfo){
        authService.addRoleAnd(roleInfo);
        return "添加成功";
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
    @GetMapping("/getPermissionInfo")
    public HashMap<String,Object> getPermissionInfo(){
        List<Permission> permissionList=authService.getPermissions();
        int size=permissionList.size();
        for (Permission p: permissionList) {
            p.setBrRole(authService.getPermissionInfo(p.getId()));
        }
       HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("info",permissionList);
        hashMap.put("dataNum",size);
        return hashMap;
    }

    /**
     * 获取某个权限编辑表单的信息
     * @param pId
     * @param m
     * @return
     */
    @GetMapping("/getPermissionById")
    public String getPermissionById(Integer pId,ModelMap m){
        Permission p=authService.getPermissionById(pId);
        m.addAttribute("info",p);
        return "user/rull-edit";
    }
    /**
     * 修改权限信息
     * @param p
     * @param
     */
    @GetMapping("/updatePermission")
    public String updatePermission(Permission p){
        authService.updatePermission(p);
        return "修改成功";
    }

    /**
     * 删除权限
     * @param pId
     */
    @GetMapping("/deletePermission")
    public String deletePermission(Integer pId){
        authService.deletePermission(pId);
        return "删除成功";
    }

    /**
     * 添加
     * @param p
     * @return
     */
    @PostMapping("/addPermission")
    public String addPermission(Permission p){
        authService.addPermission(p);
        return "添加成功";
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
