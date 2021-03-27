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
    @Select("SELECT name,des FROM role WHERE id=#{roleId}")
    Role getRoleById(Integer roleId);
    //更新角色
    @Update("UPDATE role set role.des=#{des},role.name=#{name} WHERE id=#{id}")
    void updateRole(Role role);
    //添加角色
    @Insert("INSERT INTO role (name,des) value(#{name},#{des})")
    void addRole(Role role);
    //添加role_p关系
    @Insert("INSERT INTO role_permission(role_id,p_id) value(#{roleId},#{pId})")
    void addRolePermission(Integer roleId,Integer pId);
    @Insert("INSERT INTO permission (name,rule) value(#{name},#{rule})")
    void addPermission(Permission permission);
    //删除角色与权限的关系
    @Delete("DELETE FROM role_permission WHERE role_id={roleId} and p_id=#{pId}")
    void deleteRoleAuth(Integer roleId,Integer pId);
    //获取所有的权限
    @Select("SELECT * FROM permission")
    List<Permission> getPermissions();
    @Select("SELECT * FROM permission WHERE id=#{pId}")
    Permission getPermissionById(Integer pId);
    //更新角色
    @Update("UPDATE permission set name=#{name},rule=#{rule} WHERE id=#{id}")
    void updatePermission(Permission p);
    //根据权限查找所属角色
    @Select("SELECT role.id as id,role.name as name " +
            "FROM role WHERE" +
            " role.id in(SELECT role_id FROM role_permission WHERE p_id=#{pId})")
    List<Role> getRoleByP(Integer pId);
    @Delete("DELETE FROM permission WHERE id=#{pId}")
    void deletePermission(Integer pId);
    @Delete("DELETE FROM role WHERE id=#{roleId}")
    void deleteRole(Integer roleId);
    @Delete("DELETE FROM role_permission WHERE role_id=#{roleId}")
    void deleteRolePermissionByRole(Integer roleId);
    @Delete("DELETE FROM role_permission WHERE permission_id=#{pId}")
    void deleteRolePermissionByPermission(Integer pId);
}
