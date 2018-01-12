package com.haoche666.buyer.viewholder;

import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.haoche666.buyer.R;
import com.haoche666.buyer.base.MyDialog;
import com.haoche666.buyer.constant.Constant;
import com.haoche666.buyer.model.LiaoTian;
import com.haoche666.buyer.model.OkObject;
import com.haoche666.buyer.model.UserBuyerindex;
import com.haoche666.buyer.util.ApiClient;
import com.haoche666.buyer.util.DateTransforam;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import java.util.HashMap;

import huisedebi.zjb.mylibrary.util.GsonUtils;
import huisedebi.zjb.mylibrary.util.LogUtil;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class LiaoTianXiaoXiViewHolder extends BaseViewHolder<LiaoTian> {

    private final TextView textTitle;
    private final ImageView imageKeFu;
    private final TextView textDes;
    private final TextView textTime;
    private final Badge badge;
    private final View viewKeFu;

    public LiaoTianXiaoXiViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        textTitle = $(R.id.textTitle);
        imageKeFu = $(R.id.imageKeFu);
        textDes = $(R.id.textDes);
        textTime = $(R.id.textTime);
        viewKeFu = $(R.id.viewKeFu);
        badge = new QBadgeView(getContext())
                .setBadgeTextColor(Color.WHITE)
                .setBadgeTextSize(8f, true)
                .setBadgeBackgroundColor(getContext().getResources().getColor(R.color.red))
                .setBadgeGravity(Gravity.END | Gravity.TOP)
                .setGravityOffset(3f, 0f, true);
    }

    @Override
    public void setData(LiaoTian data) {
        super.setData(data);
        getUserInfo(data);
        switch (data.getObjectName()) {
            case "RC:ImgMsg":
                textDes.setText("图片");
                break;
            case "RC:VcMsg":
                if (data.getLatestMessage().getUserInfo()!=null){
                    textDes.setText(data.getLatestMessage().getUserInfo().getName() + "：语音消息" );
                }else {
                    textDes.setText( "语音消息" );
                }
                break;
            case "RC:TxtMsg":
                if (data.getLatestMessage().getUserInfo()!=null){
                    textDes.setText(data.getLatestMessage().getUserInfo().getName() + "：" + data.getLatestMessage().getContent());
                }else {
                    textDes.setText(data.getLatestMessage().getContent());
                }
                break;
            default:
                if (TextUtils.equals(data.getSentStatus(), "SENT")) {
                    textDes.setText("您发送了一条消息");
                } else if (TextUtils.equals(data.getSentStatus(), "sentStatus")) {
                    textDes.setText("发送中……");
                } else {
                    textDes.setText("收到了对方的消息");
                }
                break;
        }
        textTime.setText(DateTransforam.stampToDate(data.getReceivedTime()));
        badge.bindTarget(viewKeFu).setBadgeNumber(data.getUnreadMessageCount());
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject(String uid) {
        String url = Constant.HOST + Constant.Url.USER_BUYERINDEX;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", uid);
        return new OkObject(params, url);
    }

    /**
     * 用户信息
     */
    private void getUserInfo(final LiaoTian data) {
        ApiClient.post(getContext(), getOkObject(data.getTargetId()), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("LiaoTianXiaoXiViewHolder--onSuccess", s + "");
                try {
                    UserBuyerindex userBuyerindex = GsonUtils.parseJSON(s, UserBuyerindex.class);
                    if (userBuyerindex.getStatus() == 1) {
                        textTitle.setText(userBuyerindex.getNickname());
                        Glide.with(getContext())
                                .load(userBuyerindex.getHeadimg())
                                .asBitmap()
                                .dontAnimate()
                                .placeholder(R.mipmap.ic_empty)
                                .into(imageKeFu);
                        data.setNickName(userBuyerindex.getNickname());
                    } else if (userBuyerindex.getStatus() == 3) {
                        MyDialog.showReLoginDialog(getContext());
                    } else {
                        Toast.makeText(getContext(), userBuyerindex.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getContext(), "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                Toast.makeText(getContext(), "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
