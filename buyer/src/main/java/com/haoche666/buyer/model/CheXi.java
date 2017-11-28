package com.haoche666.buyer.model;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zhangjiebo on 2017/11/15 0015.
 *
 * @author ZhangJieBo
 */
public class CheXi {
    /**
     * brandMap : {"A":[{"brandId":2,"brandName":"奥迪","englishName":"audi","findUri":null,"logoPath":"http://i1.chexun.net/images/2015/0113/16627/logo_100_100_65BC3B6C8188EE6FE49108EA4661AA7D.jpg"},{"brandId":789,"brandName":"阿斯顿·马丁","englishName":"AstonMartin","findUri":null,"logoPath":"http://i2.chexun.net/images/2015/0113/16627/logo_100_100_96D0974686288BC5D5049C84ABA81111.jpg"},{"brandId":910,"brandName":"阿尔法罗密欧","englishName":"AlfaRomeo","findUri":null,"logoPath":"http://i3.chexun.net/images/2015/0108/16620/logo_100_100_C266745653814FA3C15E8EB92DA0A5AC.jpg"}]}
     * companyMap : {"2":[{"companyId":81,"companyName":"一汽奥迪","englishName":"audi"},{"companyId":82,"companyName":"奥迪(进口)","englishName":"audi-1"}],"789":[{"companyId":521,"companyName":"阿斯顿·马丁","englishName":"AstonMartin"}],"910":[{"companyId":744,"companyName":"阿尔法罗密欧","englishName":"AlfaRomeo"}]}
     * seriesMap : {"521":[{"albumUpdateTime":1464234835356,"brandId":789,"companyId":521,"englishName":"db11","guidePriceMax":328.8,"guidePriceMin":236.8,"imageNum":30,"isDisassembly":1,"seriesFirstImage":"http://i0.chexun.net/images/2016/0526/18366/carother_252_190_8FCEFBFBC020BC7B3D8B08FBF7F5F3F1.jpg","seriesId":107396,"seriesName":"DB11","seriesWhiteImage":"http://i1.chexun.net/images/2016/0425/18162/car_252_190_A005D504170620E106497FE6A3AADBAD.jpg"}],"744":[{"albumUpdateTime":1500886499659,"brandId":910,"companyId":744,"englishName":"Stelvio","guidePriceMax":47.8,"guidePriceMin":41.8,"imageNum":31,"isDisassembly":1,"seriesFirstImage":"http://i0.chexun.net/images/2017/0724/23305/car_252_190_3276141D57FFEC8155E8EA02C0BE23B8.JPG","seriesId":109381,"seriesName":"Stelvio","seriesWhiteImage":"http://i3.chexun.net/images/2017/0728/23343/car_252_190_B2078DF67A3752EC6E666F56BD3E7919.jpg"},{"albumUpdateTime":1470119830387,"brandId":910,"companyId":744,"englishName":"giulia","guidePriceMax":102.8,"guidePriceMin":33.08,"imageNum":32,"isDisassembly":1,"seriesFirstImage":"http://i0.chexun.net/images/2016/0802/18826/carother_252_190_3DD2DA4E39ACA445D5BCB443BB8394D8.jpg","seriesId":107384,"seriesName":"Giulia","seriesWhiteImage":"http://i1.chexun.net/images/2016/0428/18189/car_252_190_9C9D803FBC0B0CA671371885C23EBB35.jpg"}],"81":[{"albumUpdateTime":1498010118034,"brandId":2,"companyId":81,"englishName":"audi-q5","guidePriceMax":57.17,"guidePriceMin":35.85,"imageNum":400,"isDisassembly":1,"seriesFirstImage":"http://i0.chexun.net/images/2016/1010/19183/car_252_190_998C0EF80E18F5EED3003C973051AFC1.jpg","seriesId":2102,"seriesName":"奥迪Q5","seriesWhiteImage":"http://i0.chexun.net/images/2015/0820/17065/car_252_190_8AE6AA3C7AA2A78453A25D9566849FA2.jpg"},{"albumUpdateTime":1498008650175,"brandId":2,"companyId":81,"englishName":"audi-a6","guidePriceMax":75.76,"guidePriceMin":0,"imageNum":738,"isDisassembly":1,"seriesFirstImage":"http://i1.chexun.net/images/2017/0621/23045/car_252_190_30C361C08E2A15CB6B446ABAE4410D22.jpg","seriesId":5315,"seriesName":"奥迪A6L","seriesWhiteImage":"http://i3.chexun.net/images/2015/0820/17064/car_252_190_CE62CDB53112BDF809BDB594B5E2E77B.jpg"},{"albumUpdateTime":1497951105007,"brandId":2,"companyId":81,"englishName":"audi-a4l","guidePriceMax":57.81,"guidePriceMin":27.28,"imageNum":1339,"isDisassembly":2,"seriesFirstImage":"http://i0.chexun.net/images/2017/0620/23043/car_252_190_105887C0F2629FB0CEEC3014ECADEDD3.jpg","seriesId":891,"seriesName":"奥迪A4L","seriesWhiteImage":"http://i3.chexun.net/images/2016/0809/18847/car_252_190_CE83E0765F442732C359D710698FE686.jpg"},{"albumUpdateTime":1483933399537,"brandId":2,"companyId":81,"englishName":"q3","guidePriceMax":42.88,"guidePriceMin":23.42,"imageNum":583,"isDisassembly":1,"seriesFirstImage":"http://i3.chexun.net/images/2016/1228/20177/car_252_190_E3F20C6630E008FC76546ADA4B7E2674.JPG","seriesId":101520,"seriesName":"奥迪Q3","seriesWhiteImage":"http://i3.chexun.net/images/2016/0528/18380/car_252_190_1D736DDAD98454B62A90D68EC9868D70.jpg"},{"albumUpdateTime":1450843789221,"brandId":2,"companyId":81,"englishName":"audi-a31","guidePriceMax":29.67,"guidePriceMin":18.49,"imageNum":607,"isDisassembly":2,"seriesFirstImage":"http://i2.chexun.net/images/2015/1130/17364/car_252_190_7AD6A6704C9340A88E1619F5B17D7DF9.jpg","seriesId":102103,"seriesName":"奥迪A3","seriesWhiteImage":"http://i2.chexun.net/images/2015/0820/17067/car_252_190_5BFF18D38D7796CE6B2713A7F8251E72.jpg"}],"82":[{"albumUpdateTime":1502348766907,"brandId":2,"companyId":82,"englishName":"audi-a5","guidePriceMax":67.99,"guidePriceMin":39.8,"imageNum":1094,"isDisassembly":1,"seriesFirstImage":"http://i1.chexun.net/images/2017/0728/23348/car_252_190_5E359C8F66802002088802FBFCB44C4A.jpg","seriesId":2,"seriesName":"奥迪A5","seriesWhiteImage":"http://i0.chexun.net/images/2016/0329/17984/car_252_190_1EA5B797D7E3300782ED45746751CC08.jpg"},{"albumUpdateTime":1496800175153,"brandId":2,"companyId":82,"englishName":"audi-a7","guidePriceMax":95.8,"guidePriceMin":59.8,"imageNum":768,"isDisassembly":1,"seriesFirstImage":"http://i2.chexun.net/images/2017/0607/22904/car_252_190_AFFC9DD32231CCAEA62D35A0B943BEE7.jpg","seriesId":3684,"seriesName":"奥迪A7","seriesWhiteImage":"http://i0.chexun.net/images/2016/0809/18847/car_252_190_501273FD8323C1A882E29D8F189C8AE1.jpg"},{"albumUpdateTime":1487211619870,"brandId":2,"companyId":82,"englishName":"audi-a4-1","guidePriceMax":46.88,"guidePriceMin":41.8,"imageNum":228,"isDisassembly":1,"seriesFirstImage":"http://i0.chexun.net/images/2016/0518/18313/car_252_190_C5516321DADE719FC538975CFD02A54B.jpg","seriesId":4165,"seriesName":"奥迪A4(进口)","seriesWhiteImage":"http://i0.chexun.net/images/2016/0802/18827/car_252_190_009F55494839974BBA4F0ECDAA83A084.jpg"},{"albumUpdateTime":1463552953020,"brandId":2,"companyId":82,"englishName":"sq5","guidePriceMax":66.8,"guidePriceMin":66.8,"imageNum":257,"isDisassembly":1,"seriesFirstImage":"http://i0.chexun.net/images/2016/0518/18315/car_252_190_B7F7515FECB69E7D579375B4AC1E2533.jpg","seriesId":102516,"seriesName":"奥迪SQ5","seriesWhiteImage":"http://i3.chexun.net/images/2015/0106/16617/car_252_190_8C35ED7B8AF29C3D524598723357C041.jpg"},{"albumUpdateTime":1463550667105,"brandId":2,"companyId":82,"englishName":"audis6","guidePriceMax":105.8,"guidePriceMin":99.98,"imageNum":139,"isDisassembly":1,"seriesFirstImage":"http://i3.chexun.net/images/2016/0518/18314/car_252_190_18B9EC71DEFE6BD0B253E59BAC7928B0.jpg","seriesId":102480,"seriesName":"奥迪S6","seriesWhiteImage":"http://i2.chexun.net/images/2016/0730/18816/car_252_190_E023898E7899097B5CC8FAFACD278ABA.jpg"},{"albumUpdateTime":1463549656449,"brandId":2,"companyId":82,"englishName":"audis5","guidePriceMax":85.8,"guidePriceMin":67.88,"imageNum":84,"isDisassembly":1,"seriesFirstImage":"http://i2.chexun.net/images/2016/0518/18314/car_252_190_D60A595E41EAAC75A713790745DF9388.jpg","seriesId":102468,"seriesName":"奥迪S5","seriesWhiteImage":"http://i2.chexun.net/images/2016/0831/18916/car_252_190_85F14BDBA2176AB28D329769FB04E395.jpg"},{"albumUpdateTime":1461601644040,"brandId":2,"companyId":82,"englishName":"audi-a6-1","guidePriceMax":63.8,"guidePriceMin":45.98,"imageNum":316,"isDisassembly":1,"seriesFirstImage":"http://i3.chexun.net/images/2015/1203/17457/car_252_190_261FE5787AF9CD35120A06BF84E3E0CF.jpg","seriesId":4166,"seriesName":"奥迪A6(进口)","seriesWhiteImage":"http://i3.chexun.net/images/2015/1130/17363/car_252_190_57372C2E5C42352352807279FE2152BA.jpg"},{"albumUpdateTime":1449123347932,"brandId":2,"companyId":82,"englishName":"audi-a8","guidePriceMax":291.9,"guidePriceMin":87.1,"imageNum":855,"isDisassembly":1,"seriesFirstImage":"http://i1.chexun.net/images/2015/1203/17459/car_252_190_108703EF7CF02A61E4384BD170A74C23.jpg","seriesId":611,"seriesName":"奥迪A8","seriesWhiteImage":"http://i0.chexun.net/images/2014/0825/15883/car_252_190_40BE26638CB95DD30088E749495AFD26.jpg"},{"albumUpdateTime":1448367826359,"brandId":2,"companyId":82,"englishName":"audi-tt","guidePriceMax":62.18,"guidePriceMin":51.9,"imageNum":706,"isDisassembly":1,"seriesFirstImage":"http://i1.chexun.net/images/2015/1124/17339/car_252_190_17DA82D06B5080780B7299D06D62F5E5.jpg","seriesId":8,"seriesName":"奥迪TT","seriesWhiteImage":"http://i2.chexun.net/images/2016/0518/18313/car_252_190_C1CF5F696577FA60499D41B8FF3BEA47.jpg"},{"albumUpdateTime":1447998657311,"brandId":2,"companyId":82,"englishName":"a6-allroad","guidePriceMax":59.98,"guidePriceMin":59.98,"imageNum":107,"isDisassembly":1,"seriesFirstImage":"http://i3.chexun.net/images/2015/0906/17106/car_252_190_BB1731B410C951261565FEE65F34311A.jpg","seriesId":106563,"seriesName":"奥迪A6-allroad","seriesWhiteImage":"http://i3.chexun.net/images/2015/1130/17363/car_252_190_6DA9044A9118F4C2CF9D0B311200B956.jpg"},{"albumUpdateTime":1447983237378,"brandId":2,"companyId":82,"englishName":"s3","guidePriceMax":39.98,"guidePriceMin":39.98,"imageNum":326,"isDisassembly":1,"seriesFirstImage":"http://i0.chexun.net/images/2015/0304/16700/car_252_190_C49EC4B381A2F71DC0E3A8D2F6A96147.jpg","seriesId":102514,"seriesName":"奥迪S3","seriesWhiteImage":"http://i3.chexun.net/images/2016/0831/18916/car_252_190_A9CAFE5BE6C1A162E105F26C733B59DC.jpg"}]}
     */

