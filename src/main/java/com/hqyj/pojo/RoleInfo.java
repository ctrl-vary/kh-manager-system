package com.hqyj.pojo;

import java.util.List;

/**
 * @program: shiyou
 * @description:
 * @Author: Sherlock
 * @Date 2021/3/24 20:54
 */
public class RoleInfo {
    private int id;
    private String name;
    private String des;
    private List<Integer> pIds;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public List<Integer> getpIds() {
        return pIds;
    }

    public void setpIds(List<Integer> pIds) {
        this.pIds = pIds;
    }

    @Override
    public String toString() {
        return "RoleInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", des='" + des + '\'' +
                ", pIds=" + pIds +
                '}';
    }
}
