package com.hqyj.pojo;

/**
 * @program: shiyou
 * @description:
 * @Author: Sherlock
 * @Date 2021/3/25 11:13
 */
public class KhSatisfaction {
    private Integer id;
    private Integer khId;
    private Integer jlId;
    private String khName;
    private String  linkMan;
    private String time;
    private float cost;
    private String des;
    private String satisfaction;
    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getKhId() {
        return khId;
    }

    public void setKhId(Integer khId) {
        this.khId = khId;
    }

    public Integer getJlId() {
        return jlId;
    }

    public void setJlId(Integer jlId) {
        this.jlId = jlId;
    }

    public String getKhName() {
        return khName;
    }

    public void setKhName(String khName) {
        this.khName = khName;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(String satisfaction) {
        this.satisfaction = satisfaction;
    }

    @Override
    public String toString() {
        return "KhSatisfaction{" +
                "id=" + id +
                ", khId=" + khId +
                ", jlId=" + jlId +
                ", khName='" + khName + '\'' +
                ", linkMan='" + linkMan + '\'' +
                ", time='" + time + '\'' +
                ", cost=" + cost +
                ", des='" + des + '\'' +
                ", satisfaction='" + satisfaction + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
