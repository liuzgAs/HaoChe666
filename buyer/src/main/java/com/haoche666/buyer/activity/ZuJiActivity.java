package com.haoche666.buyer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.haoche666.buyer.R;
import com.haoche666.buyer.base.ZjbBaseActivity;
import com.haoche666.buyer.constant.Constant;
import com.haoche666.buyer.fragment.ZuJiCheLiangFragment;
import com.haoche666.buyer.fragment.ZuJiNeiRongFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 足迹
 *
 * @author Administrator
 */
public class ZuJiActivity extends ZjbBaseActivity implements View.OnClickListener {

    private TabLayout tablayout;
    private ViewPager viewPager;
    List<String> list = new ArrayList<>();
    private boolean isFromDuiBi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zu_ji);
        init();
    }

    @Override
    protected void initIntent() {
        Intent intent = getIntent();
        isFromDuiBi = intent.getBooleanExtra(Constant.IntentKey.IS_FROM_DUI_BI, false);
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void findID() {
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
    }

    @Override
    protected void initViews() {
        ((TextView) findViewById(R.id.textViewTitle)).setText("足迹");
        list.add("车辆");
        list.add("内容");
        viewPager.setAdapter(new MyPageAdapter(getSupportFragmentManager()));
        tablayout.setupWithViewPager(viewPager);
        tablayout.removeAllTabs();
        /*下划线监听*/
        for (int i = 0; i < list.size(); i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.item_tablayout, null);
            TextView textTitle = view.findViewById(R.id.textTitle);
            textTitle.setText(list.get(i));
            if (i == 0) {
                tablayout.addTab(tablayout.newTab().setCustomView(view), true);
            } else {
                tablayout.addTab(tablayout.newTab().setCustomView(view), false);
            }
        }
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
    }

    @Override
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

    /**
     * fragmentadapter
     */
    class MyPageAdapter extends FragmentPagerAdapter {

        MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new ZuJiCheLiangFragment(isFromDuiBi);
                case 1:
                    return new ZuJiNeiRongFragment();
                default:
                    return new ZuJiCheLiangFragment();
            }
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }
}
