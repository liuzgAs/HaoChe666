package com.haoche666.buyer.viewholder;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.haoche666.buyer.R;
import com.haoche666.buyer.model.PinPaiBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class PinPaiXCViewHolder extends BaseViewHolder<PinPaiBean> {

    private final TextView textXuanZhong;
    private final TextView textName;
    private final ImageView imageLogo;

    public PinPaiXCViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        textXuanZhong = $(R.id.textXuanZhong);
        textName = $(R.id.textName);
        imageLogo = $(R.id.imageLogo);
    }

    @Override
    public void setData(PinPaiBean data) {
        super.setData(data);
        textXuanZhong.setVisibility(View.GONE);
        String logoPath = data.getLogoPath();
        String replace = logoPath.replace("50_50", "100_100");
        textName.setText(data.getBrandName());
        Glide.with(getContext())
                .load(replace)
                .asBitmap()
                .placeholder(R.mipmap.ic_empty)
                .into(imageLogo);
    }
    
}
