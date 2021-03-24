package com.hqyj.dao;

import com.hqyj.pojo.Auth;
import com.hqyj.pojo.Permission;
import com.hqyj.pojo.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @program: shiyou
 * @description:
 * @Author: Sherlock
 * @Date 2021/3/23 17:51
 */
@Mapper
public interface AuthDao {
    //获取角色的权限列表
    @Select("SELECT role.id as id,role.name as role,role.des as des," +
            "permission.name as auth,permission.id as pId " +
            "FROM role JOIN permission on " +
            "role.id=#{roleId} and permission.id in" +
            "(SELECT p_id from role_permission WHERE role_id=#{roleId})")
    List<Auth> getRoleAuth(int roleId);
    //获取所有角色的id
    @Select("select id from role")
    List<Integer> getRolesId();
    //更新角色
    @Update("UPDATE role set role.des=#{des},role.name=#{name} WHERE id=#{id}")
    void updateRole(Role role);
    //删除角色与权限的关系
    @Delete("DELETE FROM role_permission WHERE role_id={roleId} and p_id=#{pId}")
    void deleteRoleAuth(Integer roleId,Integer pId);
    //获取所有的权限
    @Select("SELECT * FROM permission")
    List<Permission> getPermissions();
    @Update("UPDATE permission set name=#{name}")
    void updatePermission(Permission p);
}