    private HashMap<String, List<CheXiBean>> brandMap;
    private HashMap<String, List<CompanyBean>> companyMap;
    private HashMap<String, List<SeriesBean>> seriesMap;

    public HashMap<String, List<CheXiBean>> getBrandMap() {
        return brandMap;
    }

    public void setBrandMap(HashMap<String, List<CheXiBean>> brandMap) {
        this.brandMap = brandMap;
    }

    public HashMap<String, List<CompanyBean>> getCompanyMap() {
        return companyMap;
    }

    public void setCompanyMap(HashMap<String, List<CompanyBean>> companyMap) {
        this.companyMap = companyMap;
    }

    public HashMap<String, List<SeriesBean>> getSeriesMap() {
        return seriesMap;
    }

    public void setSeriesMap(HashMap<String, List<SeriesBean>> seriesMap) {
        this.seriesMap = seriesMap;
    }

    public static class BrandMapBean {
        private List<CheXiBean> A;

        public List<CheXiBean> getA() {
            return A;
        }

        public void setA(List<CheXiBean> A) {
            this.A = A;
        }


    }

    public static class CheXiBean {
        /**
         * brandId : 2
         * brandName : 奥迪
         * englishName : audi
         * findUri : null
         * logoPath : http://i1.chexun.net/images/2015/0113/16627/logo_100_100_65BC3B6C8188EE6FE49108EA4661AA7D.jpg
         */

