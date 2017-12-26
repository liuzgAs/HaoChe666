package com.haoche666.buyer.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haoche666.buyer.R;
import com.haoche666.buyer.model.ProductQueryhistory;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class ChaXunLSViewHolder extends BaseViewHolder<ProductQueryhistory.DataBean> {

    private final TextView textDes;
    private final TextView textCreate_time;
    private final TextView textPrice;

    public ChaXunLSViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        textDes = $(R.id.textDes);
        textCreate_time = $(R.id.textCreate_time);
        textPrice = $(R.id.textPrice);
    }

    @Override
    public void setData(ProductQueryhistory.DataBean data) {
        super.setData(data);
        textDes.setText(data.getDes());
        textCreate_time.setText(data.getCreate_time());
        textPrice.setText("¥"+data.getPrice());
    }
    
}
