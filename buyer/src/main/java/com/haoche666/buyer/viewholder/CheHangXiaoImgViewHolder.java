package com.haoche666.buyer.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.haoche666.buyer.R;
import com.haoche666.buyer.model.Store;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class CheHangXiaoImgViewHolder extends BaseViewHolder<Store.DataBean.CarBean> {

    private final TextView textPrice;
    private final ImageView imageImg;

    public CheHangXiaoImgViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        textPrice = $(R.id.textPrice);
        imageImg = $(R.id.imageImg);
    }

    @Override
    public void setData(Store.DataBean.CarBean data) {
        super.setData(data);
        textPrice.setText("报价："+data.getPrice()+"万");
        Glide.with(getContext())
                .load(data.getImg())
                .asBitmap()
                .placeholder(R.mipmap.ic_empty)
                .into(imageImg);
    }
    
}
