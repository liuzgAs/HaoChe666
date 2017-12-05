package hudongchuangxiang.haoche666.seller.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hudongchuangxiang.haoche666.seller.R;
import hudongchuangxiang.haoche666.seller.base.ZjbBaseFragment;
import huisedebi.zjb.mylibrary.util.ScreenUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class CheLiangGLFragment extends ZjbBaseFragment {
    private TabLayout tablayout;
    private ViewPager viewPager;
    List<String> list = new ArrayList<>();

    private View mInflate;
    private View viewBar;
    private ImageView imageBack;

    public CheLiangGLFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mInflate == null) {
            mInflate = inflater.inflate(R.layout.fragment_che_liang_gl, container, false);
            init();
        }
        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) mInflate.getParent();
        if (parent != null) {
            parent.removeView(mInflate);
        }
        return mInflate;
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void findID() {
        tablayout = mInflate.findViewById(R.id.tablayout);
        viewPager = mInflate.findViewById(R.id.viewPager);
        imageBack = mInflate.findViewById(R.id.imageBack);
        viewBar = mInflate.findViewById(R.id.viewBar);
    }

    @Override
    protected void initViews() {
        imageBack.setVisibility(View.GONE);
        viewBar.setPadding(0, ScreenUtils.getStatusBarHeight(getActivity()), 0, 0);
        ((TextView) mInflate.findViewById(R.id.textViewTitle)).setText("车辆管理");
        list.add("在售");
        list.add("待发布");
        list.add("预约");
        list.add("已售");
        viewPager.setAdapter(new MyPageAdapter(getChildFragmentManager()));
        tablayout.setupWithViewPager(viewPager);
        tablayout.removeAllTabs();
        for (int i = 0; i < list.size(); i++) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_tablayout, null);
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

    }

    @Override
    protected void initData() {

    }

    class MyPageAdapter extends FragmentPagerAdapter {

        private MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new CheLiangGuangLiFragment(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }
}
