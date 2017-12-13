package com.haoche666.buyer.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2017/12/13 0013.
 *
 * @author ZhangJieBo
 */

public class CarHistory {
    /**
     * data : [{"des":"66.00万公里 | 2005年","id":185,"img":"https://www.haoche666.com/Uploads/attachment/20171205/65a2332c27ea0f9362d7f9b103308789.png","title":"东方车666"}]
     * info : 操作成功！
     * page : {"dataTotal":1,"page":1,"pageSize":10,"pageTotal":1}
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
         * dataTotal : 1
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

    public static class DataBean {
        /**
         * des : 66.00万公里 | 2005年
         * id : 185
         * img : https://www.haoche666.com/Uploads/attachment/20171205/65a2332c27ea0f9362d7f9b103308789.png
         * title : 东方车666
         */

        private String des;
        private int id;
        private String img;
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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
