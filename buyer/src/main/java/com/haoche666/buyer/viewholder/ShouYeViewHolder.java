package com.haoche666.buyer.viewholder;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haoche666.buyer.R;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 *
 * @author Administrator
 * @date 2017/3/28 0028
 */
public class ShouYeViewHolder extends BaseViewHolder<Integer> {

    private final TextView textShouFu;

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
    }

    @Override
    public void setData(Integer data) {
        super.setData(data);
    }
    
}
