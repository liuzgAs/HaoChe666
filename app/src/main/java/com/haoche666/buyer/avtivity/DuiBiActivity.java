package com.haoche666.buyer.avtivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haoche666.buyer.R;
import com.haoche666.buyer.base.ZjbBaseActivity;
import com.haoche666.buyer.customview.MyScrollView;
import com.haoche666.buyer.viewholder.DuiBiViewHolder;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;

import java.util.ArrayList;
import java.util.List;

import huisedebi.zjb.mylibrary.util.DpUtils;
import huisedebi.zjb.mylibrary.util.LogUtil;

public class DuiBiActivity extends ZjbBaseActivity implements View.OnClickListener {

    private TextView textViewRight;
    private EasyRecyclerView recyclerView;
    private EasyRecyclerView recyclerViewInfo;
    private RecyclerArrayAdapter<Integer> adapter;
    private RecyclerArrayAdapter<Integer> adapterInfo;
    private MyScrollView scrollView;

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

    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void findID() {
        textViewRight = (TextView) findViewById(R.id.textViewRight);
        recyclerView = (EasyRecyclerView) findViewById(R.id.recyclerView);
        recyclerViewInfo = (EasyRecyclerView) findViewById(R.id.recyclerViewInfo);
        scrollView = (MyScrollView) findViewById(R.id.scrollView);
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
            @Override
            public void onScrollChanged(int i) {
                LogUtil.LogShitou("DuiBiActivity--onScrollChanged", "i");
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
                isInfoDrag =false;
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (!isInfoDrag){
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
                isInfoDrag =true;
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView1, int dx, int dy) {
            super.onScrolled(recyclerView1, dx, dy);
            if (!isDrag){
                recyclerView.getRecyclerView().scrollBy(dx, dy);
            }
        }
    }

    @Override
    protected void initData() {
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
