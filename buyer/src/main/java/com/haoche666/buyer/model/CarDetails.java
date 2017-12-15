package com.haoche666.buyer.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2017/12/12 0012.
 *
 * @author ZhangJieBo
 */

public class CarDetails {
    /**
     * archives : [{"name":"2005年01月上牌","value":"12年11月"},{"name":"表显里程","value":"66.00万公里"},{"name":"排放标准","value":"未填写"},{"name":"上架时间","value":"6天前"},{"name":"涡轮增压","value":"1.6L"},{"name":"所在城市","value":"厦门"}]
     * banner : [{"img":"https://www.haoche666.com/Uploads/attachment/20171205/c4060bd44de90944fa8c5eca2f95f69a.jpg"}]
     * car : {"price":"3.50万","title":"东方车666","vr_url":""}
     * imgList : [{"h":307,"img":"https://www.haoche666.com/Uploads/attachment/20171205/65a2332c27ea0f9362d7f9b103308789.png","w":307},{"h":104,"img":"https://www.haoche666.com/Uploads/attachment/20171205/2aaab43c0149956102a10e26b6b802c7.jpg","w":139},{"h":565,"img":"https://www.haoche666.com/Uploads/attachment/20171205/c4060bd44de90944fa8c5eca2f95f69a.jpg","w":1200}]
     * info : 返回成功！
     * share : {"shareDes":"认证车行认证车行认证车行认证车行","shareImg":"https://www.haoche666.com/Uploads/attachment/20171205/65a2332c27ea0f9362d7f9b103308789.png","shareTitle":"东方车666","shareUrl":""}
     * status : 1
     * store : {"id":1,"intro":"认证车行认证车行认证车行认证车行","logo":"https://www.haoche666.com/Uploads/attachment/20171205/65a2332c27ea0f9362d7f9b103308789.png","name":"认证车行","tel":"10086"}
     * video : null
     */

    private CarBean car;
    private String info;
    private ShareBean share;
    private int status;
    private StoreBean store;
    private videoBean video;
    private List<ArchivesBean> archives;
    private List<BannerBean> banner;
    private List<ImgListBean> imgList;

    public CarBean getCar() {
        return car;
    }

    public void setCar(CarBean car) {
        this.car = car;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public ShareBean getShare() {
        return share;
    }

    public void setShare(ShareBean share) {
        this.share = share;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public StoreBean getStore() {
        return store;
    }

    public void setStore(StoreBean store) {
        this.store = store;
    }

    public videoBean getVideo() {
        return video;
    }

    public void setVideo(videoBean video) {
        this.video = video;
    }

    public List<ArchivesBean> getArchives() {
        return archives;
    }

    public void setArchives(List<ArchivesBean> archives) {
        this.archives = archives;
    }

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public List<ImgListBean> getImgList() {
        return imgList;
    }

    public void setImgList(List<ImgListBean> imgList) {
        this.imgList = imgList;
    }

    public static class CarBean {
        /**
         * price : 3.50万
         * title : 东方车666
         * vr_url :
         */

        private String price;
        private String title;
        private String vr_url;
        private String purchasePrice;

        public String getPurchasePrice() {
            return purchasePrice;
        }

        public void setPurchasePrice(String purchasePrice) {
            this.purchasePrice = purchasePrice;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getVr_url() {
            return vr_url;
        }

        public void setVr_url(String vr_url) {
            this.vr_url = vr_url;
        }
    }

    public static class videoBean{
        private String title;
        private String playUrl;
        private String coverForFeed;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPlayUrl() {
            return playUrl;
        }

        public void setPlayUrl(String playUrl) {
            this.playUrl = playUrl;
        }

        public String getCoverForFeed() {
            return coverForFeed;
        }

        public void setCoverForFeed(String coverForFeed) {
            this.coverForFeed = coverForFeed;
        }
    }

    public static class ShareBean {
        /**
         * shareDes : 认证车行认证车行认证车行认证车行
         * shareImg : https://www.haoche666.com/Uploads/attachment/20171205/65a2332c27ea0f9362d7f9b103308789.png
         * shareTitle : 东方车666
         * shareUrl :
         */

        private String shareDes;
        private String shareImg;
        private String shareTitle;
        private String shareUrl;

        public String getShareDes() {
            return shareDes;
        }

        public void setShareDes(String shareDes) {
            this.shareDes = shareDes;
        }

        public String getShareImg() {
            return shareImg;
        }

        public void setShareImg(String shareImg) {
            this.shareImg = shareImg;
        }

        public String getShareTitle() {
            return shareTitle;
        }

        public void setShareTitle(String shareTitle) {
            this.shareTitle = shareTitle;
        }

        public String getShareUrl() {
            return shareUrl;
        }

        public void setShareUrl(String shareUrl) {
            this.shareUrl = shareUrl;
        }
    }

    public static class StoreBean {
        /**
         * id : 1
         * intro : 认证车行认证车行认证车行认证车行
         * logo : https://www.haoche666.com/Uploads/attachment/20171205/65a2332c27ea0f9362d7f9b103308789.png
         * name : 认证车行
         * tel : 10086
         */

        private int id;
        private String intro;
        private String logo;
        private String name;
        private String tel;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }
    }

    public static class ArchivesBean {
        /**
         * name : 2005年01月上牌
         * value : 12年11月
         */

        private String name;
        private String value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static class BannerBean {
        /**
         * img : https://www.haoche666.com/Uploads/attachment/20171205/c4060bd44de90944fa8c5eca2f95f69a.jpg
         */

        private String img;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }

    public static class ImgListBean {
        /**
         * h : 307
         * img : https://www.haoche666.com/Uploads/attachment/20171205/65a2332c27ea0f9362d7f9b103308789.png
         * w : 307
         */

        private int h;
        private String img;
        private int w;

        public int getH() {
            return h;
        }

        public void setH(int h) {
            this.h = h;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getW() {
            return w;
        }

        public void setW(int w) {
            this.w = w;
        }
    }
}
