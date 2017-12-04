package com.haoche666.buyer.constant;


import huisedebi.zjb.mylibrary.util.MD5Util;

/**
 * Created by zjb on 2016/6/12.
 */
public class Constant {
    public static String HOST = "http://" + MD5Util.getMD5Time();
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
    }

    public static class IntentKey {
        public static final String TYPE = "type";
        public static final String POSITION = "position";
    }

    public static class RequestResultCode {
        public static final int IMAGE_PICKER = 2029;
    }

    public static class Acache {
        public static final String APP = "app";
        public static final String USER_INFO = "userInfo";
        public static final String TOKENTIME = "tokentime";
    }

    public static class BroadcastCode {
        public static final String CHE_LIANG_BIAN_JI_DIALOG = "che_liang_bian_ji_dialog";
    }


}
