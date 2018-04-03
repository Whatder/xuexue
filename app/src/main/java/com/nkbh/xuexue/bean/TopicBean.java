package com.nkbh.xuexue.bean;

import java.io.Serializable;

/**
 * Created by User on 2018/3/17.
 */

public class TopicBean implements Serializable {

    /**
     * id : 1
     * name : Jayyy
     * profile_pic : pic2
     * author_id : 2
     * title : 今天天气真好
     * content : 哈哈哈哈哈
     * create_time : 1522224797
     * like_count : 1
     */

    private int id;
    private String name;
    private String profile_pic;
    private int author_id;
    private String title;
    private String content;
    private int create_time;
    private int like_count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
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

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }
}
