package com.haoche666.buyer.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haoche666.buyer.R;
import com.haoche666.buyer.model.UserMsg;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class XiaoXiViewHolder extends BaseViewHolder<UserMsg.DataBean> {

    private final TextView textAddTime;
    private final TextView textTitle;
    private final TextView textIntro;

    public XiaoXiViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        textAddTime = $(R.id.textAddTime);
        textTitle = $(R.id.textTitle);
        textIntro = $(R.id.textIntro);
    }

    @Override
    public void setData(UserMsg.DataBean data) {
        super.setData(data);
        textAddTime.setText(data.getCreate_time());
        textTitle.setText(data.getTitle());
        textIntro.setText(data.getDes());
    }
    
}
