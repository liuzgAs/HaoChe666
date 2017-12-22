package com.haoche666.buyer.model;

/**
 * Created by zhangjiebo on 2017/12/22/022.
 *
 * @author ZhangJieBo
 */

public class Sort {
    private String name;
    private boolean isSelect;

    public Sort(String name, boolean isSelect) {
        this.name = name;
        this.isSelect = isSelect;
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
