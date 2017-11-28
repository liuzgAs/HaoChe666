package com.haoche666.buyer.viewholder;

import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.haoche666.buyer.R;
import com.haoche666.buyer.model.CheXi;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class CheXiViewHolder extends BaseViewHolder<CheXi.SeriesBean> {

    private final ImageView imageImg;
    private final TextView textName;
    private final TextView textTitle;

    public CheXiViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        imageImg = $(R.id.imageImg);
        textName = $(R.id.textName);
        textTitle = $(R.id.textTitle);
    }

    @Override
    public void setData(CheXi.SeriesBean data) {
        super.setData(data);
        if (TextUtils.equals("http://file.chexun.com/images_index/album_h_12080.gif",data.getSeriesFirstImage())){
            Glide.with(getContext())
                    .load(R.mipmap.logo)
                    .asBitmap()
                    .placeholder(R.mipmap.ic_empty)
                    .into(imageImg);
        }else {
            Glide.with(getContext())
                    .load(data.getSeriesFirstImage())
                    .asBitmap()
                    .placeholder(R.mipmap.ic_empty)
                    .into(imageImg);
        }
        textName.setText(data.getSeriesName());
        if (TextUtils.isEmpty(data.getCompanyName())){
            textTitle.setVisibility(View.GONE);
        }else {
            textTitle.setVisibility(View.VISIBLE);
            textTitle.setText(data.getCompanyName());
        }
    }
    
}
