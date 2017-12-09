package com.haoche666.buyer.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2017/12/9 0009.
 *
 * @author ZhangJieBo
 */

public class Buyer {
    /**
     * info : 操作成功！
     * status : 1
     * store : [{"car":[{"id":185,"img":"https://www.haoche666.com/Uploads/attachment/20171205/65a2332c27ea0f9362d7f9b103308789.png"}],"des":"51辆在售，58辆已售","id":1,"intro":"认证车行认证车行认证车行认证车行","name":"认证车行"},{"car":[],"des":"51辆在售，58辆已售","id":2,"intro":"","name":"认证车行"}]
     */

    private String info;
    private int status;
    private List<StoreBean> store;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<StoreBean> getStore() {
        return store;
    }

    public void setStore(List<StoreBean> store) {
        this.store = store;
    }

    public static class StoreBean {
        /**
         * car : [{"id":185,"img":"https://www.haoche666.com/Uploads/attachment/20171205/65a2332c27ea0f9362d7f9b103308789.png"}]
         * des : 51辆在售，58辆已售
         * id : 1
         * intro : 认证车行认证车行认证车行认证车行
         * name : 认证车行
         */

        private String des;
        private int id;
        private String intro;
        private String name;
        private List<CarBean> car;

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

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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
             * img : https://www.haoche666.com/Uploads/attachment/20171205/65a2332c27ea0f9362d7f9b103308789.png
             */

            private int id;
            private String img;

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
        }
    }
}
