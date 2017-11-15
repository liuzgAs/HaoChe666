package com.haoche666.buyer.constant;


import com.haoche666.buyer.util.AppUtil;

/**
 * Created by zjb on 2016/6/12.
 */
public class Constant {
    public static String HOST = "http://" + AppUtil.getMD5Time();
    public static int changeControl = 2017;//判断数据是否有改变
    public static String WXAPPID = "wxfef0031f5d8f3ed0";//微信appid
    public static String WXSCRENT = "02d7e0ca570f95630b552bd055fdd14a";//微信scrent
    public static String QQ_ID = "1106239952";//qq
    public static String QQ_KEY = "HcA9s2rpKkLO2M5w";//qq

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

    public static class PERMISSION {
        public static final int ACCESS_COARSE_LOCATION = 0;
        public static final int ACCESS_FINE_LOCATION = 1;
        public static final int WRITE_EXTERNAL_STORAGE = 2;
        public static final int READ_EXTERNAL_STORAGE = 3;
        public static final int READ_PHONE_STATE = 4;
        public static final int PERMISSION_READ_SMS = 5;
        public static final int RECEIVE_BOOT_COMPLETED = 6;
        public static final int RECEIVE_WRITE_SETTINGS = 7;
        public static final int RECEIVE_VIBRATE = 8;
        public static final int RECEIVE_DISABLE_KEYGUARD = 9;
        public static final int CALL_PHONE = 10;
        public static final int SYSTEM_ALERT_WINDOW = 11;
        public static final int PERMISSION_WRITE_EXTERNAL_STORAGE_SSENGJI = 12;
        public static final int CAMERA = 13;
        public static final int MOUNT_UNMOUNT_FILESYSTEMS = 14;
        public static final int READ_CONTACTS = 15;

    }

    public static class INTENT_KEY {
        public static final String TITLE = "title";
        public static final String PHONE = "phone";
        public static final String amount = "amount";
        public static final String type = "type";
        public static final String id = "id";
        public static final String tongDaoId = "tongDaoId";
        public static final String img = "img";
        public static final String value = "value";
        public static final String URL = "URL";
        public static final String EXTRAMAP = "ExtraMap";
        public static final String CITY = "city";
        public static final String position = "position";
        public static final String BIG_IMG_POSITION = "bigImgPosition";
        public static final String BIG_IMG = "bigImg";
        public static final String shezhi = "shezhi";
        public static final String isFrist = "isFrist";
        public static final String Main = "Main";
    }

    public static class REQUEST_RESULT_CODE {
        public static final int IMG01 = 2018;
        public static final int IMG02 = 2019;
        public static final int IMG03 = 2020;
        public static final int IMG04 = 2021;
        public static final int IMG05 = 2022;
        public static final int XIN_YONG_KA = 2023;
        public static final int IMAGE_HEAD = 2024;
        public static final int IMAGE_WX = 2025;
        public static final int BaoCun = 2026;
        public static final int address = 2027;
        public static final int IMAGE_DIANZHAO = 2028;
        public static final int IMAGE_PICKER = 2029;
    }

    public static class ACACHE {
        public static final String App = "app";
        public static final String USER_INFO = "userInfo";
        public static final String TOKENTIME = "tokentime";
        public static final String FRIST = "frist";
        public static final String LOCATION = "location";
        public static final String LAT = "lat";
        public static final String LNG = "lng";
        public static final String CITY = "city";
        public static final String CITY_ID = "cityId";
        public static final String PAINT_PASSWORD = "paintPassword";
    }

    public static class BROADCASTCODE {
        public static final String NearFilter01 = "NearFilter01";
        public static final String PAY_RECEIVER = "pay_receiver";
        public static final String WX_LOGIN = "wxLogin";
        public static final String WX_SHARE = "wxShare";
        public static final String WX_LOGIN_FAIL = "wxLoginFail";
        public static final String WX_SHARE_FAIL = "wxShareFail";
        public static final String EXTRAMAP = "extramap";
        public static final String CITY_CHOOSE = "cityChoose";
        public static final String ShangJia01 = "ShangJia01";
        public static final String ShangJia02 = "ShangJia02";
        public static final String address = "address";
        public static final String zhiFuGuanBi = "zhiFuGuanBi";
        public static final String ShuaXinDingDan = "ShuaXinDingDan";
        public static final String ShuaXinYongJin = "ShuaXinYongJin";
        public static final String ShuaXinWoDeDP = "ShuaXinWoDeDP";
        public static final String FenXiangZCLJ = "FenXiangZCLJ";
        public static final String GouWuCheNum = "GouWuCheNum";
        public static final String GuanBiShouKuan = "GuanBiShouKuan";
        public static final String ShaiXuan = "ShaiXuan";
        public static final String FenXiangXiaZaiLJ = "FenXiangXiaZaiLJ";
        public static final String VIP = "VIP";
    }

}
