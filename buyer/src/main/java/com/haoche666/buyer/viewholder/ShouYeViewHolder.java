package com.haoche666.buyer.viewholder;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.haoche666.buyer.R;
import com.haoche666.buyer.model.Buyer;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 *
 * @author Administrator
 * @date 2017/3/28 0028
 */
public class ShouYeViewHolder extends BaseViewHolder<Buyer.DataBean> {

    private final TextView textShouFu;
    private final ImageView imageImg;
    private final TextView textTitle;
    private final TextView textDes;
    private final TextView textPrice;

    public ShouYeViewHolder(ViewGroup parent, @LayoutRes int res, int leiBie) {
        super(parent, res);
        textShouFu = $(R.id.textShouFu);
        switch (leiBie){
            case 0:
                textShouFu.setVisibility(View.GONE);
                break;
            case 1:
                textShouFu.setVisibility(View.VISIBLE);
                break;
            default:
                textShouFu.setVisibility(View.GONE);
                break;
        }
        imageImg = $(R.id.imageImg);
        textTitle = $(R.id.textTitle);
        textDes = $(R.id.textDes);
        textPrice = $(R.id.textPrice);
    }

    @Override
    public void setData(Buyer.DataBean data) {
        super.setData(data);
        Glide.with(getContext())
                .load(data.getImg())
                .asBitmap()
                .placeholder(R.mipmap.ic_empty)
                .into(imageImg);
        textTitle.setText(data.getTitle());
        textDes.setText(data.getDes());
        textPrice.setText(data.getPrice());
        textShouFu.setText(data.getOne_payments());
    }
    
}
