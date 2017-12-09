package com.haoche666.buyer.avtivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.haoche666.buyer.R;
import com.haoche666.buyer.base.MyDialog;
import com.haoche666.buyer.base.ZjbBaseActivity;
import com.haoche666.buyer.constant.Constant;
import com.haoche666.buyer.model.OkObject;
import com.haoche666.buyer.model.StoreDetails;
import com.haoche666.buyer.util.ApiClient;
import com.haoche666.buyer.viewholder.CheHangViewHolder;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;

import java.util.HashMap;
import java.util.List;

import huisedebi.zjb.mylibrary.util.DpUtils;
import huisedebi.zjb.mylibrary.util.GsonUtils;
import huisedebi.zjb.mylibrary.util.LogUtil;
import huisedebi.zjb.mylibrary.util.RecycleViewDistancaUtil;
import huisedebi.zjb.mylibrary.util.ScreenUtils;

/**
 * 车行信息
 * @author Administrator
 */
public class CheHangXXActivity extends ZjbBaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<StoreDetails.DataBean> adapter;
    private int page = 1;
    private View viewBar;
    private int viewBarHeight;
    private int id;
    private StoreDetails.StoreBean storeDetailsStore;
    private TextView textViewTitle;
    private View viewBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_che_hang_xx);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {
        Intent intent = getIntent();
        id = intent.getIntExtra(Constant.IntentKey.ID, 0);
    }

    @Override
    protected void findID() {
        viewBar = findViewById(R.id.viewBar);
        recyclerView = (EasyRecyclerView) findViewById(R.id.recyclerView);
        textViewTitle = (TextView) findViewById(R.id.textViewTitle);
        viewBottom = findViewById(R.id.viewBottom);
    }

    @Override
    protected void initViews() {
        viewBottom.setVisibility(View.INVISIBLE);
        viewBarHeight = (int) (getResources().getDimension(R.dimen.titleHeight) + ScreenUtils.getStatusBarHeight(this));
        textViewTitle.setText("好车666");
        viewBar.getBackground().mutate().setAlpha(0);
        initRecycle();
    }

    private void initRecycle() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(CheHangXXActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) DpUtils.convertDpToPixel(1f, CheHangXXActivity.this), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setRefreshingColorResources(R.color.basic_color);
        recyclerView.getSwipeToRefresh().setProgressViewOffset(true, 30, 220);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<StoreDetails.DataBean>(CheHangXXActivity.this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_che_hang;
                return new CheHangViewHolder(parent, layout);
            }
        });
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {

            private ImageView imageLogo;
            private TextView textCompany;
            private TextView textName;
            private TextView textText2;
            private TextView textText1;

            @Override
            public View onCreateView(ViewGroup parent) {
                View view = LayoutInflater.from(CheHangXXActivity.this).inflate(R.layout.header_che_hang, null);
                textText1 = view.findViewById(R.id.textText1);
                textText2 = view.findViewById(R.id.textText2);
                textName = view.findViewById(R.id.textName);
                textCompany = view.findViewById(R.id.textCompany);
                imageLogo = view.findViewById(R.id.imageLogo);
                return view;
            }

            @Override
            public void onBindView(View headerView) {
                if (storeDetailsStore!=null){
                    textText1.setText(storeDetailsStore.getText1());
                    textText2.setText(storeDetailsStore.getText2());
                    textName.setText(storeDetailsStore.getName());
                    textCompany.setText(storeDetailsStore.getCompany());
                    Glide.with(CheHangXXActivity.this)
                            .load(storeDetailsStore.getLogo())
                            .asBitmap()
                            .dontAnimate()
                            .placeholder(R.mipmap.ic_empty)
                            .into(imageLogo);
                }
            }
        });
        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                page++;
                ApiClient.post(CheHangXXActivity.this, getOkObject(), new ApiClient.CallBack() {
                    @Override
                    public void onSuccess(String s) {
                        try {
                            page++;
                            StoreDetails storeDetails = GsonUtils.parseJSON(s, StoreDetails.class);
                            int status = storeDetails.getStatus();
                            if (status == 1) {
                                List<StoreDetails.DataBean> storeDetailsData = storeDetails.getData();
                                adapter.addAll(storeDetailsData);
                            } else if (status == 3) {
                                MyDialog.showReLoginDialog(CheHangXXActivity.this);
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
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int scrollY = RecycleViewDistancaUtil.getDistance(recyclerView, 0);
                float guangGaoHeight = DpUtils.convertDpToPixel(220f,CheHangXXActivity.this);
                if (scrollY <= guangGaoHeight - viewBarHeight && scrollY >= 0) {
                    int i = (int) ((double) scrollY / (double) (guangGaoHeight - viewBar.getHeight()) * 255);
                    viewBar.getBackground().mutate().setAlpha(i);
                    LogUtil.LogShitou("CheHangXXActivity--onScrolled", ""+i);
                } else {
                    viewBar.getBackground().mutate().setAlpha(255);
                    LogUtil.LogShitou("CheHangXXActivity--onScrolled", "255");
                }
            }
        });
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

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
        switch (view.getId()){
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
    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.STORE_DETAILS;
        HashMap<String, String> params = new HashMap<>();
        params.put("id",id+"");
        params.put("p",page+"");
        return new OkObject(params, url);
    }

    @Override
    public void onRefresh() {
        page = 1;
        ApiClient.post(this, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("车行详情", s);
                try {
                    page++;
                    StoreDetails storeDetails = GsonUtils.parseJSON(s, StoreDetails.class);
                    if (storeDetails.getStatus() == 1) {
                        viewBottom.setVisibility(View.VISIBLE);
                        storeDetailsStore = storeDetails.getStore();
                        viewBar.getBackground().mutate().setAlpha(0);
                        textViewTitle.setText(storeDetailsStore.getName());
                        List<StoreDetails.DataBean> storeDetailsData = storeDetails.getData();
                        adapter.clear();
                        adapter.addAll(storeDetailsData);
                    } else if (storeDetails.getStatus()== 3) {
                        MyDialog.showReLoginDialog(CheHangXXActivity.this);
                    } else {
                        showError(storeDetails.getInfo());
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
                View viewLoader = LayoutInflater.from(CheHangXXActivity.this).inflate(R.layout.view_loaderror, null);
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
                textViewTitle.setText("好车666");
                viewBar.getBackground().mutate().setAlpha(255);
            }
        });
    }
}
