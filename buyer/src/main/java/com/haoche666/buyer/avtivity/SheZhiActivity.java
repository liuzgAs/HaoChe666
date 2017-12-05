package com.haoche666.buyer.avtivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.haoche666.buyer.R;
import com.haoche666.buyer.base.ZjbBaseActivity;

/**
 * 设置
 *
 * @author Administrator
 */
public class SheZhiActivity extends ZjbBaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_she_zhi);
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

    }

    @Override
    protected void initViews() {
        ((TextView) findViewById(R.id.textViewTitle)).setText("设置");
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
        findViewById(R.id.viewYiJianFanKui).setOnClickListener(this);
        findViewById(R.id.viewChangJianWenTi).setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.viewChangJianWenTi:
                intent.setClass(this, ChangJianWenTiActivity.class);
                startActivity(intent);
                break;
            case R.id.viewYiJianFanKui:
                intent.setClass(this, YiJianFKActivity.class);
                startActivity(intent);
                break;
            case R.id.imageBack:
                finish();
                break;
            default:
                break;
        }
    }
}
