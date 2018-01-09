package com.haoche666.buyer.viewholder;

import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haoche666.buyer.R;
import com.haoche666.buyer.model.AttentionGetcontrastinfo;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class DuiBiInfoViewHolder extends BaseViewHolder<AttentionGetcontrastinfo.DataBean> {
    TextView[] text00 = new TextView[6];
    TextView[] text01 = new TextView[29];
    int[] text00ID = new int[]{
            R.id.text0000,
            R.id.text0001,
            R.id.text0002,
            R.id.text0003,
            R.id.text0004,
            R.id.text0005,
    };
    private int[] text01ID = new int[]{
            R.id.text00100,
            R.id.text00101,
            R.id.text00102,
            R.id.text00103,
            R.id.text00104,
            R.id.text00105,
            R.id.text00106,
            R.id.text00107,
            R.id.text00108,
            R.id.text00109,
            R.id.text00110,
            R.id.text00111,
            R.id.text00112,
            R.id.text00113,
            R.id.text00114,
            R.id.text00115,
            R.id.text00116,
            R.id.text00117,
            R.id.text00118,
            R.id.text00119,
            R.id.text00120,
            R.id.text00121,
            R.id.text00122,
            R.id.text00123,
            R.id.text00124,
            R.id.text00125,
            R.id.text00126,
            R.id.text00127,
            R.id.text00128,
    };

    public DuiBiInfoViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        for (int i = 0; i < text00ID.length; i++) {
            text00[i]=$(text00ID[i]);
        }
        for (int i = 0; i < text01ID.length; i++) {
            text01[i]=$(text01ID[i]);
        }
    }

    @Override
    public void setData(AttentionGetcontrastinfo.DataBean data) {
        super.setData(data);
        List<String> list00 = data.getParams_v().get(0);
        if (list00!=null){
            for (int i = 0; i < text00.length; i++) {
                text00[i].setText(list00.get(i)+"");
                if (data.getParams_vBoolean().get(0).get(i)){
                    text00[i].setVisibility(View.VISIBLE);
                }else {
                    text00[i].setVisibility(View.GONE);
                }
            }
        }else {
            for (int i = 0; i < list00.size(); i++) {
                text00[i].setVisibility(View.GONE);
            }
        }
        if (data.getParams_v().size()>1){
            List<String> list01 = data.getParams_v().get(1);
            if (list01!=null){
                for (int i = 0; i < text01.length; i++) {
                    if (!TextUtils.isEmpty(list01.get(i))){
                        text01[i].setText(list01.get(i)+"");
                    }
                    if (data.getParams_vBoolean().get(1).get(i)){
                        text01[i].setVisibility(View.VISIBLE);
                    }else {
                        text01[i].setVisibility(View.GONE);
                    }
                }
            }else {
                for (int i = 0; i < list01.size(); i++) {
                    text01[i].setVisibility(View.GONE);
                }
            }
        }
    }

}
