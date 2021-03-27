package com.hqyj.pojo;

import java.util.Date;

/**
 * @program: shiyou
 * @description:
 * @Author: Sherlock
 * @Date 2021/3/26 22:06
 */
public class SysLog {
    private Integer id;
    private String content;
    private String userName;
    private String role;
    private String ip;
    private Date time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "SysLog{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", userName='" + userName + '\'' +
                ", role='" + role + '\'' +
                ", ip='" + ip + '\'' +
                ", time=" + time +
                '}';
    }
}
