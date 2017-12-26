package com.haoche666.buyer.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2017/12/22/022.
 *
 * @author ZhangJieBo
 */

public class DuiBICanShu {
    private int loginType;
    private String platform;
    private String uid;
    private String tokenTime;
    private List<Integer> arr_cid ;

    public DuiBICanShu(int loginType, String platform, String uid, String tokenTime, List<Integer> arr_cid) {
        this.loginType = loginType;
        this.platform = platform;
        this.uid = uid;
        this.tokenTime = tokenTime;
        this.arr_cid = arr_cid;
    }

    public int getLoginType() {
        return loginType;
    }

    public void setLoginType(int loginType) {
        this.loginType = loginType;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTokenTime() {
        return tokenTime;
    }

    public void setTokenTime(String tokenTime) {
        this.tokenTime = tokenTime;
    }

    public List<Integer> getArr_cid() {
        return arr_cid;
    }

    public void setArr_cid(List<Integer> arr_cid) {
        this.arr_cid = arr_cid;
    }
}
