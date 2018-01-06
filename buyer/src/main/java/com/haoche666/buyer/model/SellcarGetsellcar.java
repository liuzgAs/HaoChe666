package com.haoche666.buyer.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/1/5/005.
 *
 * @author ZhangJieBo
 */

public class SellcarGetsellcar {
    /**
     * status : 1
     * info : 获取成功
     * data : [{"id":65,"name":"奥迪Q3","des":"300万公里 | 2038","grade":4,"status":1,"sell_city":"台湾","brand_city":"贺州"},{"id":66,"name":"奥迪Q3","des":"300万公里 | 2038","grade":4,"status":1,"sell_city":"巢湖","brand_city":"东方"}]
     */

    private int status;
    private String info;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 65
         * name : 奥迪Q3
         * des : 300万公里 | 2038
         * grade : 4
         * status : 1
         * sell_city : 台湾
         * brand_city : 贺州
         */

        private int id;
        private String name;
        private String des;
        private int grade;
        private int status;
        private String sell_city;
        private String brand_city;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public int getGrade() {
            return grade;
        }

        public void setGrade(int grade) {
            this.grade = grade;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getSell_city() {
            return sell_city;
        }

        public void setSell_city(String sell_city) {
            this.sell_city = sell_city;
        }

        public String getBrand_city() {
            return brand_city;
        }

        public void setBrand_city(String brand_city) {
            this.brand_city = brand_city;
        }
    }
}
