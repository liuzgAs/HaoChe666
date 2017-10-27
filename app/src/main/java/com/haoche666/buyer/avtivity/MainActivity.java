package com.haoche666.buyer.avtivity;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.haoche666.buyer.R;
import com.haoche666.buyer.base.ZjbBaseNotLeftActivity;
import com.haoche666.buyer.constant.Constant;
import com.haoche666.buyer.fragment.MaiCheFragment;
import com.haoche666.buyer.fragment.SellCheFragment;
import com.haoche666.buyer.fragment.ShouYeFragment;
import com.haoche666.buyer.fragment.WoDeFragment;
import com.haoche666.buyer.fragment.XiaoXiFragment;
import com.haoche666.buyer.util.UpgradeUtils;

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

    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void findID() {
        mTabHost = (FragmentTabHost) findViewById(R.id.tabHost);
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
            TextView tabs_text =  inflate.findViewById(R.id.tabs_text);
            ImageView tabs_img =  inflate.findViewById(R.id.tabs_img);
            tabs_text.setText(tabsItem[i]);
            tabs_img.setImageResource(imgRes[i]);
            mTabHost.addTab(mTabHost.newTabSpec(tabsItem[i]).setIndicator(inflate), fragment[i], null);
        }
//        mTabHost.setCurrentTab(0);
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void initData() {

    }

}
