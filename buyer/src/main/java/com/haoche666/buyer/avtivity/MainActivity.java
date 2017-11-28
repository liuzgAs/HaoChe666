package com.haoche666.buyer.avtivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.haoche666.buyer.R;
import com.haoche666.buyer.application.MyApplication;
import com.haoche666.buyer.base.ZjbBaseNotLeftActivity;
import com.haoche666.buyer.constant.Constant;
import com.haoche666.buyer.fragment.MaiCheFragment;
import com.haoche666.buyer.fragment.SellCheFragment;
import com.haoche666.buyer.fragment.ShouYeFragment;
import com.haoche666.buyer.fragment.WoDeFragment;
import com.haoche666.buyer.fragment.XiaoXiFragment;
import com.haoche666.buyer.interfacepage.OnPatchLister;
import com.taobao.sophix.SophixManager;

import huisedebi.zjb.mylibrary.util.DpUtils;
import huisedebi.zjb.mylibrary.util.UpgradeUtils;

/**
 * 主界面
 * @author Administrator
 */
public class MainActivity extends ZjbBaseNotLeftActivity {
    private String[] tabsItem = new String[5];
    private Class[] fragment = new Class[]{
            ShouYeFragment.class,
            MaiCheFragment.class,
            SellCheFragment.class,
            XiaoXiFragment.class,
            WoDeFragment.class
    };
    private int[] imgRes = new int[]{
            R.drawable.selector_item01,
            R.drawable.selector_item02,
            R.drawable.selector_item03,
            R.drawable.selector_item04,
            R.drawable.selector_item05
    };
    public FragmentTabHost mTabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //检查更新
        UpgradeUtils.checkUpgrade(MainActivity.this, Constant.HOST + Constant.Url.INDEX_VERSION);
        init();
    }

    @Override
    protected void initSP() {
        SophixManager.getInstance().queryAndLoadNewPatch();
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void findID() {
        mTabHost = findViewById(R.id.tabHost);
    }

    @Override
    protected void initViews() {
        tabsItem[0] = "首页";
        tabsItem[1] = "买车";
        tabsItem[2] = "卖车";
        tabsItem[3] = "消息";
        tabsItem[4] = "我的";
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtab);
        for (int i = 0; i < tabsItem.length; i++) {
            View inflate = getLayoutInflater().inflate(R.layout.tabs_item, null);
            TextView tabsText =  inflate.findViewById(R.id.tabs_text);
            ImageView tabsImg =  inflate.findViewById(R.id.tabs_img);
            if (i==1||i==2){
                tabsImg.setPadding(0,(int) DpUtils.convertDpToPixel(1f,this),0,(int) DpUtils.convertDpToPixel(1f,this));
            }
            tabsText.setText(tabsItem[i]);
            tabsImg.setImageResource(imgRes[i]);
            mTabHost.addTab(mTabHost.newTabSpec(tabsItem[i]).setIndicator(inflate), fragment[i], null);
        }
//        mTabHost.setCurrentTab(0);
    }

    @Override
    protected void setListeners() {
        MyApplication.setOnPatchLister(new OnPatchLister() {
            @Override
            public void patchSuccess() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("提示")
                                .setMessage("发现新的补丁，是否立即生效,需重启应用")
                                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent mStartActivity = new Intent(MainActivity.this, MainActivity.class);
                                        int mPendingIntentId = 123456;
                                        PendingIntent mPendingIntent = PendingIntent.getActivity(MainActivity.this, mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
                                        AlarmManager mgr = (AlarmManager)MainActivity.this.getSystemService(Context.ALARM_SERVICE);
                                        if (mgr != null) {
                                            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                                        }
                                        SophixManager.getInstance().killProcessSafely();
                                    }
                                })
                                .setNegativeButton("否",null)
                                .create()
                                .show();
                    }
                });
            }
        });
    }

    @Override
    protected void initData() {

    }

}
