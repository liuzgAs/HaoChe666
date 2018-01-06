package com.haoche666.buyer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.haoche666.buyer.R;
import com.haoche666.buyer.base.ToLoginActivity;
import com.haoche666.buyer.base.ZjbBaseActivity;
import com.haoche666.buyer.constant.Constant;
import com.haoche666.buyer.util.DataCleanManager;

import huisedebi.zjb.mylibrary.util.VersionUtils;

/**
 * 设置
 *
 * @author Administrator
 */
public class SheZhiActivity extends ZjbBaseActivity implements View.OnClickListener {

    private TextView textHuanCun;
    private TextView textBanben;

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
        textHuanCun = (TextView) findViewById(R.id.textHuanCun);
        textBanben = (TextView) findViewById(R.id.textBanben);
    }

    @Override
    protected void initViews() {
        ((TextView) findViewById(R.id.textViewTitle)).setText("设置");
        textHuanCun.setText(getSize());
        textBanben.setText("v" + VersionUtils.getCurrVersionName(this));
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
        findViewById(R.id.viewYiJianFanKui).setOnClickListener(this);
        findViewById(R.id.viewChangJianWenTi).setOnClickListener(this);
        findViewById(R.id.viewXiuGaiMM).setOnClickListener(this);
        findViewById(R.id.viewHuanCun).setOnClickListener(this);
        findViewById(R.id.viewAbout).setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.viewAbout:
                intent.setClass(SheZhiActivity.this,WebActivity.class);
                intent.putExtra(Constant.IntentKey.TITLE,"关于我们");
                intent.putExtra(Constant.IntentKey.URL,Constant.Url.ABOUT);
                startActivity(intent);
                break;
            case R.id.viewHuanCun:
                DataCleanManager.clearAllCache(this);
                textHuanCun.setText(getSize());
                Toast.makeText(this, "缓存清除完毕", Toast.LENGTH_SHORT).show();
                break;
            case R.id.viewXiuGaiMM:
                if (isLogin) {
                    intent.setClass(this, XiuGaiMMActivity.class);
                    startActivity(intent);
                } else {
                    ToLoginActivity.toLoginActivity(this);
                }
                break;
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

    /**
     * -------------获取缓存大小-----------------
     */
    private String getSize() {
        String totalCacheSize = null;
        try {
            totalCacheSize = DataCleanManager.getTotalCacheSize(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalCacheSize;
    }
}
