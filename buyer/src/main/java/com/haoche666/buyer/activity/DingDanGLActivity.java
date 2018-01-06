package com.haoche666.buyer.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.haoche666.buyer.R;
import com.haoche666.buyer.base.MyDialog;
import com.haoche666.buyer.base.ZjbBaseActivity;
import com.haoche666.buyer.constant.Constant;
import com.haoche666.buyer.model.Carsearch;
import com.haoche666.buyer.model.Corder;
import com.haoche666.buyer.model.OkObject;
import com.haoche666.buyer.util.ApiClient;
import com.haoche666.buyer.viewholder.DingDanGLViewHolder;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;

import java.util.HashMap;
import java.util.List;

import huisedebi.zjb.mylibrary.util.DpUtils;
import huisedebi.zjb.mylibrary.util.GsonUtils;
import huisedebi.zjb.mylibrary.util.LogUtil;

/**
 * 订单管理
 *
 * @author Administrator
 */
public class DingDanGLActivity extends ZjbBaseActivity implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {
    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<Corder.DataBean> adapter;
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ding_dan_gl);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void findID() {
        recyclerView = (EasyRecyclerView) findViewById(R.id.recyclerView);
    }

    @Override
    protected void initViews() {
        ((TextView) findViewById(R.id.textViewTitle)).setText("订单管理");
        initRecycle();
    }

    private void initRecycle() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(DingDanGLActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) DpUtils.convertDpToPixel(1f, DingDanGLActivity.this), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setRefreshingColorResources(R.color.basic_color);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<Corder.DataBean>(DingDanGLActivity.this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_ding_dan_gl;
                return new DingDanGLViewHolder(parent, layout, viewType);
            }

            @Override
            public int getViewType(int position) {
                return getItem(position).getType_id();
            }
        });
        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
            ApiClient.post(DingDanGLActivity.this, getOkObject(), new ApiClient.CallBack() {
                @Override
                public void onSuccess(String s) {
                    LogUtil.LogShitou("DingDanGLActivity--加载更多", s+"");
                    try {
                        page++;
                        Corder corder = GsonUtils.parseJSON(s, Corder.class);
                        int status = corder.getStatus();
                        if (status == 1) {
                            List<Corder.DataBean> dataBeanList = corder.getData();
                            adapter.addAll(dataBeanList);
                        } else if (status == 3) {
                            MyDialog.showReLoginDialog(DingDanGLActivity.this);
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
                showLoadingDialog();
                ApiClient.post(DingDanGLActivity.this, getJieGuoOkObject(adapter.getItem(position)), new ApiClient.CallBack() {
                    @Override
                    public void onSuccess(String s) {
                        cancelLoadingDialog();
                        LogUtil.LogShitou("ChaWeiBaoActivity--onSuccess", s + "");
                        try {
                            Carsearch carsearch = GsonUtils.parseJSON(s, Carsearch.class);
                            if (carsearch.getStatus() == 1) {
                                Intent intent = new Intent();
                                intent.setClass(DingDanGLActivity.this,WebActivity.class);
                                intent.putExtra(Constant.IntentKey.TITLE,"查维保");
                                intent.putExtra(Constant.IntentKey.URL,carsearch.getUrl());
                                startActivity(intent);
                            } else if (carsearch.getStatus() == 3) {
                                MyDialog.showReLoginDialog(DingDanGLActivity.this);
                            } else {
                                Toast.makeText(DingDanGLActivity.this, carsearch.getInfo(), Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Toast.makeText(DingDanGLActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError() {
                        cancelLoadingDialog();
                        Toast.makeText(DingDanGLActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
    }

    @Override
    protected void initData() {
        onRefresh();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageBack:
                finish();
                break;
            default:

                break;
        }
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getJieGuoOkObject(Corder.DataBean dataBean) {
        String url = Constant.HOST + Constant.Url.CARSEARCH;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime",tokenTime);
        }
        params.put("order_no",dataBean.getOrder_no());
        params.put("type_id",dataBean.getType_id()+"");
        return new OkObject(params, url);
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.CORDER;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime",tokenTime);
        }
        params.put("p",page+"");
        params.put("status",2+"");
        return new OkObject(params, url);
    }

    @Override
    public void onRefresh() {
        page = 1;
        ApiClient.post(this, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("订单列表", s);
                try {
                    page++;
                    Corder corder = GsonUtils.parseJSON(s, Corder.class);
                    if (corder.getStatus() == 1) {
                        List<Corder.DataBean> dataBeanList = corder.getData();
                        adapter.clear();
                        adapter.addAll(dataBeanList);
                    } else if (corder.getStatus() == 3) {
                        MyDialog.showReLoginDialog(DingDanGLActivity.this);
                    } else {
                        showError(corder.getInfo());
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
                View viewLoader = LayoutInflater.from(DingDanGLActivity.this).inflate(R.layout.view_loaderror, null);
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
