package com.haoche666.buyer.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zhangjiebo on 2017/12/26/026.
 *
 * @author ZhangJieBo
 */

public class PayWxpay {
    /**
     * status : 1
     * info : success
     * appid : wx51d4b5b33bc07fa7
     * partnerid : 1494797672
     * prepayid : wx20171226091627fec72a7bb70407243417
     * nonceStr : uLgzBVRJg6m9ZJB0
     * package : Sign=WXPay
     * sign : 84E2C3BA0A24C89B15BCE9359959F624
     * timeStamp : 1514250987
     */

    private int status;
    private String info;
    private String appid;
    private String partnerid;
    private String prepayid;
    private String nonceStr;
    @SerializedName("package")
    private String packageX;
    private String sign;
    private int timeStamp;

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

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public int getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(int timeStamp) {
        this.timeStamp = timeStamp;
    }
}
