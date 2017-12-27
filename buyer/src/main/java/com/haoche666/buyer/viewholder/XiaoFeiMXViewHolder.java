package com.haoche666.buyer.viewholder;

import android.support.annotation.LayoutRes;
import android.support.v4.content.ContextCompat;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haoche666.buyer.R;
import com.haoche666.buyer.model.CorderGetconsumedetail;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class XiaoFeiMXViewHolder extends BaseViewHolder<CorderGetconsumedetail.DataBean> {

    private final TextView textType;
    private final TextView textDes;
    private final TextView textCreatetime;
    private final TextView textPrice;

    public XiaoFeiMXViewHolder(ViewGroup parent, @LayoutRes int res, int viewType) {
        super(parent, res);
        textType = $(R.id.textType);
        textDes = $(R.id.textDes);
        textCreatetime = $(R.id.textCreatetime);
        textPrice = $(R.id.textPrice);
        switch (viewType) {
            case 1:
                textType.setTextColor(ContextCompat.getColor(getContext(), R.color.dingDanChaWeiXiu));
                textType.setBackgroundResource(R.drawable.shape_wei_xiu_7dp);
                textType.setText("查维修");
                break;
            case 3:
                textType.setTextColor(ContextCompat.getColor(getContext(), R.color.dingDanChaWeiZhang));
                textType.setBackgroundResource(R.drawable.shape_wei_zhang_7dp);
                textType.setText("查违章");
                break;
            case 2:
                textType.setTextColor(ContextCompat.getColor(getContext(), R.color.dingDanChaChuXian));
                textType.setBackgroundResource(R.drawable.shape_chu_xian_7dp);
                textType.setText("查出险");
                break;
            default:
                textType.setTextColor(ContextCompat.getColor(getContext(), R.color.dingDanChaWeiXiu));
                textType.setBackgroundResource(R.drawable.shape_wei_xiu_7dp);
                textType.setText("查维修");
                break;
        }
    }

    @Override
    public void setData(CorderGetconsumedetail.DataBean data) {
        super.setData(data);
        textDes.setText(data.getProduct_title());
        textCreatetime.setText(data.getCreate_time());
        textPrice.setText("¥"+data.getPrice());
    }

}
