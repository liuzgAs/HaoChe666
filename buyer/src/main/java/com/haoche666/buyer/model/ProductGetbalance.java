package com.haoche666.buyer.model;

/**
 * Created by zhangjiebo on 2017/12/23/023.
 *
 * @author ZhangJieBo
 */

public class ProductGetbalance {
    /**
     * status : 1
     * info : 获取成功
     * balance : 0
     */

    private int status;
    private String info;
    private double balance;

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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
