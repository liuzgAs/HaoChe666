package com.haoche666.buyer.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haoche666.buyer.R;
import com.haoche666.buyer.model.SellcarGetsellcar;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class WoMaiDCViewHolder extends BaseViewHolder<SellcarGetsellcar.DataBean> {

    private final TextView textName;
    private final TextView textDes;
    private final TextView textSell_city;
    private final TextView textBrand_city;

    public WoMaiDCViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        textName = $(R.id.textName);
        textDes = $(R.id.textDes);
        textSell_city = $(R.id.textSell_city);
        textBrand_city = $(R.id.textBrand_city);
    }

    @Override
    public void setData(SellcarGetsellcar.DataBean data) {
        super.setData(data);
        textName.setText(data.getName());
        textDes.setText(data.getDes());
        textSell_city.setText("卖车城市："+data.getSell_city());
        textBrand_city.setText("上牌城市："+data.getBrand_city());
    }
    
}