        private int brandId;
        private String brandName;
        private String englishName;
        private Object findUri;
        private String logoPath;

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

        public String getEnglishName() {
            return englishName;
        }

        public void setEnglishName(String englishName) {
            this.englishName = englishName;
        }

        public Object getFindUri() {
            return findUri;
        }

        public void setFindUri(Object findUri) {
            this.findUri = findUri;
        }

        public String getLogoPath() {
            return logoPath;
        }

        public void setLogoPath(String logoPath) {
            this.logoPath = logoPath;
        }
    }

    public static class CompanyBean {
        /**
         * companyId : 81
         * companyName : 一汽奥迪
         * englishName : audi
         */

        private int companyId;
        private String companyName;
        private String englishName;

        public int getCompanyId() {
            return companyId;
        }

        public void setCompanyId(int companyId) {
            this.companyId = companyId;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getEnglishName() {
            return englishName;
        }

        public void setEnglishName(String englishName) {
            this.englishName = englishName;
        }
    }

    public static class SeriesBean {
        /**
         * albumUpdateTime : 1464234835356
         * brandId : 789
         * companyId : 521
         * englishName : db11
         * guidePriceMax : 328.8
         * guidePriceMin : 236.8
         * imageNum : 30
         * isDisassembly : 1
         * seriesFirstImage : http://i0.chexun.net/images/2016/0526/18366/carother_252_190_8FCEFBFBC020BC7B3D8B08FBF7F5F3F1.jpg
         * seriesId : 107396
         * seriesName : DB11
         * seriesWhiteImage : http://i1.chexun.net/images/2016/0425/18162/car_252_190_A005D504170620E106497FE6A3AADBAD.jpg
         */

