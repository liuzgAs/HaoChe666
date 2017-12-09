package com.haoche666.buyer.constant;


/**
 * Created by zjb on 2016/6/12.
 */
public class Constant {
    public static String HOST = "http://api.haoche666.com";
    /**
     * 判断数据是否有改变
     */
    public static int changeControl = 2017;
    /**
     * 微信appid
     */
    public static String WXAPPID = "wxfef0031f5d8f3ed0";
    /**
     * 微信scrent
     */
    public static String WXSCRENT = "02d7e0ca570f95630b552bd055fdd14a";
    /**
     * qq
     */
    public static String QQ_ID = "1106239952";
    public static String QQ_KEY = "HcA9s2rpKkLO2M5w";

    public static class Url {
        /**
         * 车讯网数据
         */
        public static final String CHE_XUN = "http://auto.chexun.com/api/car/brand.do";
        /**
         * 车系查询
         */
        public static final String CHE_XI_CX = "http://auto.chexun.com/api/car/seriesByLetter.do?letter=";
        /**
         * 升级
         */
        public static final String INDEX_VERSION = "";
        /**
         * 注册
         */
        public static final String LOGIN_REGISTER = "/Login/register";
        /**
         * 注册发送验证码
         */
        public static final String LOGIN_REGSMS = "/Login/regSms";
        /**
         * 验证码登录
         */
        public static final String LOGIN_SMS = "/Login/sms";
        /**
         * 忘记验证码请求
         */
        public static final String LOGIN_FORGETSMS = "/Login/forgetSms";
        /**
         * 用户登录
         */
        public static final String LOGIN_INDEX = "/Login/index";
        /**
         * 开机记录设备
         */
        public static final String INDEX_START = "/Index/start";
        /**
         * 忘记密码
         */
        public static final String LOGIN_FORGET = "/Login/forget";
        /**
         * 买家版主页
         */
        public static final String BUYER = "/Buyer";
        /**
         * 车行详情
         */
        public static final String STORE_DETAILS = "/Store/details";
        /**
         * 车行列表
         */
        public static final String STORE = "/Store";
    }

    public static class IntentKey {
        public static final String TYPE = "type";
        public static final String POSITION = "position";
        public static final String PHONE = "phone";
        public static final String ID = "id";
    }

    public static class RequestResultCode {
        public static final int IMAGE_PICKER = 2029;
    }

    public static class Acache {
        public static final String APP = "app";
        public static final String USER_INFO = "userInfo";
        public static final String TOKENTIME = "tokentime";
        public static final String LOCATION = "location";
        public static final String LAT = "lat";
        public static final String LNG = "lng";
        public static final String CITY = "city";
        public static final String CITY_ID = "city_id";
        public static final String DID = "did";
        public static final String FRIST = "frist";
    }

    public static class BroadcastCode {
        public static final String CHE_LIANG_BIAN_JI_DIALOG = "che_liang_bian_ji_dialog";
    }


}
