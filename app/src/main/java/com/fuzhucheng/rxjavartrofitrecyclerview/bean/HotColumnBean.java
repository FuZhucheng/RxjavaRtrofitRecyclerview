package com.fuzhucheng.rxjavartrofitrecyclerview.bean;

/**
 * Created by ${符柱成} on 2016/12/9.
 */

public class HotColumnBean {
    private String image;
    private boolean isComMeg;


    public HotColumnBean(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isComMeg() {
        return isComMeg;
    }

    public void setComMeg(boolean comMeg) {
        isComMeg = comMeg;
    }
}
