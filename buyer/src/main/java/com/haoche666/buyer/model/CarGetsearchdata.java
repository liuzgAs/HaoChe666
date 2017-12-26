package com.haoche666.buyer.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2017/12/26/026.
 *
 * @author ZhangJieBo
 */

public class CarGetsearchdata {

    /**
     * data : {"sort_id":[{"title":"默认排序","value":"0"},{"title":"最新上架","value":"1"},{"title":"价格最低","value":"2"},{"title":"价格最高","value":"3"},{"title":"车龄最短","value":"4"},{"title":"里程最少","value":"5"}],"z_age":[{"title":"不限","value":[0,11]},{"title":"1年内","value":[0,1]},{"title":"2年内","value":[0,2]},{"title":"3年内","value":[0,3]},{"title":"5年内","value":[0,5]},{"title":"8年内","value":[0,8]},{"title":"10年内","value":[0,10]}],"z_price":[{"title":"不限","value":[0,61]},{"title":"0-3万","value":[0,3]},{"title":"3-5万","value":[3,5]},{"title":"5-10万","value":[5,10]},{"title":"10-15万","value":[10,15]},{"title":"15-20万","value":[15,20]},{"title":"20-30万","value":[20,30]},{"title":"30-50万","value":[30,50]},{"title":"50-60万","value":[50,60]}]}
     * info : 获取成功
     * status : 1
     */

    private DataBean data;
    private String info;
    private int status;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class DataBean {
        private List<SortIdBean> sort_id;
        private List<AgePriceBean> z_age;
        private List<AgePriceBean> z_price;

        public List<SortIdBean> getSort_id() {
            return sort_id;
        }

        public void setSort_id(List<SortIdBean> sort_id) {
            this.sort_id = sort_id;
        }

        public List<AgePriceBean> getZ_age() {
            return z_age;
        }

        public void setZ_age(List<AgePriceBean> z_age) {
            this.z_age = z_age;
        }

        public List<AgePriceBean> getZ_price() {
            return z_price;
        }

        public void setZ_price(List<AgePriceBean> z_price) {
            this.z_price = z_price;
        }

        public static class SortIdBean {
            /**
             * title : 默认排序
             * value : 0
             */

            private String title;
            private int value;
            private boolean isSelect;

            public boolean isSelect() {
                return isSelect;
            }

            public void setSelect(boolean select) {
                isSelect = select;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }
        }

    }
    public static class AgePriceBean {
        /**
         * title : 不限
         * value : [0,61]
         */

        private String title;
        private List<Integer> value;
        private boolean isSelect;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<Integer> getValue() {
            return value;
        }

        public void setValue(List<Integer> value) {
            this.value = value;
        }
    }
}
