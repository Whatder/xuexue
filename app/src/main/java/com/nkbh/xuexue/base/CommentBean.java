package com.nkbh.xuexue.base;

/**
 * Created by User on 2018/3/17.
 */

public class CommentBean {
    String profilePic;
    String name;
    String time;
    String content;

    public CommentBean(String profilePic, String name, String time, String content) {
        this.profilePic = profilePic;
        this.name = name;
        this.time = time;
        this.content = content;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
