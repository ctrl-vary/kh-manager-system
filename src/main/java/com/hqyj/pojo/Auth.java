package com.hqyj.pojo;

/**
 * @program: shiyou
 * @description:
 * @Author: Sherlock
 * @Date 2021/3/23 17:55
 */

public class Auth {
    private Integer id;
    private String role;
    private String auth;
    private String des;
    private Integer pId;
    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Override
    public String toString() {
        return "Auth{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", auth='" + auth + '\'' +
                ", des='" + des + '\'' +
                ", pId=" + pId +
                '}';
    }
}
