package com.nkbh.xuexue.bean;

/**
 * Created by User on 2018/3/20.
 */

public class HomeworkBean {
    String picUrl;
    String title;
    String content;

    public HomeworkBean(String picUrl, String title, String content) {
        this.picUrl = picUrl;
        this.title = title;
        this.content = content;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
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
}
