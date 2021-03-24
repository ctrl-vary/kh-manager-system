package com.hqyj.pojo;

/**
 * @program: shiyou
 * @description:
 * @Author: Sherlock
 * @Date 2021/3/23 21:42
 */
public class Permission {
    private Integer id;
    private String name;
    private String brRole;
    private String rule;

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getBrRole() {
        return brRole;
    }

    public void setBrRole(String brRole) {
        this.brRole = brRole;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brRole='" + brRole + '\'' +
                ", rule='" + rule + '\'' +
                '}';
    }
}
