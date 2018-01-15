package com.haoche666.buyer.model;

/**
 * Created by zhangjiebo on 2018/1/15/015.
 *
 * @author ZhangJieBo
 */

public class SellerPoster {

    /**
     * img : https://www.haoche666.com/Uploads/hb/car_1.jpg
     * status : 1
     * info : 操作成功！
     */

    private String img;
    private int status;
    private String info;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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
