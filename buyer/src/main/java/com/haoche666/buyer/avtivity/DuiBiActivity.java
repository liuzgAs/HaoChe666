package com.haoche666.buyer.avtivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haoche666.buyer.R;
import com.haoche666.buyer.base.MyDialog;
import com.haoche666.buyer.base.ZjbBaseActivity;
import com.haoche666.buyer.constant.Constant;
import com.haoche666.buyer.customview.MyScrollView;
import com.haoche666.buyer.model.AttentionGetattention;
import com.haoche666.buyer.model.DuiBICanShu;
import com.haoche666.buyer.model.DuiBi;
import com.haoche666.buyer.model.SimpleInfo;
import com.haoche666.buyer.util.ApiClient;
import com.haoche666.buyer.viewholder.DuiBiViewHolder;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;

import java.util.ArrayList;
import java.util.List;

import huisedebi.zjb.mylibrary.util.DpUtils;
import huisedebi.zjb.mylibrary.util.GsonUtils;
import huisedebi.zjb.mylibrary.util.LogUtil;

public class DuiBiActivity extends ZjbBaseActivity implements View.OnClickListener {

    private TextView textViewRight;
    private EasyRecyclerView recyclerView;
    private EasyRecyclerView recyclerViewInfo;
    private RecyclerArrayAdapter<Integer> adapter;
    private RecyclerArrayAdapter<Integer> adapterInfo;
    private MyScrollView scrollView;
    private TextView textCeiling;
    private TextView[] textCeillingArr = new TextView[8];
    private float celilingHeight;
    private View viewCeilling;
    private List<AttentionGetattention.DataBean> duiBiAllData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dui_bi);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {
        Intent intent = getIntent();
        DuiBi duiBi = (DuiBi) intent.getSerializableExtra(Constant.IntentKey.BEAN);
        duiBiAllData = duiBi.getAllData();
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void findID() {
        textViewRight = (TextView) findViewById(R.id.textViewRight);
        recyclerView = (EasyRecyclerView) findViewById(R.id.recyclerView);
        recyclerViewInfo = (EasyRecyclerView) findViewById(R.id.recyclerViewInfo);
        scrollView = (MyScrollView) findViewById(R.id.scrollView);
        textCeiling = (TextView) findViewById(R.id.textCeiling);
        textCeillingArr[0] = (TextView) findViewById(R.id.textCeiling01);
        textCeillingArr[1] = (TextView) findViewById(R.id.textCeiling02);
        textCeillingArr[2] = (TextView) findViewById(R.id.textCeiling03);
        textCeillingArr[3] = (TextView) findViewById(R.id.textCeiling04);
        textCeillingArr[4] = (TextView) findViewById(R.id.textCeiling05);
        textCeillingArr[5] = (TextView) findViewById(R.id.textCeiling06);
        textCeillingArr[6] = (TextView) findViewById(R.id.textCeiling07);
        textCeillingArr[7] = (TextView) findViewById(R.id.textCeiling08);
        celilingHeight = DpUtils.convertDpToPixel(30, DuiBiActivity.this);
        viewCeilling = findViewById(R.id.viewCeilling);
    }

    @Override
    protected void initViews() {
        ((TextView) findViewById(R.id.textViewTitle)).setText("车辆对比");
        textViewRight.setText("隐藏相同");
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter = new RecyclerArrayAdapter<Integer>(DuiBiActivity.this) {

            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_duibi;
                return new DuiBiViewHolder(parent, layout);
            }
        });
        recyclerView.addItemDecoration(new SpaceDecoration((int) DpUtils.convertDpToPixel(1, this)));

        recyclerViewInfo.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewInfo.setAdapter(adapterInfo = new RecyclerArrayAdapter<Integer>(DuiBiActivity.this) {

            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_duibi_info;
                return new DuiBiViewHolder(parent, layout);
            }
        });
        recyclerViewInfo.addItemDecoration(new SpaceDecoration((int) DpUtils.convertDpToPixel(1, this)));
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
        recyclerView.getRecyclerView().addOnScrollListener(new MyOnScrollListener());
        recyclerViewInfo.getRecyclerView().addOnScrollListener(new MyInfoOnScrollListener());
        scrollView.setOnScrollChangedListener(new MyScrollView.OnScrollChangedListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onScrollChanged(int i) {
                LogUtil.LogShitou("DuiBiActivity--onScrollChanged", ""+i);
                LogUtil.LogShitou("DuiBiActivity--onScrollChanged", "Y"+textCeillingArr[1].getY());
                if (i>textCeillingArr[7].getY()){
                    textCeiling.setText(getResources().getText(R.string.duiBiTitle08));
                    return;
                }
                if (i>textCeillingArr[6].getY()){
                    textCeiling.setText(getResources().getText(R.string.duiBiTitle07));
                    setCeiling(i,7);
                    return;
                }
                if (i>textCeillingArr[5].getY()){
                    textCeiling.setText(getResources().getText(R.string.duiBiTitle06));
                    setCeiling(i,6);
                    return;
                }
                if (i>textCeillingArr[4].getY()){
                    textCeiling.setText(getResources().getText(R.string.duiBiTitle05));
                    setCeiling(i,5);
                    return;
                }
                if (i>textCeillingArr[3].getY()){
                    textCeiling.setText(getResources().getText(R.string.duiBiTitle04));
                    setCeiling(i,4);
                    return;
                }
                if (i>textCeillingArr[2].getY()){
                    textCeiling.setText(getResources().getText(R.string.duiBiTitle03));
                    setCeiling(i,3);
                    return;
                }
                if (i>textCeillingArr[1].getY()){
                    textCeiling.setText(getResources().getText(R.string.duiBiTitle02));
                    setCeiling(i,2);
                    return;
                }
                if (i>textCeillingArr[0].getY()){
                    textCeiling.setText(getResources().getText(R.string.duiBiTitle01));
                    setCeiling(i,1);
                    return;
                }
            }

            private void setCeiling(int i,int position) {
                if (i>(textCeillingArr[position].getY()-celilingHeight)&&i<textCeillingArr[position].getY()){
                    viewCeilling.setTranslationY(-1*(i-(textCeillingArr[position].getY()-celilingHeight)));
                }else {
                    viewCeilling.setTranslationY(0);
                }
            }
        });
    }

    /**
     * 第一个被拖拽
     */
    private boolean isDrag = false;
    /**
     * 第二个被拖拽
     */
    private boolean isInfoDrag = false;

    /**
     * @创建者 CSDN_LQR
     * @描述 实现一个RecyclerView.OnScrollListener的子类，当RecyclerView空闲时取消自身的滚动监听
     */
    public class MyOnScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                isDrag = true;
                isInfoDrag = false;
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (!isInfoDrag) {
                recyclerViewInfo.getRecyclerView().scrollBy(dx, dy);
            }
        }
    }

    /**
     * @创建者 CSDN_LQR
     * @描述 实现一个RecyclerView.OnScrollListener的子类，当RecyclerView空闲时取消自身的滚动监听
     */
    public class MyInfoOnScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView1, int newState) {
            super.onScrollStateChanged(recyclerView1, newState);
            if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                isDrag = false;
                isInfoDrag = true;
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView1, int dx, int dy) {
            super.onScrolled(recyclerView1, dx, dy);
            if (!isDrag) {
                recyclerView.getRecyclerView().scrollBy(dx, dy);
            }
        }
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private String getOkObject() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < duiBiAllData.size(); i++) {
            list.add(duiBiAllData.get(i).getId());
        }
        DuiBICanShu duiBICanShu = new DuiBICanShu(1, "android", userInfo.getUid(), tokenTime,list);
        return GsonUtils.parseObject(duiBICanShu);
    }

    @Override
    protected void initData() {
        String url = Constant.HOST + Constant.Url.ATTENTION_GETCONTRASTINFO;
        ApiClient.postJson(this, url,getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("对比参数", s);
                try {
                    SimpleInfo simpleInfo = GsonUtils.parseJSON(s, SimpleInfo.class);
                    if (simpleInfo.getStatus() == 1) {
                    } else if (simpleInfo.getStatus()== 3) {
                        MyDialog.showReLoginDialog(DuiBiActivity.this);
                    } else {
                        showError(simpleInfo.getInfo());
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
                View viewLoader = LayoutInflater.from(DuiBiActivity.this).inflate(R.layout.view_loaderror, null);
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

        adapter.clear();
        adapterInfo.clear();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(1);
        adapter.addAll(list);
        adapterInfo.addAll(list);
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
