package com.hqyj.service;

import com.hqyj.dao.AuthDao;
import com.hqyj.pojo.Auth;
import com.hqyj.pojo.Permission;
import com.hqyj.pojo.Role;
import com.hqyj.pojo.RoleInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @program: shiyou
 * @description:
 * @Author: Sherlock
 * @Date 2021/3/23 20:11
 */
@Service
public class AuthServiceImp implements AuthService{
    @Resource
    AuthDao authDao;

    /**
     * 获取每个角色的权限列表
     * @param roleId
     * @return
     */
    @Override
    public List<Auth> getRoleAuth(int roleId) {
        List<Auth> authList=authDao.getRoleAuth(roleId);
        return authList;
    }
    /**
     * 获取每个角色的展示信息
     * @param roleId
     * @return
     */
    @Override
    public Auth getRoleAuthInfo(int roleId) {
        List<Auth> authList=authDao.getRoleAuth(roleId);
        if(authList.size()==0){return null;}
        Auth auth;
        auth=authList.get(0);
        StringBuilder str=null;
        str=new StringBuilder(auth.getAuth());
        for(int i=1;i<authList.size();i++){
            Auth a=authList.get(i);
            str.append(","+a.getAuth());
        }
        auth.setAuth(str.toString());
        return auth;
    }
//    /**
//     * 获取每个角色的展示信息
//     * @param roleId
//     * @return
//     */
//    @Override
//    public HashMap<String,Object> getRoleAuthInfo(int roleId) {
//        HashMap<String, Object> map=new HashMap<String, Object>();
//        List<Auth> authList=authDao.getRoleAuth(roleId);
//        if(authList.size()==0){return null;}
//        Auth auth;
//        auth=authList.get(0);
//        StringBuilder str=null;
//        str=new StringBuilder(auth.getAuth());
//        for(int i=1;i<authList.size();i++){
//            Auth a=authList.get(i);
//            str.append(","+a.getAuth());
//        }
//        auth.setAuth(str.toString());
//
//        int dataNum=authDao.getRolesId().size();
//        for (Integer a: authDao.getRolesId()) {
//            authList.add(auth);
//        }
//        map.put("list",authList);
//        map.put("dataNum",dataNum);
//        return map;
//    }

    @Override
    public List<Integer> getRolesId() {
        return authDao.getRolesId();
    }

    @Override
    public void updateRole(Role role) {
        authDao.updateRole(role);
    }

    @Override
    public void deleteRoleAuth(Integer roleId, Integer pId) {
        authDao.deleteRoleAuth(roleId,pId);
    }

    @Override
    public List<Permission> getPermissions() {
        return authDao.getPermissions();
    }

    @Override
    public void updatePermission(Permission p) {
        authDao.updatePermission(p);
    }

    @Override
    public String getPermissionInfo(int pId) {
        List<Role> roleList=authDao.getRoleByP(pId);
        if(roleList.size()<=0){return  null;}
        Role r=roleList.get(0);
        StringBuilder s=new StringBuilder(r.getName());
        for (int i=1;i<roleList.size();i++){
            Role role=roleList.get(i);
            s.append(","+role.getName());
        }
        return s.toString();
    }

    @Override
    public void deletePermission(Integer pId) {
        authDao.deletePermission(pId);
        authDao.deleteRolePermissionByPermission(pId);
    }
    @Override
    public List<Role> getRoleByP(Integer pId) {
        return authDao.getRoleByP(pId);
    }
    @Override
    public void deleteRole(Integer roleId) {
        authDao.deleteRole(roleId);
        authDao.deleteRolePermissionByRole(roleId);
    }

    @Override
    public Role getRoleById(Integer roleId) {
        return authDao.getRoleById(roleId);
    }

    @Override
    public void addRoleAnd(RoleInfo roleInfo) {
        Role role=new Role();
        role.setDes(roleInfo.getDes());
        role.setId(roleInfo.getId());
        role.setName(roleInfo.getName());
        int roleId=role.getId();
        authDao.addRole(role);
        List<Integer> pIds=roleInfo.getpIds();
        for (Integer pId: pIds) {
            authDao.addRolePermission(roleId,pId);
        }

    }
    @Override
    public void addPermission(Permission p) {
        authDao.addPermission(p);
    }

    @Override
    public Permission getPermissionById(Integer pId) {
        return authDao.getPermissionById(pId);
    }

}
