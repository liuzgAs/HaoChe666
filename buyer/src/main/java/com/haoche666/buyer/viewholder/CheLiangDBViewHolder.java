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
public class CheLiangDBViewHolder extends BaseViewHolder<AttentionGetattention.DataBean> {

    private final ImageView imageSelect;
    private final ImageView imageImg;
    private final TextView textTitle;
    private final TextView textDes;

    public CheLiangDBViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        imageSelect = $(R.id.imageSelect);
        imageImg = $(R.id.imageImg);
        textTitle = $(R.id.textTitle);
        textDes = $(R.id.textDes);
    }

    @Override
    public void setData(AttentionGetattention.DataBean data) {
        super.setData(data);
        if (data.isSelect()){
            imageSelect.setImageResource(R.mipmap.xuanzhong);
        }else {
            imageSelect.setImageResource(R.mipmap.weixuanzhong);
        }
        textTitle.setText(data.getTitle());
        textDes.setText(data.getDes());
        Glide.with(getContext())
                .load(data.getImg())
                .asBitmap()
                .placeholder(R.mipmap.ic_empty)
                .into(imageImg);
    }
    
}
