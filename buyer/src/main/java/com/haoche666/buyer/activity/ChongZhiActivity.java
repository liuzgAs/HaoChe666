package com.haoche666.buyer.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.haoche666.buyer.R;
import com.haoche666.buyer.base.ZjbBaseActivity;
import com.haoche666.buyer.constant.Constant;
import com.haoche666.buyer.fragment.ChongZhiGZFragment;
import com.haoche666.buyer.fragment.XiaoFeiMXFragment;
import com.haoche666.buyer.util.MoneyInputFilter;

import java.util.ArrayList;
import java.util.List;

public class ChongZhiActivity extends ZjbBaseActivity implements View.OnClickListener {
    private TabLayout tablayout;
    List<String> list = new ArrayList<>();
    private ViewPager viewPager;
    private EditText editMoney;
    private BroadcastReceiver reciver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action) {
                case Constant.BroadcastCode.CHONG_ZHI:
                    editMoney.setText("");
                    break;
                default:
                    break;
            }
        }
    };

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
        editMoney = (EditText) findViewById(R.id.editMoney);
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
        MoneyInputFilter.init(editMoney);
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
        findViewById(R.id.btnNext).setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnNext:
                if (TextUtils.equals(editMoney.getText().toString().trim(), "")) {
                    Toast.makeText(ChongZhiActivity.this, "请输入金额", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.equals(editMoney.getText().toString().trim(), "0.")) {
                    Toast.makeText(ChongZhiActivity.this, "请输入正确的金额", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Double.parseDouble(editMoney.getText().toString().trim()) == 0) {
                    Toast.makeText(ChongZhiActivity.this, "金额必须大于0", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra(Constant.IntentKey.VALUE, editMoney.getText().toString().trim());
                intent.setClass(this, PayChongZhiActivity.class);
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

    @Override
    public void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.BroadcastCode.CHONG_ZHI);
        registerReceiver(reciver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(reciver);
    }
}
