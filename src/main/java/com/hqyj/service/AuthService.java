package com.hqyj.service;
import com.hqyj.pojo.Auth;
import com.hqyj.pojo.Permission;
import com.hqyj.pojo.Role;

import java.util.List;
/**
 * @program: shiyou
 * @description:
 * @Author: Sherlock
 * @Date 2021/3/23 20:10
 */
public interface AuthService {
    List<Auth> getRoleAuth(int roleId);
    Auth getRoleAuthInfo(int roleId);
    List<Integer> getRolesId();
    void updateRole(Role role);
    void deleteRoleAuth(Integer roleId,Integer pId);
    List<Permission> getPermissions();
    void updatePermission(Permission p);
    String getPermissionInfo(int pId);
    void deletePermission(Integer pId);
    List<Role> getRoleByP(Integer pId);
    void deleteRole(Integer roleId);
}
