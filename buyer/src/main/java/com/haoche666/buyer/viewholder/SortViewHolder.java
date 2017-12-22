package com.haoche666.buyer.viewholder;

import android.support.annotation.LayoutRes;
import android.support.v4.content.ContextCompat;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haoche666.buyer.R;
import com.haoche666.buyer.model.Sort;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class SortViewHolder extends BaseViewHolder<Sort> {

    private final TextView textName;

    public SortViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        textName = $(R.id.textName);
    }

    @Override
    public void setData(Sort data) {
        super.setData(data);
        textName.setText(data.getName());
        if (data.isSelect()){
            textName.setTextColor(ContextCompat.getColor(getContext(),R.color.basic_color));
        }else {
            textName.setTextColor(ContextCompat.getColor(getContext(),R.color.light_black));
        }
    }
    
}
