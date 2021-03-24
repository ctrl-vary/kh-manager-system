package com.hqyj.service;

import com.hqyj.dao.AuthDao;
import com.hqyj.pojo.Auth;
import com.hqyj.pojo.Permission;
import com.hqyj.pojo.Role;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

}