        private long albumUpdateTime;
        private int brandId;
        private int companyId;
        private String englishName;
        private double guidePriceMax;
        private double guidePriceMin;
        private int imageNum;
        private int isDisassembly;
        private String seriesFirstImage;
        private int seriesId;
        private String seriesName;
        private String seriesWhiteImage;
        private String companyName;

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public long getAlbumUpdateTime() {
            return albumUpdateTime;
        }

        public void setAlbumUpdateTime(long albumUpdateTime) {
            this.albumUpdateTime = albumUpdateTime;
        }

        public int getBrandId() {
            return brandId;
        }

        public void setBrandId(int brandId) {
            this.brandId = brandId;
        }

        public int getCompanyId() {
            return companyId;
        }

        public void setCompanyId(int companyId) {
            this.companyId = companyId;
        }

        public String getEnglishName() {
            return englishName;
        }

        public void setEnglishName(String englishName) {
            this.englishName = englishName;
        }

        public double getGuidePriceMax() {
            return guidePriceMax;
        }

        public void setGuidePriceMax(double guidePriceMax) {
            this.guidePriceMax = guidePriceMax;
        }

        public double getGuidePriceMin() {
            return guidePriceMin;
        }

        public void setGuidePriceMin(double guidePriceMin) {
            this.guidePriceMin = guidePriceMin;
        }

        public int getImageNum() {
            return imageNum;
        }

        public void setImageNum(int imageNum) {
            this.imageNum = imageNum;
        }

        public int getIsDisassembly() {
            return isDisassembly;
        }

        public void setIsDisassembly(int isDisassembly) {
            this.isDisassembly = isDisassembly;
        }

        public String getSeriesFirstImage() {
            return seriesFirstImage;
        }

        public void setSeriesFirstImage(String seriesFirstImage) {
            this.seriesFirstImage = seriesFirstImage;
        }

        public int getSeriesId() {
            return seriesId;
        }

        public void setSeriesId(int seriesId) {
            this.seriesId = seriesId;
        }

        public String getSeriesName() {
            return seriesName;
        }

        public void setSeriesName(String seriesName) {
            this.seriesName = seriesName;
        }

        public String getSeriesWhiteImage() {
            return seriesWhiteImage;
        }

        public void setSeriesWhiteImage(String seriesWhiteImage) {
            this.seriesWhiteImage = seriesWhiteImage;
        }
    }

}
