package com.fuzhucheng.rxjavartrofitrecyclerview.bean;

/**
 * Created by ${符柱成} on 2016/12/10.
 */
public class AttentionBean {

    private String icon;
    private String title;


    public AttentionBean(String icon, String title) {
        this.icon = icon;
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
