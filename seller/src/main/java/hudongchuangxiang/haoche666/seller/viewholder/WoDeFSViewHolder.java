package hudongchuangxiang.haoche666.seller.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import hudongchuangxiang.haoche666.seller.R;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class WoDeFSViewHolder extends BaseViewHolder<Integer> {

    private final ImageView imageTouXiang;
    private final TextView textUser_name;
    private final TextView textAdd_time;

    public WoDeFSViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        imageTouXiang = $(R.id.imageTouXiang);
        textUser_name = $(R.id.textUser_name);
        textAdd_time = $(R.id.textAdd_time);
    }

    @Override
    public void setData(Integer data) {
        super.setData(data);
        Glide.with(getContext())
                .load(R.mipmap.pic1)
                .bitmapTransform(new RoundedCornersTransformation(getContext(), 10, 0))
                .placeholder(R.mipmap.ic_empty)
                .into(imageTouXiang);
    }

}
