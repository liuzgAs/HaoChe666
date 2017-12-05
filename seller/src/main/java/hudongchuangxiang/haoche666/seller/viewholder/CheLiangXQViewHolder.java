package hudongchuangxiang.haoche666.seller.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import hudongchuangxiang.haoche666.seller.R;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class CheLiangXQViewHolder extends BaseViewHolder<Integer> {

    private final ImageView image;

    public CheLiangXQViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        image = $(R.id.image);
    }

    @Override
    public void setData(Integer data) {
        super.setData(data);
        image.setImageResource(data);
    }
    
}
