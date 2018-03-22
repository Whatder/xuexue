package com.nkbh.xuexue.bean;

/**
 * Created by User on 2018/3/22.
 */

public class CommunityReplyBean {
    String profilePic;
    String name;
    String content;
    String time;

    public CommunityReplyBean(String profilePic, String name, String content, String time) {
        this.profilePic = profilePic;
        this.name = name;
        this.content = content;
        this.time = time;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
