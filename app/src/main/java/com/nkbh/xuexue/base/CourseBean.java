package com.nkbh.xuexue.base;

import java.io.Serializable;

/**
 * Created by User on 2018/3/15.
 */

public class CourseBean implements Serializable {

    /**
     * id : 2
     * thumbnail :
     * title : Android开发艺术
     * summary : 《Android开发艺术探索》侧重于Android知识的体系化和系统工作机制的分析，通过《Android开发艺术探索》的学习可以极大地提高开发者的Android技术水平，从而更加高效地成为高级开发者。而对于高级开发者来说，仍然可以从《Android开发艺术探索》的知识体系中获益。
     * url : http://10.1.95.99/movies/1522648283.mp4
     */

    private int id;
    private String thumbnail;
    private String title;
    private String summary;
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
