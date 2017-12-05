package hudongchuangxiang.haoche666.seller.viewholder;

import android.support.annotation.LayoutRes;
import android.support.v4.content.ContextCompat;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import hudongchuangxiang.haoche666.seller.R;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class DingDanGLViewHolder extends BaseViewHolder<Integer> {

    private final TextView textType;

    public DingDanGLViewHolder(ViewGroup parent, @LayoutRes int res, int viewType) {
        super(parent, res);
        textType = $(R.id.textType);
        switch (viewType) {
            case 0:
                textType.setTextColor(ContextCompat.getColor(getContext(), R.color.dingDanChaWeiXiu));
                textType.setBackgroundResource(R.drawable.shape_wei_xiu_7dp);
                textType.setText("查维修");
                break;
            case 1:
                textType.setTextColor(ContextCompat.getColor(getContext(), R.color.dingDanChaWeiZhang));
                textType.setBackgroundResource(R.drawable.shape_wei_zhang_7dp);
                textType.setText("查违章");
                break;
            case 2:
                textType.setTextColor(ContextCompat.getColor(getContext(), R.color.dingDanChaChuXian));
                textType.setBackgroundResource(R.drawable.shape_chu_xian_7dp);
                textType.setText("查出险");
                break;
            case 3:
                textType.setTextColor(ContextCompat.getColor(getContext(), R.color.dingDanChaLiShi));
                textType.setBackgroundResource(R.drawable.shape_li_shi_7dp);
                textType.setText("查历史");
                break;
            default:
                textType.setTextColor(ContextCompat.getColor(getContext(), R.color.dingDanChaWeiXiu));
                textType.setBackgroundResource(R.drawable.shape_wei_xiu_7dp);
                textType.setText("查维修");
                break;
        }
    }

    @Override
    public void setData(Integer data) {
        super.setData(data);
    }

}
