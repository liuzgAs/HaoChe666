package com.haoche666.buyer.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.haoche666.buyer.R;
import com.haoche666.buyer.model.CarDetails;

public class CheLiangBannerImgHolderView implements Holder<CarDetails.BannerBean> {
    private ImageView imageView;

    @Override
    public View createView(Context context) {
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, CarDetails.BannerBean data) {
        Glide.with(context)
                .load(data.getImg())
                .placeholder(R.mipmap.ic_empty)
                .into(imageView);
    }
}