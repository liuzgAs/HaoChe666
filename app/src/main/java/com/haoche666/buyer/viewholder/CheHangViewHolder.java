package com.haoche666.buyer.viewholder;

import android.support.annotation.LayoutRes;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haoche666.buyer.R;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class CheHangViewHolder extends BaseViewHolder<Integer> {

    private final TextView textJiaGe;

    public CheHangViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        textJiaGe = $(R.id.textJiaGe);
    }

    @Override
    public void setData(Integer data) {
        super.setData(data);
        SpannableString span = new SpannableString("¥51.8万");
        span.setSpan(new RelativeSizeSpan(0.6f), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textJiaGe.setText(span);
    }
    
}
