package com.nkbh.xuexue.bean.admin;

import java.io.Serializable;

/**
 * Created by User on 2018/4/3.
 */

public class Admin implements Serializable {

    /**
     * id : 1
     * account : super
     * password : 123456
     */

    private int id;
    private String account;
    private String password;

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
}
