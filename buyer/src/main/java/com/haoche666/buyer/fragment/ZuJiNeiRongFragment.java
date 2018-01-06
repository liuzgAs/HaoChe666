package com.haoche666.buyer.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haoche666.buyer.R;
import com.haoche666.buyer.activity.WebActivity;
import com.haoche666.buyer.base.MyDialog;
import com.haoche666.buyer.base.ZjbBaseFragment;
import com.haoche666.buyer.constant.Constant;
import com.haoche666.buyer.model.ArticleHistory;
import com.haoche666.buyer.model.OkObject;
import com.haoche666.buyer.util.ApiClient;
import com.haoche666.buyer.viewholder.ZuJiNRViewHolder;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.HashMap;
import java.util.List;

import huisedebi.zjb.mylibrary.util.DpUtils;
import huisedebi.zjb.mylibrary.util.GsonUtils;
import huisedebi.zjb.mylibrary.util.LogUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZuJiNeiRongFragment extends ZjbBaseFragment implements SwipeRefreshLayout.OnRefreshListener  {

    private View mInflate;
    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<ArticleHistory.DataBean> adapter;
    private int page = 1;
    private RecyclerArrayAdapter.ItemView footer;

    public ZuJiNeiRongFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mInflate == null) {
            mInflate = inflater.inflate(R.layout.fragment_zu_ji_nei_rong, container, false);
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
    protected void initSP() {

    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void findID() {
        recyclerView = mInflate.findViewById(R.id.recyclerView);
    }

    @Override
    protected void initViews() {
        initRecycle();
    }

    private void initRecycle() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setRefreshingColorResources(R.color.basic_color);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<ArticleHistory.DataBean>(getActivity()) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_jing_xuan_tu_wen;
                return new ZuJiNRViewHolder(parent, layout);
            }
        });
        footer = new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                View view = new View(getActivity());
                view.setBackgroundColor(Color.WHITE);
                view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) DpUtils.convertDpToPixel(10, getActivity())));
                return view;
            }

            @Override
            public void onBindView(View headerView) {

            }
        };
        adapter.addFooter(footer);
        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
            ApiClient.post(getActivity(), getOkObject(), new ApiClient.CallBack() {
                @Override
                public void onSuccess(String s) {
                    try {
                        page++;
                        ArticleHistory articleHistory = GsonUtils.parseJSON(s, ArticleHistory.class);
                        int status = articleHistory.getStatus();
                        if (status == 1) {
                            List<ArticleHistory.DataBean> dataBeanList = articleHistory.getData();
                            adapter.addAll(dataBeanList);
                            if (adapter.getAllData().size()==0){
                                adapter.removeFooter(footer);
                                recyclerView.showEmpty();
                            }
                        } else if (status == 3) {
                            MyDialog.showReLoginDialog(getActivity());
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
                Intent intent = new Intent();
                intent.setClass(getActivity(), WebActivity.class);
                intent.putExtra(Constant.IntentKey.TITLE,adapter.getItem(position).getTitle());
                intent.putExtra(Constant.IntentKey.URL,adapter.getItem(position).getUrl());
                startActivity(intent);
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
        String url = Constant.HOST + Constant.Url.ARTICLE_HISTORY;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime",tokenTime);
        }
        params.put("p",page+"");
        return new OkObject(params, url);
    }

    @Override
    public void onRefresh() {
        page = 1;
        ApiClient.post(getActivity(), getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("足迹文章", s);
                try {
                    page++;
                    ArticleHistory articleHistory = GsonUtils.parseJSON(s, ArticleHistory.class);
                    if (articleHistory.getStatus() == 1) {
                        List<ArticleHistory.DataBean> dataBeanList = articleHistory.getData();
                        adapter.clear();
                        adapter.addAll(dataBeanList);
                    } else if (articleHistory.getStatus()== 3) {
                        MyDialog.showReLoginDialog(getActivity());
                    } else {
                        showError(articleHistory.getInfo());
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
                View viewLoader = LayoutInflater.from(getActivity()).inflate(R.layout.view_loaderror, null);
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

}
