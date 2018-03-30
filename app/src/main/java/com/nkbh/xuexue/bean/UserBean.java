package com.nkbh.xuexue.bean;

import java.io.Serializable;

/**
 * Created by User on 2018/3/29.
 */

public class UserBean implements Serializable {

    /**
     * id : 1
     * account : admin
     * password : 123456
     * name : Boss
     * profile_pic : pic1
     */

    private int id;
    private String account;
    private String password;
    private String name;
    private String profile_pic;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }
}
