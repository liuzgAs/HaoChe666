package com.haoche666.buyer.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2017/12/9 0009.
 *
 * @author ZhangJieBo
 */

public class Store {
    /**
     * data : [{"address":"厦门市","car":[{"id":185,"price":"3.50"}],"id":1,"img":"https://www.haoche666.com/Uploads/attachment/20171205/65a2332c27ea0f9362d7f9b103308789.png","lat":"24.556","lng":"112.369","logo":"https://www.haoche666.com/Uploads/attachment/20171205/65a2332c27ea0f9362d7f9b103308789.png","name":"认证车行","tel":"10086","text":"在售265辆｜已售12辆"}]
     * info : 返回成功！
     * page : {"dataTotal":1,"page":"1","pageSize":10,"pageTotal":1}
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
         * dataTotal : 1
         * page : 1
         * pageSize : 10
         * pageTotal : 1
         */

        private int dataTotal;
        private String page;
        private int pageSize;
        private int pageTotal;

        public int getDataTotal() {
            return dataTotal;
        }

        public void setDataTotal(int dataTotal) {
            this.dataTotal = dataTotal;
        }

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
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
         * address : 厦门市
         * car : [{"id":185,"price":"3.50"}]
         * id : 1
         * img : https://www.haoche666.com/Uploads/attachment/20171205/65a2332c27ea0f9362d7f9b103308789.png
         * lat : 24.556
         * lng : 112.369
         * logo : https://www.haoche666.com/Uploads/attachment/20171205/65a2332c27ea0f9362d7f9b103308789.png
         * name : 认证车行
         * tel : 10086
         * text : 在售265辆｜已售12辆
         */

        private String address;
        private int id;
        private String img;
        private String lat;
        private String lng;
        private String logo;
        private String name;
        private String tel;
        private String text;
        private List<CarBean> car;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public List<CarBean> getCar() {
            return car;
        }

        public void setCar(List<CarBean> car) {
            this.car = car;
        }

        public static class CarBean {
            /**
             * id : 185
             * price : 3.50
             */

            private int id;
            private String price;
            private String img;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }
        }
    }
}
