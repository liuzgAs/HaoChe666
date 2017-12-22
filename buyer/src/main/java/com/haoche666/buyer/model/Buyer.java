package com.haoche666.buyer.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2017/12/9 0009.
 *
 * @author ZhangJieBo
 */

public class Buyer {
    /**
     * banner : [{"code":"","img":"https://www.haoche666.com/Uploads/attachment/20171205/c4060bd44de90944fa8c5eca2f95f69a.jpg","item_id":0},{"code":"","img":"https://www.haoche666.com/Uploads/attachment/20171205/c4060bd44de90944fa8c5eca2f95f69a.jpg","item_id":0}]
     * data : [{"des":"福州 | 10.00万公里 | 2015年","id":189,"img":"https://www.haoche666.com","price":"8.00万","title":"现代现代"},{"des":"福州 | 10.00万公里 | 2015年","id":188,"img":"https://www.haoche666.com","price":"8.00万","title":"现代现代"},{"des":"福州 | 10.00万公里 | 2015年","id":187,"img":"https://www.haoche666.com","price":"8.00万","title":"现代现代"},{"des":"厦门 | 10.00万公里 | 1970年","id":186,"img":"https://www.haoche666.com","price":"8.00万","title":"大众朗逸2015款"},{"des":"厦门 | 66.00万公里 | 2005年","id":185,"img":"https://www.haoche666.com/Uploads/attachment/20171205/65a2332c27ea0f9362d7f9b103308789.png","price":"3.50万","title":"东方车666"}]
     * info : 操作成功！
     * news : []
     * page : {"dataTotal":5,"page":1,"pageSize":10,"pageTotal":1}
     * status : 1
     * store : [{"car":[{"id":185,"img":"https://www.haoche666.com/Uploads/attachment/20171205/65a2332c27ea0f9362d7f9b103308789.png"}],"des":"51辆在售，58辆已售","id":1,"intro":"认证车行认证车行认证车行认证车行","name":"认证车行"},{"car":[],"des":"51辆在售，58辆已售","id":2,"intro":"","name":"认证车行"},{"car":[{"id":186,"img":"https://www.haoche666.com/Uploads/attachment/20171209/411b1428fc591ee3b28a9621d40b68d1.jpg"},{"id":187,"img":"https://www.haoche666.com"},{"id":188,"img":"https://www.haoche666.com"}],"des":"51辆在售，58辆已售","id":4,"intro":"东赞名车东赞名车","name":"东赞名车"},{"car":[],"des":"51辆在售，58辆已售","id":5,"intro":"","name":"认证车行"}]
     * video : [{"id":189,"img":"https://www.haoche666.com/Uploads/attachment/20171209/411b1428fc591ee3b28a9621d40b68d1.jpg","title":"现代现代","video":{"coverForFeed":"https://www.haoche666.comhttps://www.haoche666.com/Uploads/attachment/20171209/411b1428fc591ee3b28a9621d40b68d1.jpg","playUrl":"https://www.haoche666.com/Uploads/attachment/20171209/411b1428fc591ee3b28a9621d40b68d1.jpg","title":"现代现代"}}]
     */

    private String info;
    private PageBean page;
    private int status;
    private List<BannerBean> banner;
    private List<DataBean> data;
    private List<NewsBean> news;
    private List<StoreBean> store;
    private List<VideoBeanX> video;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public List<NewsBean> getNews() {
        return news;
    }

    public void setNews(List<NewsBean> news) {
        this.news = news;
    }

    public List<StoreBean> getStore() {
        return store;
    }

    public void setStore(List<StoreBean> store) {
        this.store = store;
    }

    public List<VideoBeanX> getVideo() {
        return video;
    }

    public void setVideo(List<VideoBeanX> video) {
        this.video = video;
    }

    public static class PageBean {
        /**
         * dataTotal : 5
         * page : 1
         * pageSize : 10
         * pageTotal : 1
         */

        private int dataTotal;
        private int page;
        private int pageSize;
        private int pageTotal;

        public int getDataTotal() {
            return dataTotal;
        }

        public void setDataTotal(int dataTotal) {
            this.dataTotal = dataTotal;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPageTotal() {
            return pageTotal;
        }

        public void setPageTotal(int pageTotal) {
            this.pageTotal = pageTotal;
        }
    }

    public static class BannerBean {
        /**
         * code :
         * img : https://www.haoche666.com/Uploads/attachment/20171205/c4060bd44de90944fa8c5eca2f95f69a.jpg
         * item_id : 0
         */

        private String code;
        private String img;
        private int item_id;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getItem_id() {
            return item_id;
        }

        public void setItem_id(int item_id) {
            this.item_id = item_id;
        }
    }

    public static class DataBean {
        /**
         * des : 福州 | 10.00万公里 | 2015年
         * id : 189
         * img : https://www.haoche666.com
         * price : 8.00万
         * title : 现代现代
         */

        private String des;
        private int id;
        private String img;
        private String price;
        private String title;

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
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
    }

    public static class StoreBean {
        /**
         * car : [{"id":185,"img":"https://www.haoche666.com/Uploads/attachment/20171205/65a2332c27ea0f9362d7f9b103308789.png"}]
         * des : 51辆在售，58辆已售
         * id : 1
         * intro : 认证车行认证车行认证车行认证车行
         * name : 认证车行
         */

        private String des;
        private int id;
        private String intro;
        private String name;
        private int is_attention;
        private List<CarBean> car;

        public int getIs_attention() {
            return is_attention;
        }

        public void setIs_attention(int is_attention) {
            this.is_attention = is_attention;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<CarBean> getCar() {
            return car;
        }

        public void setCar(List<CarBean> car) {
            this.car = car;
        }

        public static class CarBean {
            /**
             * id : 185
             * img : https://www.haoche666.com/Uploads/attachment/20171205/65a2332c27ea0f9362d7f9b103308789.png
             */

            private int id;
            private String img;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }
        }
    }

    public static class VideoBeanX {
        /**
         * id : 189
         * img : https://www.haoche666.com/Uploads/attachment/20171209/411b1428fc591ee3b28a9621d40b68d1.jpg
         * title : 现代现代
         * video : {"coverForFeed":"https://www.haoche666.comhttps://www.haoche666.com/Uploads/attachment/20171209/411b1428fc591ee3b28a9621d40b68d1.jpg","playUrl":"https://www.haoche666.com/Uploads/attachment/20171209/411b1428fc591ee3b28a9621d40b68d1.jpg","title":"现代现代"}
         */

        private int id;
        private String img;
        private String title;
        private VideoBean video;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public VideoBean getVideo() {
            return video;
        }

        public void setVideo(VideoBean video) {
            this.video = video;
        }

        public static class VideoBean {
            /**
             * coverForFeed : https://www.haoche666.comhttps://www.haoche666.com/Uploads/attachment/20171209/411b1428fc591ee3b28a9621d40b68d1.jpg
             * playUrl : https://www.haoche666.com/Uploads/attachment/20171209/411b1428fc591ee3b28a9621d40b68d1.jpg
             * title : 现代现代
             */

            private String coverForFeed;
            private String playUrl;
            private String title;

            public String getCoverForFeed() {
                return coverForFeed;
            }

            public void setCoverForFeed(String coverForFeed) {
                this.coverForFeed = coverForFeed;
            }

            public String getPlayUrl() {
                return playUrl;
            }

            public void setPlayUrl(String playUrl) {
                this.playUrl = playUrl;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
    public static class NewsBean{
        private int id;
        private String img;
        private String title;
        private String view;
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getView() {
            return view;
        }

        public void setView(String view) {
            this.view = view;
        }
    }
}
