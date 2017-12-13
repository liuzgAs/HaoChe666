package com.haoche666.buyer.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2017/12/12 0012.
 *
 * @author ZhangJieBo
 */

public class Article {
    /**
     * data : [{"img":"https://www.haoche666.com/Uploads/attachment/20171205/c4060bd44de90944fa8c5eca2f95f69a.jpg","title":"文章列表2","url":"https://www.haoche666.com/News/view/id/27.html"},{"img":"https://www.haoche666.com/Uploads/attachment/20171205/c4060bd44de90944fa8c5eca2f95f69a.jpg","title":"文章列表 1","url":"https://www.haoche666.com/News/view/id/26.html"}]
     * info : 返回成功！
     * page : {"dataTotal":2,"page":"1","pageSize":10,"pageTotal":1}
     * status : 1
     */

    private String info;
    private PageBean page;
    private int status;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class PageBean {
        /**
         * dataTotal : 2
         * page : 1
         * pageSize : 10
         * pageTotal : 1
         */

        private int dataTotal;
        private String page;
        private int pageSize;
        private int pageTotal;

        public int getDataTotal() {
            return dataTotal;
        }

        public void setDataTotal(int dataTotal) {
            this.dataTotal = dataTotal;
        }

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
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

    public static class DataBean {
        /**
         * img : https://www.haoche666.com/Uploads/attachment/20171205/c4060bd44de90944fa8c5eca2f95f69a.jpg
         * title : 文章列表2
         * url : https://www.haoche666.com/News/view/id/27.html
         */

        private String img;
        private String title;
        private String url;
        private String des;

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
