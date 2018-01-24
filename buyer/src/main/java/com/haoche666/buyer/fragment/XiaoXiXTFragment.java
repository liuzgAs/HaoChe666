package com.haoche666.buyer.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haoche666.buyer.R;
import com.haoche666.buyer.base.MyDialog;
import com.haoche666.buyer.base.ZjbBaseFragment;
import com.haoche666.buyer.constant.Constant;
import com.haoche666.buyer.model.OkObject;
import com.haoche666.buyer.model.UserMsg;
import com.haoche666.buyer.util.ApiClient;
import com.haoche666.buyer.viewholder.XiaoXiViewHolder;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.HashMap;
import java.util.List;

import huisedebi.zjb.mylibrary.util.GsonUtils;
import huisedebi.zjb.mylibrary.util.LogUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class XiaoXiXTFragment extends ZjbBaseFragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {


    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<UserMsg.DataBean> adapter;
    private int page = 1;
    private int type = 1;

    private View mInflate;

    public XiaoXiXTFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public XiaoXiXTFragment(int type) {
        // Required empty public constructor
        this.type =type;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mInflate == null) {
            mInflate = inflater.inflate(R.layout.fragment_xiao_xi_xt, container, false);
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
        recyclerView = mInflate.findViewById(R.id.recyclerView);
    }

    @Override
    protected void initViews() {
        initRecycler();
    }

    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setRefreshingColorResources(R.color.basic_color);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<UserMsg.DataBean>(mContext) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_gonggao;
                return new XiaoXiViewHolder(parent, layout);
            }
        });
        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
             ApiClient.post(mContext, getOkObject(), new ApiClient.CallBack() {
                 @Override
                 public void onSuccess(String s) {
                     LogUtil.LogShitou("DingDanGLActivity--加载更多", s+"");
                     try {
                         page++;
                         UserMsg userMsg = GsonUtils.parseJSON(s, UserMsg.class);
                         int status = userMsg.getStatus();
                         if (status == 1) {
                             List<UserMsg.DataBean> dataBeanList = userMsg.getData();
                             adapter.addAll(dataBeanList);
                         } else if (status == 3) {
                             MyDialog.showReLoginDialog(mContext);
                         } else {
                             adapter.pauseMore();
                         }
                     } catch (Exception e) {
                         adapter.pauseMore();
                     }
                 }

                 @Override
                 public void onError() {
                     adapter.pauseMore();
                 }
             });
            }

            @Override
            public void onMoreClick() {

            }
        });
        adapter.setNoMore(R.layout.view_nomore, new RecyclerArrayAdapter.OnNoMoreListener() {
            @Override
            public void onNoMoreShow() {

            }

            @Override
            public void onNoMoreClick() {
            }
        });
        adapter.setError(R.layout.view_error, new RecyclerArrayAdapter.OnErrorListener() {
            @Override
            public void onErrorShow() {
                adapter.resumeMore();
            }

            @Override
            public void onErrorClick() {
                adapter.resumeMore();
            }
        });
        recyclerView.setRefreshListener(this);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void initData() {
        onRefresh();
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.USER_MSG;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        params.put("p", String.valueOf(page));
        params.put("type", type + "");
        return new OkObject(params, url);
    }

    @Override
    public void onRefresh() {
        page = 1;
        ApiClient.post(mContext, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("消息"+type, s);
                try {
                    page++;
                    UserMsg userMsg = GsonUtils.parseJSON(s, UserMsg.class);
                    if (userMsg.getStatus() == 1) {
                        List<UserMsg.DataBean> dataBeanList = userMsg.getData();
                        adapter.clear();
                        adapter.addAll(dataBeanList);
                    } else if (userMsg.getStatus() == 3) {
                        MyDialog.showReLoginDialog(mContext);
                    } else {
                        showError(userMsg.getInfo());
                    }
                } catch (Exception e) {
                    showError("数据出错");
                }
            }

            @Override
            public void onError() {
                showError("网络出错");
            }

            /**
             * 错误显示
             * @param msg
             */
            private void showError(String msg) {
                View viewLoader = LayoutInflater.from(mContext).inflate(R.layout.view_loaderror, null);
                TextView textMsg = viewLoader.findViewById(R.id.textMsg);
                textMsg.setText(msg);
                viewLoader.findViewById(R.id.buttonReLoad).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        recyclerView.showProgress();
                        initData();
                    }
                });
                recyclerView.setErrorView(viewLoader);
                recyclerView.showError();
            }
        });
    }

    @Override
    public void onClick(View view) {

    }
}
