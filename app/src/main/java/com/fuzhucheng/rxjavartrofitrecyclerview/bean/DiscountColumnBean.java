package com.fuzhucheng.rxjavartrofitrecyclerview.bean;

/**
 * Created by ${符柱成} on 2016/12/8.
 */
public class DiscountColumnBean {

    private int icon;
    private String name;
    private String content;

    public DiscountColumnBean(int icon, String name, String content) {
        this.icon = icon;
        this.name = name;
        this.content = content;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
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


}
