package com.haoche666.buyer.model;

import java.util.List;

/**
 * Created by zhangjiebo on 2017/12/22/022.
 *
 * @author ZhangJieBo
 */

public class PriceAge {
    private String name;
    private List<Integer> value;
    private boolean isSelect;

    public PriceAge(String name,List<Integer> value, boolean isSelect) {
        this.name = name;
        this.value = value;
        this.isSelect = isSelect;
    }

    public List<Integer> getValue() {
        return value;
    }

    public void setValue(List<Integer> value) {
        this.value = value;
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
