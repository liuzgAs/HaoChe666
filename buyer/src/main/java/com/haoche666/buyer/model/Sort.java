package com.haoche666.buyer.model;

/**
 * Created by zhangjiebo on 2017/12/22/022.
 *
 * @author ZhangJieBo
 */

public class Sort {
    private String name;
    private int sort_id;
    private boolean isSelect;

    public Sort(int sort_id,String name, boolean isSelect) {
        this.sort_id = sort_id;
        this.name = name;
        this.isSelect = isSelect;
    }

    public int getSort_id() {
        return sort_id;
    }

    public void setSort_id(int sort_id) {
        this.sort_id = sort_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
