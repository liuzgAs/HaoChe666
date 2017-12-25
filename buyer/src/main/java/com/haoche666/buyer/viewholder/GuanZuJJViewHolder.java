package com.haoche666.buyer.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.haoche666.buyer.R;
import com.haoche666.buyer.model.AttentionGetattention;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class GuanZuJJViewHolder extends BaseViewHolder<AttentionGetattention.DataBean> {

    private final ImageView imageImg;
    private final ImageView imageIs_sale;
    private final TextView textTitle;
    private final TextView textDes;

    public GuanZuJJViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        imageImg = $(R.id.imageImg);
        imageIs_sale = $(R.id.imageIs_sale);
        textTitle = $(R.id.textTitle);
        textDes = $(R.id.textDes);
    }

    @Override
    public void setData(AttentionGetattention.DataBean data) {
        super.setData(data);
        Glide.with(getContext())
                .load(data.getImg())
                .asBitmap()
                .placeholder(R.mipmap.ic_empty)
                .into(imageImg);
        textTitle.setText(data.getTitle());
        textDes.setText(data.getDes());
        if (data.getIs_sale()==0){
            imageIs_sale.setImageResource(R.mipmap.daishou);
        }else {
            imageIs_sale.setImageResource(R.mipmap.yishou);
        }
    }
    
}
