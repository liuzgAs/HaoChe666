package com.haoche666.buyer.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haoche666.buyer.R;
import com.haoche666.buyer.model.CorderGetconsumedetail;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class XiaoFeiMXViewHolder extends BaseViewHolder<CorderGetconsumedetail.DataBean> {

    private final TextView textDes;
    private final TextView textCreatetime;
    private final TextView textPrice;

    public XiaoFeiMXViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        textDes = $(R.id.textDes);
        textCreatetime = $(R.id.textCreatetime);
        textPrice = $(R.id.textPrice);
    }

    @Override
    public void setData(CorderGetconsumedetail.DataBean data) {
        super.setData(data);
        textDes.setText(data.getProduct_title());
        textCreatetime.setText(data.getCreate_time());
        textPrice.setText("¥"+data.getPrice());
    }

}
