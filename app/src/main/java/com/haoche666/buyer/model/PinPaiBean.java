package com.haoche666.buyer.model;

public class PinPaiBean {
    /**
     * brandId : 101180
     * brandName : ARCFOX
     * logoPath : http://i2.chexun.net/images/2017/0822/23555/logo_50_50_D56770C0D0C7ABF69795EE606FE3DCC1.jpg
     * englishName : arcfox
     * findUri : /search-a101180-b0-c0-d0-e0-f0-g0-h0-i0:0.html
     */

    private int brandId;
    private int index;
    private String brandName;
    private String letter;
    private String logoPath;
    private String englishName;
    private String findUri;

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
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

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getFindUri() {
        return findUri;
    }

    public void setFindUri(String findUri) {
        this.findUri = findUri;
    }
}