package com.haoche666.buyer.model;

/**
 * Created by zhangjiebo on 2017/12/27/027.
 *
 * @author ZhangJieBo
 */

public class Carsearch {
    /**
     * message : 获取短信成功
     * statue : 1
     */

    private String info;
    private String url;
    private int status;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
