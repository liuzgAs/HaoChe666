package com.haoche666.buyer.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2017/12/22/022.
 *
 * @author ZhangJieBo
 */

public class MaiChe {
    private int loginType;
    private String platform;
    private String uid;
    private String tokenTime;
    private int p;
    private int bid;
    private int bsid;
    private int sort_id;
    private int hotcat_id;
    private int city_id;
    private List<Integer> z_price ;
    private List<Integer> z_age ;
    private String title;

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public int getHotcat_id() {
        return hotcat_id;
    }

    public void setHotcat_id(int hotcat_id) {
        this.hotcat_id = hotcat_id;
    }

    public int getSort_id() {
        return sort_id;
    }

    public void setSort_id(int sort_id) {
        this.sort_id = sort_id;
    }

    public MaiChe(int loginType, String platform, String uid, String tokenTime, int p, int bid,int bsid, int sort_id,int hotcat_id,int city_id, List<Integer> z_price, List<Integer> z_age, String title) {
        this.loginType = loginType;
        this.platform = platform;
        this.uid = uid;
        this.tokenTime = tokenTime;
        this.p = p;
        this.bid = bid;
        this.bsid = bsid;
        this.sort_id = sort_id;
        this.hotcat_id = hotcat_id;
        this.city_id = city_id;
        this.z_price = z_price;
        this.z_age = z_age;
        this.title = title;
    }

    public MaiChe(int loginType, String platform, int p, int bid,int bsid,  int sort_id, int hotcat_id,int city_id, List<Integer> z_price, List<Integer> z_age, String title) {
        this.loginType = loginType;
        this.platform = platform;
        this.p = p;
        this.bid = bid;
        this.bsid = bsid;
        this.sort_id = sort_id;
        this.hotcat_id = hotcat_id;
        this.city_id = city_id;
        this.z_price = z_price;
        this.z_age = z_age;
        this.title = title;
    }

    public int getBsid() {
        return bsid;
    }

    public void setBsid(int bsid) {
        this.bsid = bsid;
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

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public List<Integer> getZ_price() {
        return z_price;
    }

    public void setZ_price(List<Integer> z_price) {
        this.z_price = z_price;
    }

    public List<Integer> getZ_age() {
        return z_age;
    }

    public void setZ_age(List<Integer> z_age) {
        this.z_age = z_age;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
