package hudongchuangxiang.com.seller.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hudongchuangxiang.com.seller.R;
import hudongchuangxiang.com.seller.base.ZjbBaseActivity;
import hudongchuangxiang.com.seller.fragment.ChongZhiGZFragment;
import hudongchuangxiang.com.seller.fragment.XiaoFeiMXFragment;

public class WoDeQianBaoActivity extends ZjbBaseActivity implements View.OnClickListener {
    private TabLayout tablayout;
    List<String> list = new ArrayList<>();
    private ViewPager viewPager;
    private TextView textViewRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wo_de_qian_bao);
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
        textViewRight = (TextView) findViewById(R.id.textViewRight);
    }

    @Override
    protected void initViews() {
        ((TextView) findViewById(R.id.textViewTitle)).setText("充值");
        textViewRight.setText("充值");
        textViewRight.setTextColor(ContextCompat.getColor(this,R.color.textGold));
        list.clear();
        list.add("消费明细");
        list.add("充值规则");
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        tablayout.setupWithViewPager(viewPager);
        tablayout.removeAllTabs();
        for (int i = 0; i < list.size(); i++) {
            View view = LayoutInflater.from(WoDeQianBaoActivity.this).inflate(R.layout.item_tablayout, null);
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
