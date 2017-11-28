package com.haoche666.buyer.model;

/**
 * Created by zhangjiebo on 2017/11/15 0015.
 *
 * @author ZhangJieBo
 */
public class ReMen {
    private String brandName;
    private String logoPath ;
    private String letter;
    private int brandId;

    public ReMen(String brandName, String logoPath, String letter, int brandId) {
        this.brandName = brandName;
        this.logoPath = logoPath;
        this.letter = letter;
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }
}
