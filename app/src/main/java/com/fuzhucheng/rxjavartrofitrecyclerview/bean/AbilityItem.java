package com.fuzhucheng.rxjavartrofitrecyclerview.bean;

/**
 * Created by ${符柱成} on 2017/2/19.
 */

public class AbilityItem {
    private String image;
    private String title;
    private String content;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public AbilityItem(String image, String title, String content) {
        this.image = image;
        this.title = title;
        this.content = content;
    }
}
