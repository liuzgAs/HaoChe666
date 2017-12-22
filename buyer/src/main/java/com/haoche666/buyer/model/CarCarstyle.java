package com.haoche666.buyer.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2017/12/22/022.
 *
 * @author ZhangJieBo
 */

public class CarCarstyle {
    /**
     * data : [{"id":1,"img":"https://www.haoche666.com/Uploads/car_style/1_0.jpeg","name":"奥迪Q3"},{"id":2,"img":"https://www.haoche666.com/Uploads/car_style/1_1.jpeg","name":"奥迪A3"},{"id":3,"img":"https://www.haoche666.com/Uploads/car_style/1_2.jpeg","name":"奥迪A6L"},{"id":4,"img":"https://www.haoche666.com/Uploads/car_style/1_3.jpeg","name":"奥迪A4L"},{"id":5,"img":"https://www.haoche666.com/Uploads/car_style/1_4.jpeg","name":"奥迪A8L"},{"id":6,"img":"https://www.haoche666.com/Uploads/car_style/1_5.jpeg","name":"奥迪TT"},{"id":7,"img":"https://www.haoche666.com/Uploads/car_style/1_6.jpeg","name":"奥迪Q7"},{"id":8,"img":"https://www.haoche666.com/Uploads/car_style/1_7.jpeg","name":"奥迪A7"},{"id":9,"img":"https://www.haoche666.com/Uploads/car_style/1_8.jpeg","name":"奥迪A6"},{"id":10,"img":"https://www.haoche666.com/Uploads/car_style/1_9.jpeg","name":"奥迪S7"},{"id":11,"img":"https://www.haoche666.com/Uploads/car_style/1_10.jpeg","name":"奥迪SQ5"},{"id":12,"img":"https://www.haoche666.com/Uploads/car_style/1_11.jpeg","name":"奥迪S5"},{"id":13,"img":"https://www.haoche666.com/Uploads/car_style/1_12.jpeg","name":"奥迪A1"},{"id":14,"img":"https://www.haoche666.com/Uploads/car_style/1_13.jpeg","name":"奥迪S8"},{"id":15,"img":"https://www.haoche666.com/Uploads/car_style/1_14.jpeg","name":"奥迪RS7"},{"id":16,"img":"https://www.haoche666.com/Uploads/car_style/1_15.jpeg","name":"奥迪A4"},{"id":17,"img":"https://www.haoche666.com/Uploads/car_style/1_16.jpeg","name":"奥迪R8"},{"id":18,"img":"https://www.haoche666.com/Uploads/car_style/1_17.jpeg","name":"奥迪A8"},{"id":19,"img":"https://www.haoche666.com/Uploads/car_style/1_18.jpeg","name":"奥迪TTS"},{"id":20,"img":"https://www.haoche666.com/Uploads/car_style/1_19.jpeg","name":"奥迪Q5"},{"id":21,"img":"https://www.haoche666.com/Uploads/car_style/1_20.jpeg","name":"奥迪A2"},{"id":22,"img":"https://www.haoche666.com/Uploads/car_style/1_21.jpeg","name":"奥迪RS5"},{"id":23,"img":"https://www.haoche666.com/Uploads/car_style/1_22.jpeg","name":"奥迪RS6"},{"id":24,"img":"https://www.haoche666.com/Uploads/car_style/1_23.jpeg","name":"奥迪200"},{"id":25,"img":"https://www.haoche666.com/Uploads/car_style/1_24.jpeg","name":"奥迪S3"},{"id":26,"img":"https://www.haoche666.com/Uploads/car_style/1_25.jpeg","name":"奥迪A5"},{"id":27,"img":"https://www.haoche666.com/Uploads/car_style/1_26.jpeg","name":"奥迪S6"}]
     * info : 操作成功！
     * status : 1
     */

    private String info;
    private int status;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * img : https://www.haoche666.com/Uploads/car_style/1_0.jpeg
         * name : 奥迪Q3
         */

        private int id;
        private String img;
        private String name;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
