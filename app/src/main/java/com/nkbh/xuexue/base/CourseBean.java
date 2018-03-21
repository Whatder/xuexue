package com.nkbh.xuexue.base;

import java.io.Serializable;

/**
 * Created by User on 2018/3/15.
 */

public class CourseBean implements Serializable {
    String picUrl;
    String title;
    String content;

    public CourseBean(String picUrl, String title, String content) {
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
