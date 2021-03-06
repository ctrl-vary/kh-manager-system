package com.hqyj.test;

import com.hqyj.dao.AuthDao;
import com.hqyj.pojo.Auth;
import com.hqyj.pojo.Permission;
import com.hqyj.pojo.Role;
import com.hqyj.service.AuthService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
/**
 * @program: shiyou
 * @description:
 * @Author: Sherlock
 * @Date 2021/3/23 20:08
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthTest {
    @Resource
    AuthService authService;
    @Resource
    AuthDao authDao;
    @Test
    public void test1(){
        List<Integer> rolesId=authService.getRolesId();
        List<Auth> authList=new ArrayList<>();
        int dataNum=rolesId.size();
        for (Integer a: rolesId) {
            Auth auth=authService.getRoleAuthInfo(a);
            authList.add(auth);
        }
        System.out.println(authList.toString());
    }
//    @Test
//    public void test2(){
//        System.out.println(authService.getRoleAuthInfo(3));
//    }
    @Test
    public void test3(){
        System.out.println(authService.getRolesId());
    }
    @Test
    public void test4(){
        Role role=new Role();
        role.setId(2);
        role.setDes("对用户增删改查");
        role.setName("客户经理1");
        authDao.updateRole(role);
    }
    @Test
    public void test5(){
        System.out.println(authDao.getRoleByP(1).toString());
    }
    @Test
    public void test6(){
        Permission permission=new Permission();
        permission.setId(4);
        permission.setName("几点开始");
        authDao.updatePermission(permission);
    }
}
