package com.haoche666.buyer.constant;


/**
 * Created by zjb on 2016/6/12.
 */
public class Constant {
    public static String HOST = "https://www.haoche666.com/api";
    /**
     * 判断数据是否有改变
     */
    public static int changeControl = 2017;
    /**
     * 微信appid
     */
    public static String WXAPPID = "wx51d4b5b33bc07fa7";
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
        /**
         * 车辆详情
         */
        public static final String CAR_DETAILS = "/Car/details";
        /**
         * 我的
         */
        public static final String USER_BUYERINDEX = "/User/buyerIndex";
        /**
         * 修改昵称和头像
         */
        public static final String USER_SVAEINFO = "/User/svaeInfo";
        /**
         * 单图上传
         */
        public static final String RESPOND_APPIMGADD = "/Respond/appImgAdd";
        /**
         * 文章列表
         */
        public static final String ARTICLE = "/Article";
        /**
         * 足迹车辆
         */
        public static final String CAR_HISTORY = "/Car/history";
        /**
         * 足迹文章
         */
        public static final String ARTICLE_HISTORY = "/Article/history";
        /**
         * 常见问题
         */
        public static final String FAQ = "/Faq";
        /**
         * 意见反馈
         */
        public static final String USER_FEEDBACK = "/User/feedback";
        /**
         * 修改密码
         */
        public static final String USER_PWDSAVE = "/User/pwdSave";
        /**
         * 版本判断
         */
        public static final String INDEX_VERSION = "/Index/version";
        /**
         * 买车页面
         */
        public static final String CAR = "/Car";
        /**
         * 添加关注车辆/车行 1-车辆；2-车行
         */
        public static final String ATTENTION = "/Attention";
        /**
         * 车辆品牌用途颜色
         */
        public static final String CAR_CARPARAM = "/Car/carParam";
        /**
         * 车辆款式
         */
        public static final String CAR_CARSTYLE = "/Car/carStyle";
        /**
         * 获取我的关注
         */
        public static final String ATTENTION_GETATTENTION = "/Attention/getAttention";
        /**
         * 创建订单(查询单)
         */
        public static final String CORDER_CREATEORDER = "/corder/createOrder";
        /**
         * 获取付费类型信息（会员\查询）
         */
        public static final String PRODUCT = "/product";
        /**
         * 余额获取
         */
        public static final String PRODUCT_GETBALANCE = "/product/getbalance";
        /**
         * 余额支付
         */
        public static final String PAY_BALANCEPAY = "/pay/balancePay";
        /**
         * 支付宝支付
         */
        public static final String PAY_ALIPAY = "/pay/alipay";
        /**
         * 创建充值订单
         */
        public static final String CORDER_RECHARGE = "/corder/recharge";
        /**
         * 微信支付
         */
        public static final String PAY_WXPAY = "/pay/wxpay";
        /**
         * 获取消费明细
         */
        public static final String CORDER_GETCONSUMEDETAIL = "/corder/getConsumeDetail";
        /**
         * 查询历史获取
         */
        public static final String PRODUCT_QUERYHISTORY = "/product/queryHistory";
        /**
         * 获取价格、车龄范围数组（搜索）
         */
        public static final String CAR_GETSEARCHDATA = "/car/getSearchData";
        /**
         * 获取订单列表
         */
        public static final String CORDER = "/corder";
        /**
         * 获取对比参数
         */
        public static final String ATTENTION_GETCONTRASTINFO = "/Attention/getContrastInfo";
    }

    public static class IntentKey {
        public static final String TYPE = "type";
        public static final String POSITION = "position";
        public static final String PHONE = "phone";
        public static final String ID = "id";
        public static final String BSID = "bsid";
        public static final String VALUE = "value";
        public static final String BEAN = "bean";
        public static final String NICKNAME = "nickName";
        public static final String URL = "url";
        public static final String TITLE = "title";
        public static final String NAME = "name";
        public static final String BRAND = "brand";
    }

    public static class RequestResultCode {
        public static final int IMAGE_PICKER = 2029;
        public static final int PIN_PAI = 2030;
        public static final int BRAND = 2031;
        public static final int MAICHE = 2032;
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
        public static final String USERINFO = "userInfo";
        public static final String CHE_HANG_GUAN_ZHU = "che_hang_guan_zhu";
        public static final String SHOU_YE_CHE_HANG = "shou_ye_che_hang";
        public static final String PAY_RECEIVER = "pay_receiver";
        public static final String WX_LOGIN = "wxLogin";
        public static final String WX_SHARE = "wxShare";
        public static final String WX_LOGIN_FAIL = "wxLoginFail";
        public static final String WX_SHARE_FAIL = "wxShareFail";
        public static final String CHONG_ZHI = "chongZhi";
    }


}
