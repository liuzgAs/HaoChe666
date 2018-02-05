package com.haoche666.buyer.model;

import java.io.Serializable;

/**
 * Created by zhangjiebo on 2018/2/5/005.
 *
 * @author ZhangJieBo
 */

public class ShareBean implements Serializable{
    private String img;
    private String title;
    private String des;
    private String share_url;

    public ShareBean(String img, String title, String des, String share_url) {
        this.img = img;
        this.title = title;
        this.des = des;
        this.share_url = share_url;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }
}
