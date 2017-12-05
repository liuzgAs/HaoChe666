package hudongchuangxiang.haoche666.seller.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hudongchuangxiang.haoche666.seller.R;
import hudongchuangxiang.haoche666.seller.base.ZjbBaseActivity;
import hudongchuangxiang.haoche666.seller.fragment.XiaoXiFragment;

public class XiaoXiZXActivity extends ZjbBaseActivity implements View.OnClickListener {
    private TabLayout tablayout;
    private ViewPager viewPager;
    List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiao_xi_zx);
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
        ((TextView) findViewById(R.id.textViewTitle)).setText("消息中心");
        list.add("全部消息");
        list.add("平台公告");
        list.add("我的消息");
        viewPager.setAdapter(new MyPageAdapter(getSupportFragmentManager()));
        tablayout.setupWithViewPager(viewPager);
        tablayout.removeAllTabs();
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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    class MyPageAdapter extends FragmentPagerAdapter {

        private MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new XiaoXiFragment();
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }
}
