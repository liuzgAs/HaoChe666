package com.haoche666.buyer.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/1/3/003.
 *
 * @author ZhangJieBo
 */

public class ProductGetbanner {

    /**
     * status : 1
     * info : 返回成功！
     * banner : [{"img":"https://www.haoche666.com/Uploads/attachment/20171205/c4060bd44de90944fa8c5eca2f95f69a.jpg","url":""},{"img":"https://www.haoche666.com/Uploads/attachment/20171205/c4060bd44de90944fa8c5eca2f95f69a.jpg","url":""},{"img":"https://www.haoche666.com/Uploads/attachment/20171205/c4060bd44de90944fa8c5eca2f95f69a.jpg","url":""}]
     */

    private int status;
    private String info;
    private List<BannerBean> banner;

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

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public static class BannerBean {
        /**
         * img : https://www.haoche666.com/Uploads/attachment/20171205/c4060bd44de90944fa8c5eca2f95f69a.jpg
         * url :
         */

        private String img;
        private String url;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
