package com.haoche666.buyer.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.haoche666.buyer.R;
import com.haoche666.buyer.activity.WebActivity;
import com.haoche666.buyer.constant.Constant;
import com.haoche666.buyer.model.Buyer;
import com.haoche666.buyer.model.ShareBean;

import java.util.List;

/**
 * Created by zhangjiebo on 2017/10/27 0027.
 *
 * @author ZhangJieBo
 */
public class BannerAdapter extends PagerAdapter{

    private Context mContext;
    private  List<Buyer.VideoBeanX> imgList;

    public BannerAdapter(Context context, List<Buyer.VideoBeanX> imgList) {
        this.mContext = context;
        this.imgList=imgList;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE/2;
    }

    @Override
    public View instantiateItem(ViewGroup container, final int position) {
        View inflate = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_banner, null);
        inflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(mContext, WebActivity.class);
                intent.putExtra(Constant.IntentKey.TITLE,  mContext.getString(R.string.shipinxaingqing));
                Buyer.VideoBeanX videoBeanX = imgList.get(position % imgList.size());
                intent.putExtra(Constant.IntentKey.URL, videoBeanX.getShare_url());
                intent.putExtra(Constant.IntentKey.BEAN,new ShareBean(videoBeanX.getImg(),videoBeanX.getTitle(),videoBeanX.getDes(),videoBeanX.getShare_url()));
                mContext.startActivity(intent);
            }
        });
        ImageView imageImg = inflate.findViewById(R.id.imageImg);
        if (imgList!=null){
            if (imgList.size()>0){
                Glide.with(mContext)
                        .load(imgList.get(position%imgList.size()).getImg())
                        .asBitmap()
                        .placeholder(R.mipmap.ic_empty)
                        .into(imageImg);
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
