package com.nkbh.xuexue.bean;

/**
 * Created by User on 2018/3/11.
 */

public class PlanBean {
    int id;
    String title;
    String content;
    String time;
    boolean status;

    public PlanBean(String title, String content, String time, boolean status) {
        this.title = title;
        this.content = content;
        this.time = time;
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
