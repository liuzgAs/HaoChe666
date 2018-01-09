package com.haoche666.buyer.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.haoche666.buyer.R;
import com.haoche666.buyer.model.BuyerGetvideolistu;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class ShiPinLBViewHolder extends BaseViewHolder<BuyerGetvideolistu.DataBean> {

    private final ImageView imageImg;
    private final TextView textTitle;
    private final TextView textDes;
    private final TextView textDate;

    public ShiPinLBViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        imageImg = $(R.id.imageImg);
        textTitle = $(R.id.textTitle);
        textDes = $(R.id.textDes);
        textDate = $(R.id.textDate);
    }

    @Override
    public void setData(BuyerGetvideolistu.DataBean data) {
        super.setData(data);
        Glide.with(getContext())
                .load(data.getImg())
                .asBitmap()
                .placeholder(R.mipmap.empty_wo_de_dd)
                .into(imageImg);
        textTitle.setText(data.getTitle());
        textDes.setText(data.getDes());
        textDate.setText(data.getCreate_time());
    }
    
}
