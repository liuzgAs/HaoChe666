package com.haoche666.buyer.model;

import java.io.Serializable;

/**
 * Created by zhangjiebo on 2017/12/12 0012.
 *
 * @author ZhangJieBo
 */

public class Location implements Serializable {
    private String name;
    private String address;
    private String lat;
    private String lng;

    public Location(String name, String address, String lat, String lng) {
        this.name = name;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
