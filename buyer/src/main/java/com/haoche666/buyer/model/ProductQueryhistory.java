package com.haoche666.buyer.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2017/12/26/026.
 *
 * @author ZhangJieBo
 */

public class ProductQueryhistory {

    /**
     * data : [{"create_time":"2017-12-26","des":"奥迪 | 265864412658666","id":48,"price":0.01,"vin":"265864412658666"},{"create_time":"2017-12-26","des":"奥迪 | 55569645526566666","id":47,"price":0.01,"vin":"55569645526566666"},{"create_time":"2017-12-26","des":"奥迪 | 55569645526566666","id":46,"price":0.01,"vin":"55569645526566666"},{"create_time":"2017-12-25","des":"奥迪 | 52366","id":45,"price":0.01,"vin":"52366"},{"create_time":"2017-12-25","des":"奥迪 | 566","id":43,"price":0.01,"vin":"566"},{"create_time":"2017-12-25","des":"安驰 | 552222","id":42,"price":0.01,"vin":"552222"},{"create_time":"2017-12-25","des":"奥迪 | 666996","id":41,"price":0.01,"vin":"666996"},{"create_time":"2017-12-25","des":"奥迪 | 663666","id":40,"price":0.01,"vin":"663666"},{"create_time":"2017-12-25","des":"奥迪 | 663666","id":39,"price":0.01,"vin":"663666"},{"create_time":"2017-12-25","des":"奥迪 | 66563","id":38,"price":0.01,"vin":"66563"}]
     * info : 获取成功!
     * page : {"dataTotal":29,"page":1,"pageSize":10,"pageTotal":3}
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

    public static class PageBean {
        /**
         * dataTotal : 29
         * page : 1
         * pageSize : 10
         * pageTotal : 3
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

    public static class DataBean {
        /**
         * create_time : 2017-12-26
         * des : 奥迪 | 265864412658666
         * id : 48
         * price : 0.01
         * vin : 265864412658666
         */

        private String create_time;
        private String des;
        private int id;
        private double price;
        private String vin;

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
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

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getVin() {
            return vin;
        }

        public void setVin(String vin) {
            this.vin = vin;
        }
    }
}
