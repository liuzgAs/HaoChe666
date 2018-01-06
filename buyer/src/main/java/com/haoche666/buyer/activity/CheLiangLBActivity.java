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

import com.haoche666.buyer.R;
import com.haoche666.buyer.base.MyDialog;
import com.haoche666.buyer.base.ZjbBaseActivity;
import com.haoche666.buyer.constant.Constant;
import com.haoche666.buyer.model.Buyer;
import com.haoche666.buyer.model.MaiChe;
import com.haoche666.buyer.util.ApiClient;
import com.haoche666.buyer.viewholder.ShouYeViewHolder;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;

import java.util.ArrayList;
import java.util.List;

import huisedebi.zjb.mylibrary.util.GsonUtils;
import huisedebi.zjb.mylibrary.util.LogUtil;

public class CheLiangLBActivity extends ZjbBaseActivity implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<Buyer.DataBean> adapter;
    private Buyer.HotSearch hotSearch;
    private Buyer.HotCar hotCar;
    private boolean isFromDuiBi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_che_liang_lb);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {
        Intent intent = getIntent();
        hotSearch = (Buyer.HotSearch) intent.getSerializableExtra(Constant.IntentKey.BEAN);
        if (hotSearch != null) {
            switch (hotSearch.getType_id()) {
                case 1:
                    z_price = hotSearch.getValue();
                    break;
                case 2:
                    z_age = hotSearch.getValue();
                    break;
                case 3:
                    title = hotSearch.getTitle();
                    break;
                default:
                    break;
            }
        }
        hotCar = (Buyer.HotCar) intent.getSerializableExtra(Constant.IntentKey.VALUE);
        if (hotCar != null) {
            hotcat_id = hotCar.getId();
        }
        isFromDuiBi = intent.getBooleanExtra(Constant.IntentKey.IS_FROM_DUI_BI, false);
    }

    @Override
    protected void findID() {
        recyclerView = (EasyRecyclerView) findViewById(R.id.recyclerView);
    }

    @Override
    protected void initViews() {
        if (hotSearch != null) {
            ((TextView) findViewById(R.id.textViewTitle)).setText(hotSearch.getTitle());
        }
        if (hotCar != null) {
            ((TextView) findViewById(R.id.textViewTitle)).setText(hotCar.getTitle());
        }
        if (isFromDuiBi) {
            ((TextView) findViewById(R.id.textViewTitle)).setText("全部车源");
        }
        initRecycler();
    }

    /**
     * 初始化recyclerview
     */
    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) getResources().getDimension(R.dimen.line_width), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setRefreshingColorResources(R.color.basic_color);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<Buyer.DataBean>(CheLiangLBActivity.this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_shou_ye;
                return new ShouYeViewHolder(parent, layout, 1);
            }
        });
        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                ApiClient.postJson(CheLiangLBActivity.this, url, getOkObject(), new ApiClient.CallBack() {
                    @Override
                    public void onSuccess(String s) {
                        try {
                            page++;
                            Buyer buyer = GsonUtils.parseJSON(s, Buyer.class);
                            int status = buyer.getStatus();
                            if (status == 1) {
                                List<Buyer.DataBean> dataBeanList = buyer.getData();
                                adapter.addAll(dataBeanList);
                            } else if (status == 3) {
                                MyDialog.showReLoginDialog(CheLiangLBActivity.this);
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
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent();
                intent.setClass(CheLiangLBActivity.this, CheLiangXQActivity.class);
                intent.putExtra(Constant.IntentKey.ID,adapter.getItem(position).getId());
                startActivity(intent);
            }
        });
        recyclerView.setRefreshListener(this);
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
    }

    @Override
    protected void initData() {
        onRefresh();
    }

    String url = Constant.HOST + Constant.Url.CAR;
    private int bid = 0;
    private int bsid = 0;
    private int sort_id = 0;
    private int city_id = 0;
    private List<Integer> z_price = new ArrayList<>();
    private List<Integer> z_age = new ArrayList<>();
    private String title = "";
    private int page = 1;
    private int hotcat_id = 0;

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private String getOkObject() {
        MaiChe maiChe;
        if (isLogin) {
            maiChe = new MaiChe(1, "android", userInfo.getUid(), tokenTime, page, bid, bsid, sort_id, hotcat_id, city_id, z_price, z_age, title);
        } else {
            maiChe = new MaiChe(1, "android", page, bid, bsid, sort_id, hotcat_id, city_id, z_price, z_age, title);
        }
        return GsonUtils.parseObject(maiChe);
    }

    @Override
    public void onRefresh() {
        page = 1;
        recyclerView.showProgress();
        ApiClient.postJson(CheLiangLBActivity.this, url, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("买车", s);
                try {
                    page++;
                    Buyer buyer = GsonUtils.parseJSON(s, Buyer.class);
                    if (buyer.getStatus() == 1) {
                        List<Buyer.DataBean> dataBeanList = buyer.getData();
                        adapter.clear();
                        adapter.addAll(dataBeanList);
                    } else if (buyer.getStatus() == 3) {
                        MyDialog.showReLoginDialog(CheLiangLBActivity.this);
                    } else {
                        showError(buyer.getInfo());
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
                View viewLoader = LayoutInflater.from(CheLiangLBActivity.this).inflate(R.layout.view_loaderror, null);
                TextView textMsg = viewLoader.findViewById(R.id.textMsg);
                textMsg.setText(msg);
                viewLoader.findViewById(R.id.buttonReLoad).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        recyclerView.showProgress();
                        onRefresh();
                    }
                });
                recyclerView.setErrorView(viewLoader);
                recyclerView.showError();
            }
        });
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
}
