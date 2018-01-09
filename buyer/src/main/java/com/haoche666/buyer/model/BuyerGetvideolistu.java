package com.haoche666.buyer.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/1/9/009.
 *
 * @author ZhangJieBo
 */

public class BuyerGetvideolistu {

    /**
     * status : 1
     * info : 操作成功！
     * page : {"page":1,"pageTotal":1,"pageSize":10,"dataTotal":4}
     * data : [{"id":1,"img":"https://www.haoche666.com/Uploads/attachment/20171205/c4060bd44de90944fa8c5eca2f95f69a.jpg","playUrl":"https://www.haoche666.com/Uploads/attachment/20180108/e4a0bad4dead166f6e684841b927721e.mp4","title":"2017款 宝马 宝马7系 三厢 2.0T 手自一体 领先型 (BMW 730Li)","create_time":"1970.01.01","des":"播放了226次","share_url":"https://www.haoche666.com/api/Buyer/viewInfo/id/1"},{"id":2,"img":"https://www.haoche666.com/Uploads/attachment/20180109/b36b30f8be0c6a618676ae59777501be.jpeg","playUrl":"http://1254375935.vod2.myqcloud.com/2d09f8f3vodtransgzp1254375935/fc31f5ef4564972818911757204/v.f30.mp4","title":"野人谈车 03 一台用来买菜的平明超跑 超大空间超省油","create_time":"2018.01.08","des":"播放了228次","share_url":"https://www.haoche666.com/api/Buyer/viewInfo/id/2"},{"id":3,"img":"https://www.haoche666.com/Uploads/attachment/20180109/ea39f1d1163c2c200f6c7bf68dc7dd18.jpg","playUrl":"https://www.haoche666.com/Uploads/attachment/20180109/bcd1068097337695753730c99d308a3a.mp4","title":"2013越野车","create_time":"2018.01.09","des":"播放了12次","share_url":"https://www.haoche666.com/api/Buyer/viewInfo/id/3"},{"id":4,"img":"https://www.haoche666.com/Uploads/attachment/20171215/374184e7694b1dbbce76a774b81bb8f6.jpg","playUrl":"https://www.haoche666.com/Uploads/attachment/20180109/478cfd7447f2e4a24aaced0569d039b5.mp4","title":"野人谈车 03 一台用来买菜的平明超跑 超大空间超省油","create_time":"2018.01.09","des":"播放了201次","share_url":"https://www.haoche666.com/api/Buyer/viewInfo/id/4"}]
     */

    private int status;
    private String info;
    private PageBean page;
    private List<DataBean> data;

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

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class PageBean {
        /**
         * page : 1
         * pageTotal : 1
         * pageSize : 10
         * dataTotal : 4
         */

        private int page;
        private int pageTotal;
        private int pageSize;
        private int dataTotal;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getPageTotal() {
            return pageTotal;
        }

        public void setPageTotal(int pageTotal) {
            this.pageTotal = pageTotal;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getDataTotal() {
            return dataTotal;
        }

        public void setDataTotal(int dataTotal) {
            this.dataTotal = dataTotal;
        }
    }

    public static class DataBean {
        /**
         * id : 1
         * img : https://www.haoche666.com/Uploads/attachment/20171205/c4060bd44de90944fa8c5eca2f95f69a.jpg
         * playUrl : https://www.haoche666.com/Uploads/attachment/20180108/e4a0bad4dead166f6e684841b927721e.mp4
         * title : 2017款 宝马 宝马7系 三厢 2.0T 手自一体 领先型 (BMW 730Li)
         * create_time : 1970.01.01
         * des : 播放了226次
         * share_url : https://www.haoche666.com/api/Buyer/viewInfo/id/1
         */

        private int id;
        private String img;
        private String playUrl;
        private String title;
        private String create_time;
        private String des;
        private String share_url;

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

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public String getShare_url() {
            return share_url;
        }

        public void setShare_url(String share_url) {
            this.share_url = share_url;
        }
    }
}
