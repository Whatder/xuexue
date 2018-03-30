package com.nkbh.xuexue.bean;

import java.io.Serializable;

/**
 * Created by User on 2018/3/11.
 */

public class PlanBean implements Serializable {

    /**
     * id : 1
     * user_id : 1
     * title : 吃饭
     * content : 吃大餐
     * create_time : 1522123361
     * status : 1
     */

    private int id;
    private int user_id;
    private String title;
    private String content;
    private int create_time;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCreate_time() {
        return create_time;
    }

    public void setCreate_time(int create_time) {
        this.create_time = create_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
