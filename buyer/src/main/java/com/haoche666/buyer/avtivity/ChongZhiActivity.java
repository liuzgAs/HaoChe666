package com.haoche666.buyer.avtivity;

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
import com.haoche666.buyer.fragment.ChongZhiGZFragment;
import com.haoche666.buyer.fragment.XiaoFeiMXFragment;

import java.util.ArrayList;
import java.util.List;

public class ChongZhiActivity extends ZjbBaseActivity implements View.OnClickListener {
    private TabLayout tablayout;
    List<String> list = new ArrayList<>();
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chong_zhi);
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
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
    }

    @Override
    protected void initViews() {
        ((TextView) findViewById(R.id.textViewTitle)).setText("充值");
        list.clear();
        list.add("消费明细");
        list.add("充值规则");
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        tablayout.setupWithViewPager(viewPager);
        tablayout.removeAllTabs();
        for (int i = 0; i < list.size(); i++) {
            View view = LayoutInflater.from(ChongZhiActivity.this).inflate(R.layout.item_tablayout, null);
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
     * viewpager  Adapter
     */
    class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new XiaoFeiMXFragment();
                case 1:
                    return new ChongZhiGZFragment();
                default:
                    return new ChongZhiGZFragment();
            }
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }
}
