package com.haoche666.buyer.model;

/**
 * Created by zhangjiebo on 2017/12/12 0012.
 *
 * @author ZhangJieBo
 */

public class UserBuyerindex {
    /**
     * grade : 0
     * grade_name : 普通会员
     * headimg : https://www.haoche666.com/Uploads/attachment/20171205/65a2332c27ea0f9362d7f9b103308789.png
     * info : 返回成功！
     * mobile : 15871105320
     * money : 20.61
     * nickname : 15871105320
     * status : 1
     */

    private int grade;
    private String grade_name;
    private String headimg;
    private String info;
    private String mobile;
    private double money;
    private String nickname;
    private int status;

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getGrade_name() {
        return grade_name;
    }

    public void setGrade_name(String grade_name) {
        this.grade_name = grade_name;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
