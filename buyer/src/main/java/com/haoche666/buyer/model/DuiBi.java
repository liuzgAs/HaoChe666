package com.haoche666.buyer.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhangjiebo on 2017/12/26/026.
 *
 * @author ZhangJieBo
 */

public class DuiBi implements Serializable{
    public DuiBi(List<AttentionGetattention.DataBean> allData) {
        this.allData = allData;
    }

    private List<AttentionGetattention.DataBean> allData;

    public List<AttentionGetattention.DataBean> getAllData() {
        return allData;
    }

    public void setAllData(List<AttentionGetattention.DataBean> allData) {
        this.allData = allData;
    }
}
