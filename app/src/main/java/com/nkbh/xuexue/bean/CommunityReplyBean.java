package com.nkbh.xuexue.bean;

/**
 * Created by User on 2018/3/22.
 */

public class CommunityReplyBean {

    /**
     * id : 2
     * profile_pic : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1521720129230&di=e020e6bb5a818ea9d535669c635190d5&imgtype=jpg&src=http%3A%2F%2Fimg4.imgtn.bdimg.com%2Fit%2Fu%3D461511789%2C4187442821%26fm%3D214%26gp%3D0.jpg
     * name : Boss
     * content : 管理员回复测试二
     * create_time : 1522302001
     */

    private int id;
    private String profile_pic;
    private String name;
    private String content;
    private int create_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
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

    public int getCreate_time() {
        return create_time;
    }

    public void setCreate_time(int create_time) {
        this.create_time = create_time;
    }
}
