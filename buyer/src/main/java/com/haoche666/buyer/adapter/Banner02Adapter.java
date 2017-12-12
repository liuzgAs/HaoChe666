package com.haoche666.buyer.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.haoche666.buyer.R;
import com.haoche666.buyer.avtivity.CheHangXXActivity;
import com.haoche666.buyer.constant.Constant;
import com.haoche666.buyer.model.Buyer;

import java.util.List;

/**
 * Created by zhangjiebo on 2017/10/27 0027.
 *
 * @author ZhangJieBo
 */
public class Banner02Adapter extends PagerAdapter {

    private Context mContext;
    private List<Buyer.StoreBean> imgList;
    private ImageView[] imageViews = new ImageView[3];

    public Banner02Adapter(Context context, List<Buyer.StoreBean> imgList) {
        this.mContext = context;
        this.imgList = imgList;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE / 2;
    }

    @Override
    public View instantiateItem(ViewGroup container, final int position) {
        View inflate = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_che_hang_tui_jian, null);
        inflate.findViewById(R.id.textJinRuDP).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imgList.size() > 0) {
                    Intent intent = new Intent();
                    intent.putExtra(Constant.IntentKey.ID, imgList.get(position % imgList.size()).getId());
                    intent.setClass(mContext, CheHangXXActivity.class);
                    mContext.startActivity(intent);
                }
            }
        });
        TextView textName = inflate.findViewById(R.id.textName);
        TextView textIntro = inflate.findViewById(R.id.textIntro);
        TextView textDes = inflate.findViewById(R.id.textDes);
        imageViews[0] = inflate.findViewById(R.id.image01);
        imageViews[1] = inflate.findViewById(R.id.image02);
        imageViews[2] = inflate.findViewById(R.id.image03);
        imageViews[0].setVisibility(View.INVISIBLE);
        imageViews[1].setVisibility(View.INVISIBLE);
        imageViews[2].setVisibility(View.INVISIBLE);
        if (imgList.size() > 0) {
            textIntro.setText("简介：" + imgList.get(position % imgList.size()).getIntro());
            textName.setText(imgList.get(position % imgList.size()).getName());
            textDes.setText(imgList.get(position % imgList.size()).getDes());
            List<Buyer.StoreBean.CarBean> carBeanList = imgList.get(position % imgList.size()).getCar();
            for (int i = 0; i < carBeanList.size(); i++) {
                if (!TextUtils.isEmpty(carBeanList.get(i).getImg())) {
                    imageViews[i].setVisibility(View.VISIBLE);
                    Glide.with(mContext)
                            .load(carBeanList.get(i).getImg())
                            .asBitmap()
                            .placeholder(R.mipmap.ic_empty)
                            .into(imageViews[i]);
                    imageViews[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });
                }
            }
        }
        container.addView(inflate, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        return inflate;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
