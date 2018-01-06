package com.haoche666.buyer.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2018/1/5/005.
 *
 * @author ZhangJieBo
 */

public class UserMsg {
    /**
     * data : [{"create_time":1513664777,"des":"好车平台开门红","id":1,"title":"好车666"}]
     * info : 操作成功！
     * msgType : [{"act":1,"id":1,"name":"全部消息"},{"act":0,"id":2,"name":"平台公告"},{"act":0,"id":3,"name":"我的消息"}]
     * page : {"dataTotal":1,"page":"1","pageSize":10,"pageTotal":1}
     * status : 1
     */

    private String info;
    private PageBean page;
    private int status;
    private List<DataBean> data;
    private List<MsgTypeBean> msgType;

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

    public List<MsgTypeBean> getMsgType() {
        return msgType;
    }

    public void setMsgType(List<MsgTypeBean> msgType) {
        this.msgType = msgType;
    }

    public static class PageBean {
        /**
         * dataTotal : 1
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
         * create_time : 1513664777
         * des : 好车平台开门红
         * id : 1
         * title : 好车666
         */

        private String create_time;
        private String des;
        private int id;
        private String title;

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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class MsgTypeBean {
        /**
         * act : 1
         * id : 1
         * name : 全部消息
         */

        private int act;
        private int id;
        private String name;

        public int getAct() {
            return act;
        }

        public void setAct(int act) {
            this.act = act;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
