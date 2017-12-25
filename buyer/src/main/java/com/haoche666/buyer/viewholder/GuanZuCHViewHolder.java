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
public class GuanZuCHViewHolder extends BaseViewHolder<AttentionGetattention.DataBean> {

    private final ImageView imageImg;
    private final TextView textTitle;
    private final TextView textDes;
    private final TextView textDes2;
    private final TextView textUpdates;

    public GuanZuCHViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        imageImg = $(R.id.imageImg);
        textTitle = $(R.id.textTitle);
        textDes = $(R.id.textDes);
        textDes2 = $(R.id.textDes2);
        textUpdates = $(R.id.textUpdates);
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
        textDes2.setText(data.getDes2());
        textUpdates.setText("已更新"+data.getUpdates()+"辆");
    }
    
}
