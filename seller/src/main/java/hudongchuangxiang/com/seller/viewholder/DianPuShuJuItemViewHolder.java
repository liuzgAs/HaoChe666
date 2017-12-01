package hudongchuangxiang.com.seller.viewholder;

import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import hudongchuangxiang.com.seller.R;
import hudongchuangxiang.com.seller.customview.JiaoBiao;
import huisedebi.zjb.mylibrary.util.DpUtils;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class DianPuShuJuItemViewHolder extends BaseViewHolder<Integer> {

    private final JiaoBiao viewJiaoBiao;
    private int type;

    public DianPuShuJuItemViewHolder(ViewGroup parent, @LayoutRes int res,int type) {
        super(parent, res);
        this.type =type;
        viewJiaoBiao = $(R.id.viewJiaoBiao);
        viewJiaoBiao.setVisibility(View.VISIBLE);
        ImageView imageBianJi = $(R.id.imageBianJi);
        imageBianJi.setVisibility(View.GONE);
    }

    @Override
    public void setData(Integer data) {
        super.setData(data);
        if (type==1){
            switch (data) {
                case 1:
                    viewJiaoBiao.setBgColorAndTextAndSize(Color.parseColor("#e96262"),"NO.1", DpUtils.convertDpToPixel(14,getContext()));
                    break;
                case 2:
                    viewJiaoBiao.setBgColorAndTextAndSize(Color.parseColor("#ec9b32"),"NO.2", DpUtils.convertDpToPixel(14,getContext()));
                    break;
                case 3:
                    viewJiaoBiao.setBgColorAndTextAndSize(Color.parseColor("#e2c330"),"NO.3", DpUtils.convertDpToPixel(14,getContext()));
                    break;
                default:
                    break;
            }
        }else {
            switch (data) {
                case 1:
                    viewJiaoBiao.setBgColorAndTextAndSize(Color.parseColor("#7174e2"),"NO.1", DpUtils.convertDpToPixel(14,getContext()));
                    break;
                case 2:
                    viewJiaoBiao.setBgColorAndTextAndSize(Color.parseColor("#68a9dc"),"NO.2", DpUtils.convertDpToPixel(14,getContext()));
                    break;
                case 3:
                    viewJiaoBiao.setBgColorAndTextAndSize(Color.parseColor("#68c5a1"),"NO.3", DpUtils.convertDpToPixel(14,getContext()));
                    break;
                default:
                    break;
            }
        }

    }

}
