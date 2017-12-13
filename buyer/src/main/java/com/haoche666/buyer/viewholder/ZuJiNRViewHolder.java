package com.haoche666.buyer.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.haoche666.buyer.R;
import com.haoche666.buyer.model.ArticleHistory;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class ZuJiNRViewHolder extends BaseViewHolder<ArticleHistory.DataBean> {

    private final ImageView imageImg;
    private final TextView textView;
    private final TextView textTitle;

    public ZuJiNRViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        imageImg = $(R.id.imageImg);
        textView = $(R.id.textView);
        textTitle = $(R.id.textTitle);
    }

    @Override
    public void setData(ArticleHistory.DataBean data) {
        super.setData(data);
        Glide.with(getContext())
                .load(data.getImg())
                .asBitmap()
                .placeholder(R.mipmap.ic_empty)
                .into(imageImg);
        textView.setText(data.getView());
        textTitle.setText(data.getTitle());
    }
    
}
