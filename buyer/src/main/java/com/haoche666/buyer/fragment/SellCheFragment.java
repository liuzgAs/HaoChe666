package com.haoche666.buyer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.haoche666.buyer.R;
import com.haoche666.buyer.avtivity.WebActivity;
import com.haoche666.buyer.base.ZjbBaseFragment;
import com.haoche666.buyer.constant.Constant;

import huisedebi.zjb.mylibrary.util.ScreenUtils;

public class SellCheFragment extends ZjbBaseFragment implements View.OnClickListener {

    private View mInflate;
    private View viewBar;
    private ImageView imageBack;
    private TextView textViewRight;

    public SellCheFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mInflate == null) {
            mInflate = inflater.inflate(R.layout.fragment_sell_che, container, false);
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
        viewBar = mInflate.findViewById(R.id.viewBar);
        imageBack = mInflate.findViewById(R.id.imageBack);
        textViewRight = mInflate.findViewById(R.id.textViewRight);
    }

    @Override
    protected void initViews() {
        ViewGroup.LayoutParams layoutParams = viewBar.getLayoutParams();
        layoutParams.height = (int) (getResources().getDimension(R.dimen.titleHeight) + ScreenUtils.getStatusBarHeight(getActivity()));
        viewBar.setLayoutParams(layoutParams);
        viewBar.setPadding(0, ScreenUtils.getStatusBarHeight(getActivity()), 0, 0);
        imageBack.setVisibility(View.GONE);
        ((TextView) mInflate.findViewById(R.id.textViewTitle)).setText("我要卖车");
        textViewRight.setText("卖车流程");
    }

    @Override
    protected void setListeners() {
        textViewRight.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.textViewRight:
                intent.setClass(getActivity(), WebActivity.class);
                intent.putExtra(Constant.IntentKey.TITLE, "卖车流程");
                intent.putExtra(Constant.IntentKey.URL, Constant.Url.MAI_CHE_GUI_ZE);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
