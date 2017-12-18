package hudongchuangxiang.haoche666.seller.model;

import java.io.Serializable;

/**
 * Created by zhangjiebo on 2017/12/14 0014.
 *
 * @author ZhangJieBo
 */

public class UserApplybefore implements Serializable{
    /**
     * data : {"address":"","area":"","company":"","head":0,"headUrl":"","img":0,"imgUrl":"","mobile":"","name":"","stock":""}
     * info : 返回成功！
     * state : 3
     * status : 1
     * tipsDes :
     * tipsTitle : 填写资料，使用好车666卖家版本
     */

    private DataBean data;
    private String info;
    private int state;
    private int status;
    private String tipsDes;
    private String tipsTitle;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTipsDes() {
        return tipsDes;
    }

    public void setTipsDes(String tipsDes) {
        this.tipsDes = tipsDes;
    }

    public String getTipsTitle() {
        return tipsTitle;
    }

    public void setTipsTitle(String tipsTitle) {
        this.tipsTitle = tipsTitle;
    }

    public static class DataBean implements Serializable{
        /**
         * address :
         * area :
         * company :
         * head : 0
         * headUrl :
         * img : 0
         * imgUrl :
         * mobile :
         * name :
         * stock :
         */

        private String address;
        private String area;
        private String company;
        private int head;
        private String headUrl;
        private int img;
        private String imgUrl;
        private String mobile;
        private String name;
        private String stock;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public int getHead() {
            return head;
        }

        public void setHead(int head) {
            this.head = head;
        }

        public String getHeadUrl() {
            return headUrl;
        }

        public void setHeadUrl(String headUrl) {
            this.headUrl = headUrl;
        }

        public int getImg() {
            return img;
        }

        public void setImg(int img) {
            this.img = img;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStock() {
            return stock;
        }

        public void setStock(String stock) {
            this.stock = stock;
        }
    }
}
