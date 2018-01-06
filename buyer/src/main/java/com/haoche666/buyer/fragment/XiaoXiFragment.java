package com.haoche666.buyer.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.haoche666.buyer.R;
import com.haoche666.buyer.activity.XiTongXXActivity;
import com.haoche666.buyer.base.ToLoginActivity;
import com.haoche666.buyer.base.ZjbBaseFragment;

import java.util.HashMap;
import java.util.Map;

import huisedebi.zjb.mylibrary.util.LogUtil;
import huisedebi.zjb.mylibrary.util.ScreenUtils;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

/**
 * A simple {@link Fragment} subclass.
 */
public class XiaoXiFragment extends ZjbBaseFragment implements View.OnClickListener {


    private View mInflate;
    private View mRelaTitleStatue;
//    private Badge badge;
    private ImageView imageKeFu;

    public XiaoXiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mInflate == null) {
            mInflate = inflater.inflate(R.layout.fragment_xiao_xi, container, false);
            init();
        }
        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) mInflate.getParent();
        if (parent != null) {
            parent.removeView(mInflate);
        }
        return mInflate;
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void findID() {
        mRelaTitleStatue = mInflate.findViewById(R.id.relaTitleStatue);
        imageKeFu = mInflate.findViewById(R.id.imageKeFu);
    }

    @Override
    protected void initViews() {
        ViewGroup.LayoutParams layoutParams = mRelaTitleStatue.getLayoutParams();
        layoutParams.height = (int) (getResources().getDimension(R.dimen.titleHeight) + ScreenUtils.getStatusBarHeight(getActivity()));
        mRelaTitleStatue.setLayoutParams(layoutParams);
        mRelaTitleStatue.setPadding(0, ScreenUtils.getStatusBarHeight(getActivity()), 0, 0);
//        badge = new QBadgeView(getActivity())
//                .setBadgeTextColor(Color.WHITE)
//                .setBadgeTextSize(8f, true)
//                .setBadgeBackgroundColor(getResources().getColor(R.color.red))
//                .setBadgeGravity(Gravity.END | Gravity.TOP)
//                .setGravityOffset(3f, 0f, true);
    }

    @Override
    protected void setListeners() {
        mInflate.findViewById(R.id.viewXiTongXiaoXi).setOnClickListener(this);
        mInflate.findViewById(R.id.viewKeFu).setOnClickListener(this);
    }

    @Override
    protected void initData() {
//        connectRongYun();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        //        RongIM.getInstance().addUnReadMessageCountChangedObserver(new IUnReadMessageObserver() {
//            @Override
//            public void onCountChanged(int i) {
//                if (i > 0 && i <= 99) {
//                    badge.bindTarget(imageKeFu).setBadgeText(i + "");
//                } else if (i > 99) {
//                    badge.bindTarget(imageKeFu).setBadgeText("99+");
//                } else {
//                    badge.bindTarget(imageKeFu).setBadgeText(null);
//                }
//            }
//        }, Conversation.ConversationType.PRIVATE);
//        if (isLogin){
//            connectRongYun();
//        }else {
//            LogUtil.LogShitou("XiaoXiFragment--onStart", "111111");
//        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.viewKeFu:
                if (isLogin) {
                    connectRongYun();
                } else {
                    ToLoginActivity.toLoginActivity(getActivity());
                }
                break;
            case R.id.viewXiTongXiaoXi:
                intent.setClass(getActivity(), XiTongXXActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    /**
     * 链接融云
     */
    private void connectRongYun() {
        RongIM.connect(userInfo.getYunToken(), new RongIMClient.ConnectCallback() {

            /**
             * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
             *                  2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
             */
            @Override
            public void onTokenIncorrect() {
                LogUtil.LogShitou("CheLiangXQActivity--onTokenIncorrect", "1111");
            }

            /**
             * 连接融云成功
             * @param userid 当前 token 对应的用户 id
             */
            @Override
            public void onSuccess(String userid) {
                final io.rong.imlib.model.UserInfo userInfoRongYun = new io.rong.imlib.model.UserInfo(userInfo.getUid(), userInfo.getUserName(), Uri.parse(userInfo.getHeadImg()));
//                RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
//                    @Override
//                    public UserInfo getUserInfo(String s) {
//                        return userInfoRongYun;
//                    }
//                },true);
//                RongIM.getInstance().refreshUserInfoCache(userInfoRongYun);
                /**
                 * 设置当前用户信息，
                 * @param userInfo 当前用户信息
                 */
                RongIM.getInstance().setCurrentUserInfo(userInfoRongYun);
                /**
                 * 设置消息体内是否携带用户信息。
                 * @param state 是否携带用户信息，true 携带，false 不携带。
                 */
                RongIM.getInstance().setMessageAttachedUserInfo(true);
                Map<String, Boolean> supportedConversation = new HashMap<>();
                supportedConversation.put(Conversation.ConversationType.PRIVATE.getName(), false);
                RongIM.getInstance().startConversationList(getActivity(), supportedConversation);
            }

            /**
             * 连接融云失败
             * @param errorCode 错误码，可到官网 查看错误码对应的注释
             */
            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                LogUtil.LogShitou("CheLiangXQActivity--onError", "" + errorCode.toString());
            }
        });
    }
}
