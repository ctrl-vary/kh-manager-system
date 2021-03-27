package com.hqyj.pojo;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class KhSer  extends MyPage implements Serializable {
    private Date ser_date;
    private Timestamp ser_time;
    private String ser_place;
    private String ser_kh_name;
    private String ser_kh_phone;
    private String ser_type;
    private String content;

    public Date getSer_date() {
        return ser_date;
    }

    public String getSer_type() {
        return ser_type;
    }

    public void setSer_type(String ser_type) {
        this.ser_type = ser_type;
    }

    public void setSer_date(Date ser_date) {
        this.ser_date = ser_date;
    }

    public Timestamp getSer_time() {
        return ser_time;
    }

    public void setSer_time(Timestamp ser_time) {
        this.ser_time = ser_time;
    }

    public String getSer_place() {
        return ser_place;
    }

    public void setSer_place(String ser_place) {
        this.ser_place = ser_place;
    }

    public String getSer_kh_name() {
        return ser_kh_name;
    }

    public void setSer_kh_name(String ser_kh_name) {
        this.ser_kh_name = ser_kh_name;
    }

    public String getSer_kh_phone() {
        return ser_kh_phone;
    }

    public void setSer_kh_phone(String ser_kh_phone) {
        this.ser_kh_phone = ser_kh_phone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
