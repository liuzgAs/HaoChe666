package com.haoche666.buyer.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.haoche666.buyer.R;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class CheLiangXQViewHolder extends BaseViewHolder<Integer> {

    private final ImageView image;

    public CheLiangXQViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        image = $(R.id.image);
    }

    @Override
    public void setData(Integer data) {
        super.setData(data);
        image.setImageResource(data);
    }
    
}
