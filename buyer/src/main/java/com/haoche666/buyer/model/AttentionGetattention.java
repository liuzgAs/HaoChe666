package com.haoche666.buyer.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhangjiebo on 2017/12/23/023.
 *
 * @author ZhangJieBo
 */

public class AttentionGetattention implements Serializable{
    /**
     * data : [{"des":"12.00万公里 | 2015年","id":3,"img":"https://www.haoche666.com/Uploads/attachment/20171219/c8023024a06442e12db6cb13368abe7b.jpg","is_sale":0,"title":"奥迪A6L 2015款 TFSI 纪念智领版"}]
     * info : 操作成功！
     * page : {"dataTotal":1,"page":1,"pageSize":10,"pageTotal":1}
     * status : 1
     */

    private String info;
    private PageBean page;
    private int status;
    private List<DataBean> data;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class PageBean implements Serializable{
        /**
         * dataTotal : 1
         * page : 1
         * pageSize : 10
         * pageTotal : 1
         */

        private int dataTotal;
        private int page;
        private int pageSize;
        private int pageTotal;

        public int getDataTotal() {
            return dataTotal;
        }

        public void setDataTotal(int dataTotal) {
            this.dataTotal = dataTotal;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPageTotal() {
            return pageTotal;
        }

        public void setPageTotal(int pageTotal) {
            this.pageTotal = pageTotal;
        }
    }

    public static class DataBean implements Serializable{
        /**
         * des : 12.00万公里 | 2015年
         * id : 3
         * img : https://www.haoche666.com/Uploads/attachment/20171219/c8023024a06442e12db6cb13368abe7b.jpg
         * is_sale : 0
         * title : 奥迪A6L 2015款 TFSI 纪念智领版
         */

        private String des;
        private String des2;
        private int id;
        private String img;
        private int is_sale;
        private int updates;
        private String title;
        private String cut_price;
        private String price;
        private List<CarBean> car;
        private boolean isSelect;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getCut_price() {
            return cut_price;
        }

        public void setCut_price(String cut_price) {
            this.cut_price = cut_price;
        }

        public List<CarBean> getCar() {
            return car;
        }

        public void setCar(List<CarBean> car) {
            this.car = car;
        }

        public String getDes2() {
            return des2;
        }

        public void setDes2(String des2) {
            this.des2 = des2;
        }

        public int getUpdates() {
            return updates;
        }

        public void setUpdates(int updates) {
            this.updates = updates;
        }


        public static class CarBean implements Serializable{
            private int id;
            private String img;
            private String price;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getIs_sale() {
            return is_sale;
        }

        public void setIs_sale(int is_sale) {
            this.is_sale = is_sale;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
