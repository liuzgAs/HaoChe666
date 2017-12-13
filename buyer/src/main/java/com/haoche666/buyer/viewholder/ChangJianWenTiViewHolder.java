package com.haoche666.buyer.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haoche666.buyer.R;
import com.haoche666.buyer.model.Faq;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class ChangJianWenTiViewHolder extends BaseViewHolder<Faq.DataBean> {

    private final TextView textTitle;
    private final TextView textIntro;

    public ChangJianWenTiViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        textTitle = $(R.id.textTitle);
        textIntro = $(R.id.textIntro);
    }

    @Override
    public void setData(Faq.DataBean data) {
        super.setData(data);
        textTitle.setText(data.getTitle());
        textIntro.setText(data.getIntro());
    }
    
}
