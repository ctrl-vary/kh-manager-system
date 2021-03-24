package com.hqyj.controller;

import com.hqyj.pojo.Auth;
import com.hqyj.pojo.Permission;
import com.hqyj.pojo.Role;
import com.hqyj.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
     * @param m
     */
    @GetMapping("/getRolesAuth")
    public void getRolesAuth(ModelMap m){
        List<Integer> rolesId=authService.getRolesId();
        List<Auth> authList=new ArrayList<>();
        int dataNum=rolesId.size();
        for (Integer a: rolesId) {
            Auth auth=authService.getRoleAuthInfo(a);
            authList.add(auth);
        }
    }

    /**
     * 获取角色具体权限
     */
    @GetMapping("/getRolesAuthList")
    public void getRolesAuthList(Integer roleId){
        List<Auth> authList=authService.getRoleAuth(roleId);
    }

    /**
     * 删除角色权限
     * @param authList
     */
    @PostMapping("/updateRole")
    public void updateRole(List<Auth> authList){
        if(authList.size()<=0){return;}
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

    }

    /**
     * 获取权限展示信息
     */
    @GetMapping("/getPermissionInfo")
    public void getPermissionInfo(){
        List<Permission> permissionList=authService.getPermissions();
        int size=permissionList.size();
        for (Permission p: permissionList) {
            p.setBrRole(authService.getPermissionInfo(p.getId()));
        }
    }

    /**
     * 修改权限信息
     * @param p
     * @param ids
     */
    @GetMapping("/updatePermission")
    public void updatePermission(Permission p,List<Integer> ids){
        authService.updatePermission(p);
        if(ids.size()>0){
       List<Role> roleList=authService.getRoleByP(p.getId());
       Set<Integer> oldRoleId=new HashSet<>();
       List<Integer> delId=new ArrayList<>();
        for (Role r: roleList) { oldRoleId.add(r.getId()); }
            for (Integer id: ids) {
                if(!oldRoleId.contains(id)){delId.add(id);}
            }
        }

    }

    /**
     * 删除权限
     * @param pId
     */
    @GetMapping("/deletePermission")
    public void deletePermission(Integer pId){
        authService.deletePermission(pId);
    }

    /**
     * 删除角色
     * @param roleId
     */
    @GetMapping("/deleteRole")
    public void deleteRole(Integer roleId){
        authService.deleteRole(roleId);
    }
    /**
     * 获取到需要取消与角色关系的权限
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
