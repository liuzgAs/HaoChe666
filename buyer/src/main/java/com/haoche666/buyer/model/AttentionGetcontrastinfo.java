package com.haoche666.buyer.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/1/8/008.
 *
 * @author ZhangJieBo
 */

public class AttentionGetcontrastinfo {
    /**
     * data : [{"img":"https://www.haoche666.com/Uploads/attachment/20180104/1dddf19ee9419c1694eb9697072e5660.jpg","params_v":[["4.00万","9.58万","9.50万公里","3年","北京","现代"],["北京现代","合资","领动","领动(16/03-)","XDA1AK01","AP_4028b2b653a2f3280153bba629682ab6","2016款 北京现代 领动 三厢 1.6L 手自一体 智炫精英型 (BH7167TAV)","北京现代BH7167TAV轿车","1.591","2016","手自一体","直喷","直喷","北京现代G4FD","前置前驱","手自一体 智炫·精英型 GLX 国Ⅴ","1","119800","103800","5","智炫精英型","201603","4610*1800*1450","2700","6","1317","95.3","国五","优雅白"]],"title":"2016款 北京现代 领动 三厢 1.6L 手自一体 智炫精英型 (BH7167TAV)"}]
     * info : 获取成功！
     * left_nav : [{"item_name":"主要参数","item_v":["车主报价","新车购置价","行驶里程","车龄","车牌城市","车牌号"]},{"item_name":"基本参数","item_v":["品牌名称","国产/进口","车系","车组名称","车组编码","车型代码，唯一标识","车型名称","车型俗称","排量","年款","变速箱类型","供油方式","燃油类型","发动机型号","驱动形式","备注","是否有更多配置","厂商指导价","新车购置价","座位数","配置等级","上市年份","外形尺寸","轴距","变速器档数","整备质量（千克）","功率","排放标准","车身颜色"]}]
     * status : 1
     */

    private String info;
    private int status;
    private List<DataBean> data;
    private List<LeftNavBean> left_nav;

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

    public List<LeftNavBean> getLeft_nav() {
        return left_nav;
    }

    public void setLeft_nav(List<LeftNavBean> left_nav) {
        this.left_nav = left_nav;
    }

    public static class DataBean {
        /**
         * img : https://www.haoche666.com/Uploads/attachment/20180104/1dddf19ee9419c1694eb9697072e5660.jpg
         * params_v : [["4.00万","9.58万","9.50万公里","3年","北京","现代"],["北京现代","合资","领动","领动(16/03-)","XDA1AK01","AP_4028b2b653a2f3280153bba629682ab6","2016款 北京现代 领动 三厢 1.6L 手自一体 智炫精英型 (BH7167TAV)","北京现代BH7167TAV轿车","1.591","2016","手自一体","直喷","直喷","北京现代G4FD","前置前驱","手自一体 智炫·精英型 GLX 国Ⅴ","1","119800","103800","5","智炫精英型","201603","4610*1800*1450","2700","6","1317","95.3","国五","优雅白"]]
         * title : 2016款 北京现代 领动 三厢 1.6L 手自一体 智炫精英型 (BH7167TAV)
         */

        private String img;
        private String title;
        private List<List<String>> params_v;
        private List<List<Boolean>> params_vBoolean;

        public List<List<Boolean>> getParams_vBoolean() {
            return params_vBoolean;
        }

        public void setParams_vBoolean(List<List<Boolean>> params_vBoolean) {
            this.params_vBoolean = params_vBoolean;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<List<String>> getParams_v() {
            return params_v;
        }

        public void setParams_v(List<List<String>> params_v) {
            this.params_v = params_v;
        }
    }

    public static class LeftNavBean {
        /**
         * item_name : 主要参数
         * item_v : ["车主报价","新车购置价","行驶里程","车龄","车牌城市","车牌号"]
         */

        private String item_name;
        private List<String> item_v;

        public String getItem_name() {
            return item_name;
        }

        public void setItem_name(String item_name) {
            this.item_name = item_name;
        }

        public List<String> getItem_v() {
            return item_v;
        }

        public void setItem_v(List<String> item_v) {
            this.item_v = item_v;
        }
    }
}
