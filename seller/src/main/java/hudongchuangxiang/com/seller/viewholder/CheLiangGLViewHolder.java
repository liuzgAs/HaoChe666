package hudongchuangxiang.com.seller.viewholder;

import android.support.annotation.LayoutRes;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import hudongchuangxiang.com.seller.R;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class CheLiangGLViewHolder extends BaseViewHolder<Integer> {

    private final TextView textPrice;
    private final ImageView imageBianJi;

    public CheLiangGLViewHolder(ViewGroup parent, @LayoutRes int res,int position) {
        super(parent, res);
        imageBianJi = $(R.id.imageBianJi);
        switch (position) {
            case 2:
                imageBianJi.setVisibility(View.GONE);
                break;
            default:
                imageBianJi.setVisibility(View.VISIBLE);
                break;
        }
        textPrice = $(R.id.textPrice);
    }

    @Override
    public void setData(Integer data) {
        super.setData(data);
        SpannableString span = new SpannableString("¥51.8万");
        span.setSpan(new RelativeSizeSpan(0.6f), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textPrice.setText(span);
    }

}
