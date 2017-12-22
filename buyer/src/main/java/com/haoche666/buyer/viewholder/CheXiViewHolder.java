package com.haoche666.buyer.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.haoche666.buyer.R;
import com.haoche666.buyer.model.CarCarstyle;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class CheXiViewHolder extends BaseViewHolder<CarCarstyle.DataBean> {

    private final ImageView imageImg;
    private final TextView textName;

    public CheXiViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        imageImg = $(R.id.imageImg);
        textName = $(R.id.textName);
    }

    @Override
    public void setData(CarCarstyle.DataBean data) {
        super.setData(data);
            Glide.with(getContext())
                    .load(data.getImg())
                    .asBitmap()
                    .placeholder(R.mipmap.ic_empty)
                    .into(imageImg);
        textName.setText(data.getName());
    }
    
}
