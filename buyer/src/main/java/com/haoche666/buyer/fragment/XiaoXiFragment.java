package com.haoche666.buyer.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haoche666.buyer.R;
import com.haoche666.buyer.activity.XiTongXXActivity;
import com.haoche666.buyer.base.ZjbBaseFragment;
import com.haoche666.buyer.constant.Constant;
import com.haoche666.buyer.model.LiaoTian;
import com.haoche666.buyer.model.UserInfo;
import com.haoche666.buyer.viewholder.LiaoTianXiaoXiViewHolder;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import huisedebi.zjb.mylibrary.util.ACache;
import huisedebi.zjb.mylibrary.util.GsonUtils;
import huisedebi.zjb.mylibrary.util.LogUtil;
import huisedebi.zjb.mylibrary.util.ScreenUtils;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;

/**
 * A simple {@link Fragment} subclass.
 */
public class XiaoXiFragment extends ZjbBaseFragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {


    private View mInflate;
    private View mRelaTitleStatue;
    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<LiaoTian> adapter;

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
        recyclerView = mInflate.findViewById(R.id.recyclerView);
    }

    @Override
    protected void initViews() {
        ViewGroup.LayoutParams layoutParams = mRelaTitleStatue.getLayoutParams();
        layoutParams.height = (int) (getResources().getDimension(R.dimen.titleHeight) + ScreenUtils.getStatusBarHeight(getActivity()));
        mRelaTitleStatue.setLayoutParams(layoutParams);
        mRelaTitleStatue.setPadding(0, ScreenUtils.getStatusBarHeight(getActivity()), 0, 0);
        initRecycler();
    }

    /**
     * 初始化recyclerview
     */
    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) getResources().getDimension(R.dimen.line_width), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setRefreshingColorResources(R.color.basic_color);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<LiaoTian>(getActivity()) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_xiaoxi;
                return new LiaoTianXiaoXiViewHolder(parent, layout);
            }
        });
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.header_xiao_xi, null);
                view.findViewById(R.id.viewXiTongXiaoXi).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.setClass(getActivity(), XiTongXXActivity.class);
                        startActivity(intent);
                    }
                });
                return view;
            }

            @Override
            public void onBindView(View headerView) {

            }
        });
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                RongIM.getInstance().startConversation(getActivity(), Conversation.ConversationType.PRIVATE,adapter.getItem(position).getTargetId(),adapter.getItem(position).getNickName());
            }
        });
        recyclerView.setRefreshListener(this);
    }

    @Override
    protected void setListeners() {
        RongIM.setOnReceiveMessageListener(new RongIMClient.OnReceiveMessageListener() {
            @Override
            public boolean onReceived(Message message, int i) {
                onRefresh();
                return false;
            }
        });
        RongIM.getInstance().setSendMessageListener(new RongIM.OnSendMessageListener() {
            @Override
            public Message onSend(Message message) {
                onRefresh();
                return message;
            }

            @Override
            public boolean onSent(Message message, RongIM.SentMessageErrorCode sentMessageErrorCode) {
                return false;
            }
        });
    }

    @Override
    protected void initData() {
        onRefresh();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View view) {
    }

    /**
     * 链接融云
     */
    private void connectRongYun() {
        ACache aCache = ACache.get(getActivity(), Constant.Acache.APP);
        final UserInfo userInfo = (UserInfo) aCache.getAsObject(Constant.Acache.USER_INFO);
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
//                RongIM.getInstance().startConversationList(getActivity(), supportedConversation);
                List<Conversation> conversationList = RongIM.getInstance().getRongIMClient().getConversationList();
                List<LiaoTian> liaoTianList = new ArrayList<>();
                for (int i = 0; i < conversationList.size(); i++) {
                    LogUtil.LogShitou("XiaoXiFragment--onSuccess", GsonUtils.parseObject(conversationList.get(i)));
                    LiaoTian liaoTian = GsonUtils.parseJSON(GsonUtils.parseObject(conversationList.get(i)), LiaoTian.class);
                    liaoTianList.add(liaoTian);
                }
                adapter.clear();
                adapter.addAll(liaoTianList);
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

    @Override
    public void onRefresh() {
        connectRongYun();
    }
}
