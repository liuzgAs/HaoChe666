package com.haoche666.buyer.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.haoche666.buyer.fragment.BannerFragment;

import java.util.List;

public class MyPagerAdapter extends MyFragmentAdapter {
    private List<String> mAdvsEntityList;

    public MyPagerAdapter(FragmentManager fm, List<String> mAdvsEntityList) {
        super(fm);
        this.mAdvsEntityList = mAdvsEntityList;
    }

    @Override
    public Fragment getItem(int position) {
        int pager_index = position % mAdvsEntityList.size();
        String advsEntity = mAdvsEntityList.get(pager_index);
        return new BannerFragment();
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE / 2;
    }
}