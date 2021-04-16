package com.yidu.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * (Userinfo)实体类
 *
 * @author makejava
 * @since 2021-04-08 10:59:57
 */
@Component
public class Userinfo implements Serializable {
    private static final long serialVersionUID = -98968998632500354L;
    
    private Integer userid;
    
    private String username;
    
    private String userpass;
    
    private String compellation;
    
    private Integer state;
    
    private String headimg;


    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpass() {
        return userpass;
    }

    public void setUserpass(String userpass) {
        this.userpass = userpass;
    }

    public String getCompellation() {
        return compellation;
    }

    public void setCompellation(String compellation) {
        this.compellation = compellation;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }


    @Override
    public String toString() {
        return "Userinfo{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", userpass='" + userpass + '\'' +
                ", compellation='" + compellation + '\'' +
                ", state=" + state +
                ", headimg='" + headimg + '\'' +
                '}';
    }
}