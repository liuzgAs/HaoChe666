package com.haoche666.buyer.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.haoche666.buyer.R;
import com.haoche666.buyer.model.CarDetails;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class CheLiangXQViewHolder extends BaseViewHolder<CarDetails.ImgListBean> {

    private final ImageView image;

    public CheLiangXQViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        image = $(R.id.image);
    }

    @Override
    public void setData(CarDetails.ImgListBean data) {
        super.setData(data);
        Glide.with(getContext())
                .load(data.getImg())
                .asBitmap()
                .placeholder(R.mipmap.ic_empty)
                .into(image);
    }
    
}
