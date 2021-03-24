package com.hqyj.pojo;

/**
 * @program: shiyou
 * @description:
 * @Author: Sherlock
 * @Date 2021/3/23 21:05
 */
public class Role {
    private int id;
    private String name;
    private String des;

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

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", des='" + des + '\'' +
                '}';
    }
}
