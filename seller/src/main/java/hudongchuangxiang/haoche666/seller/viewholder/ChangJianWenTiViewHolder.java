package hudongchuangxiang.haoche666.seller.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import hudongchuangxiang.haoche666.seller.R;
import hudongchuangxiang.haoche666.seller.model.Faq;


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
