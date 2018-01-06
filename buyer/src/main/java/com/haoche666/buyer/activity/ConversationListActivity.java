package com.haoche666.buyer.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.haoche666.buyer.R;
import com.haoche666.buyer.application.MyApplication;
import com.haoche666.buyer.base.SwipeBackActivity;
import com.haoche666.buyer.util.StatusBarUtils;

import me.imid.swipebacklayout.lib.SwipeBackLayout;

/**
 * Created by zhangjiebo on 2018/1/6/006.
 *
 * @author ZhangJieBo
 */

public class ConversationListActivity extends SwipeBackActivity implements View.OnClickListener {

    private SwipeBackLayout mSwipeBackLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversationlist);
        mSwipeBackLayout = getSwipeBackLayout();
        // 设置滑动方向，可设置EDGE_LEFT, EDGE_RIGHT, EDGE_ALL, EDGE_BOTTOM
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        init();
    }

    public void init() {
        MyApplication.getInstance().addActivity(this);
        initSP();
        initIntent();
        findID();
        initViews();
        setListeners();
    }

    protected void initSP() {

    }

    protected void initIntent() {

    }

    protected void findID() {

    }

    protected void initViews() {
        ((TextView) findViewById(R.id.textViewTitle)).setText("客服对话列表");
        StatusBarUtils.setWindowStatusBarColor(this,R.color.tranblack);
    }

    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
    }

    protected void initData() {

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
