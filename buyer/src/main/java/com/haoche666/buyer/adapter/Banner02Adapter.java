package com.haoche666.buyer.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.haoche666.buyer.R;
import com.haoche666.buyer.avtivity.CheHangXXActivity;
import com.haoche666.buyer.avtivity.CheLiangXQActivity;
import com.haoche666.buyer.avtivity.MainActivity;
import com.haoche666.buyer.base.MyDialog;
import com.haoche666.buyer.base.ToLoginActivity;
import com.haoche666.buyer.constant.Constant;
import com.haoche666.buyer.model.Buyer;
import com.haoche666.buyer.model.OkObject;
import com.haoche666.buyer.model.SimpleInfo;
import com.haoche666.buyer.util.ApiClient;

import java.util.HashMap;
import java.util.List;

import huisedebi.zjb.mylibrary.util.GsonUtils;
import huisedebi.zjb.mylibrary.util.LogUtil;

/**
 * Created by zhangjiebo on 2017/10/27 0027.
 *
 * @author ZhangJieBo
 */
public class Banner02Adapter extends PagerAdapter {

    private Context mContext;
    private List<Buyer.StoreBean> imgList;
    private ImageView[] imageViews = new ImageView[3];
    private TextView textGuanZhu;

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
        textGuanZhu = inflate.findViewById(R.id.textGuanZhu);
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
            final List<Buyer.StoreBean.CarBean> carBeanList = imgList.get(position % imgList.size()).getCar();
            for (int i = 0; i < carBeanList.size(); i++) {
                if (!TextUtils.isEmpty(carBeanList.get(i).getImg())) {
                    imageViews[i].setVisibility(View.VISIBLE);
                    Glide.with(mContext)
                            .load(carBeanList.get(i).getImg())
                            .asBitmap()
                            .placeholder(R.mipmap.ic_empty)
                            .into(imageViews[i]);
                    final int finalI = i;
                    imageViews[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent();
                            intent.setClass(mContext, CheLiangXQActivity.class);
                            intent.putExtra(Constant.IntentKey.ID,carBeanList.get(finalI).getId());
                            mContext.startActivity(intent);
                        }
                    });
                }
            }
            if (imgList.get(position % imgList.size()).getIs_attention()==0){
                textGuanZhu.setText("+关注");
                textGuanZhu.setBackgroundResource(R.drawable.shape_basic1dp_5dp);
                textGuanZhu.setTextColor(ContextCompat.getColor(mContext,R.color.textGold));
                textGuanZhu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (((MainActivity)mContext).isLogin){
                            guanZhu(position);
                        }else {
                            ToLoginActivity.toLoginActivity(mContext);
                        }
                    }
                });
            }else {
                textGuanZhu.setText("已关注");
                textGuanZhu.setBackgroundResource(R.drawable.shape_textgray1dp_5dp);
                textGuanZhu.setTextColor(ContextCompat.getColor(mContext,R.color.text_gray));
                textGuanZhu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(mContext, "您已关注该车行", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }

        container.addView(inflate, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        return inflate;
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject(int car_store_id) {
        String url = Constant.HOST + Constant.Url.Attention;
        HashMap<String, String> params = new HashMap<>();
        if (((MainActivity)mContext).isLogin) {
            params.put("uid", ((MainActivity)mContext).userInfo.getUid());
            params.put("tokenTime",((MainActivity)mContext).tokenTime);
        }
        /*1-车辆；2-车行*/
        params.put("type_id","2");
        params.put("car_store_id",car_store_id+"");
        /*1-关注；0-取消关注*/
        params.put("a_status","1");
        return new OkObject(params, url);
    }

    /**
     * des： 关注
     * author： ZhangJieBo
     * date： 2017/12/22/022 15:58
     */
    private void guanZhu(final int position) {
        ((MainActivity)mContext).showLoadingDialog();
        ApiClient.post(mContext, getOkObject(imgList.get(position % imgList.size()).getId()), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                ((MainActivity)mContext).cancelLoadingDialog();
                LogUtil.LogShitou("Banner02Adapter--onSuccess",s+ "");
                try {
                    SimpleInfo simpleInfo = GsonUtils.parseJSON(s, SimpleInfo.class);
                    if (simpleInfo.getStatus()==1){
                        imgList.get(position % imgList.size()).setIs_attention(1);
                        textGuanZhu.setText("已关注");
                        textGuanZhu.setBackgroundResource(R.drawable.shape_textgray1dp_5dp);
                        textGuanZhu.setTextColor(ContextCompat.getColor(mContext,R.color.text_gray));
                    }else if (simpleInfo.getStatus()==3){
                        MyDialog.showReLoginDialog(mContext);
                    }else {
                    }
                    Toast.makeText(mContext, simpleInfo.getInfo(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(mContext,"数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                ((MainActivity)mContext).cancelLoadingDialog();
                Toast.makeText(mContext, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
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
