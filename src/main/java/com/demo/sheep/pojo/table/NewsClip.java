package com.demo.sheep.pojo.table;

public class NewsClip {
    private Integer id;

    private Integer newsId;

    private String clipType;

    private String content;

    private Integer clipOrder;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public String getClipType() {
        return clipType;
    }

    public void setClipType(String clipType) {
        this.clipType = clipType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getClipOrder() {
        return clipOrder;
    }

    public void setClipOrder(Integer clipOrder) {
        this.clipOrder = clipOrder;
    }
}