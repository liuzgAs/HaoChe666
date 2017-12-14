package com.haoche666.buyer.viewholder;

import android.support.annotation.LayoutRes;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.haoche666.buyer.R;
import com.haoche666.buyer.model.StoreDetails;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class CheHangViewHolder extends BaseViewHolder<StoreDetails.DataBean> {

    private final TextView textJiaGe;
    private final ImageView imageImg;
    private final TextView textTitle;
    private final TextView textCard_time;

    public CheHangViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        textJiaGe = $(R.id.textJiaGe);
        imageImg = $(R.id.imageImg);
        textTitle = $(R.id.textTitle);
        textCard_time = $(R.id.textCard_time);
    }

    @Override
    public void setData(StoreDetails.DataBean data) {
        super.setData(data);
        SpannableString span = new SpannableString("¥"+data.getPrice());
        span.setSpan(new RelativeSizeSpan(0.6f), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textJiaGe.setText(span);
        Glide.with(getContext())
                .load(data.getImg())
                .asBitmap()
                .placeholder(R.mipmap.ic_empty)
                .into(imageImg);
        textTitle.setText(data.getTitle());
        textCard_time.setText(data.getCard_time()+"|"+data.getKm());
    }
    
}
