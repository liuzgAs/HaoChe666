package hudongchuangxiang.haoche666.seller.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2017/12/14 0014.
 *
 * @author ZhangJieBo
 */

public class IndexIndex {
    /**
     * info : 操作成功！
     * num : [{"name":"在售车辆","value":86},{"name":"已售车辆","value":86},{"name":"今日发布","value":86},{"name":"今日浏览","value":86}]
     * status : 1
     * tab : [{"des":"累计在售3辆","title":"今日新增"},{"des":"第一时间更新全国车源","title":"订阅车源"}]
     */

    private String info;
    private int status;
    private List<NumBean> num;
    private List<TabBean> tab;

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

    public List<NumBean> getNum() {
        return num;
    }

    public void setNum(List<NumBean> num) {
        this.num = num;
    }

    public List<TabBean> getTab() {
        return tab;
    }

    public void setTab(List<TabBean> tab) {
        this.tab = tab;
    }

    public static class NumBean {
        /**
         * name : 在售车辆
         * value : 86
         */

        private String name;
        private int value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    public static class TabBean {
        /**
         * des : 累计在售3辆
         * title : 今日新增
         */

        private String des;
        private String title;

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
