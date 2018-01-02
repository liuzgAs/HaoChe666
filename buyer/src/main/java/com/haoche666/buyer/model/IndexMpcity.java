package com.haoche666.buyer.model;

/**
 * Created by zhangjiebo on 2018/1/2/002.
 *
 * @author ZhangJieBo
 */

public class IndexMpcity {
    /**
     * cityName : 厦门
     * cityId : 60
     * status : 1
     * info : 操作成功！
     */

    private String cityName;
    private int cityId;
    private int status;
    private String info;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

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
}
