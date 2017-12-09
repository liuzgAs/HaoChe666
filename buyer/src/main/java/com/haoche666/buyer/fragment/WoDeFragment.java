package com.haoche666.buyer.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.haoche666.buyer.R;
import com.haoche666.buyer.avtivity.ChaXunFWActivity;
import com.haoche666.buyer.avtivity.CheLiangDBActivity;
import com.haoche666.buyer.avtivity.ChongZhiActivity;
import com.haoche666.buyer.avtivity.DingDanGLActivity;
import com.haoche666.buyer.avtivity.GeRenXXActivity;
import com.haoche666.buyer.avtivity.PayVipActivity;
import com.haoche666.buyer.avtivity.SheZhiActivity;
import com.haoche666.buyer.avtivity.WoDeGZActivity;
import com.haoche666.buyer.avtivity.WoMaiDeCheActivity;
import com.haoche666.buyer.base.ToLoginActivity;
import com.haoche666.buyer.base.ZjbBaseFragment;
import com.haoche666.buyer.customview.HeaderWaveHelper;
import com.haoche666.buyer.customview.HeaderWaveView;

import huisedebi.zjb.mylibrary.util.ScreenUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class WoDeFragment extends ZjbBaseFragment implements View.OnClickListener {


    private View mInflate;
    private HeaderWaveView waveView;
    private HeaderWaveHelper mHeaderWaveHelper;
    private View viewBar;
    private View viewBar01;
    private ImageView imageVip;
    private TextView textName;
    private ImageView imageHead;

    public WoDeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mInflate == null) {
            mInflate = inflater.inflate(R.layout.fragment_wo_de, container, false);
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
        waveView = mInflate.findViewById(R.id.header_wave_view);
        viewBar = mInflate.findViewById(R.id.viewBar);
        viewBar01 = mInflate.findViewById(R.id.viewBar01);
        imageVip = mInflate.findViewById(R.id.imageVip);
        textName = mInflate.findViewById(R.id.textName);
        imageHead = mInflate.findViewById(R.id.imageHead);
    }

    @Override
    protected void initViews() {
        mHeaderWaveHelper = new HeaderWaveHelper(waveView, ContextCompat.getColor(getActivity(), R.color.waveBgLigth), ContextCompat.getColor(getActivity(), R.color.waveBg));
        ViewGroup.LayoutParams layoutParams = viewBar.getLayoutParams();
        layoutParams.height = (int) (getResources().getDimension(R.dimen.titleHeight) + ScreenUtils.getStatusBarHeight(getActivity()));
        viewBar.setLayoutParams(layoutParams);
        viewBar.setPadding(0, ScreenUtils.getStatusBarHeight(getActivity()), 0, 0);
        ViewGroup.LayoutParams layoutParams01 = viewBar01.getLayoutParams();
        layoutParams01.height = (int) (getResources().getDimension(R.dimen.titleHeight) + ScreenUtils.getStatusBarHeight(getActivity()));
        viewBar01.setLayoutParams(layoutParams01);
        viewBar01.setPadding(0, ScreenUtils.getStatusBarHeight(getActivity()), 0, 0);
    }

    @Override
    protected void setListeners() {
        mInflate.findViewById(R.id.viewWoDeGZ).setOnClickListener(this);
        mInflate.findViewById(R.id.viewDuiBi).setOnClickListener(this);
        mInflate.findViewById(R.id.viewWoDeDD).setOnClickListener(this);
        mInflate.findViewById(R.id.viewChaXunFW).setOnClickListener(this);
        mInflate.findViewById(R.id.imageSheZhi).setOnClickListener(this);
        mInflate.findViewById(R.id.textChongZhi).setOnClickListener(this);
        mInflate.findViewById(R.id.viewGeRenXX).setOnClickListener(this);
        mInflate.findViewById(R.id.viewWoMaiDeChe).setOnClickListener(this);
        imageVip.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        if (isLogin){
            textName.setText(userInfo.getUserName());
            Glide.with(getActivity())
                    .load(userInfo.getHeadImg())
                    .asBitmap()
                    .dontAnimate()
                    .placeholder(R.mipmap.ic_empty)
                    .into(imageHead);
        }else {
            textName.setText("未登录");
            Glide.with(getActivity())
                    .load(R.mipmap.mine_head)
                    .asBitmap()
                    .dontAnimate()
                    .placeholder(R.mipmap.ic_empty)
                    .into(imageHead);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mHeaderWaveHelper.cancel();
    }

    @Override
    public void onResume() {
        super.onResume();
        mHeaderWaveHelper.start();
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.viewWoMaiDeChe:
                intent.setClass(getActivity(), WoMaiDeCheActivity.class);
                startActivity(intent);
                break;
            case R.id.viewGeRenXX:
                if (isLogin){
                    intent.setClass(getActivity(), GeRenXXActivity.class);
                    startActivity(intent);
                }else {
                    ToLoginActivity.toLoginActivity(getActivity());
                }
                break;
            case R.id.textChongZhi:
                intent.setClass(getActivity(), ChongZhiActivity.class);
                startActivity(intent);
                break;
            case R.id.imageVip:
                intent.setClass(getActivity(), PayVipActivity.class);
                startActivity(intent);
                break;
            case R.id.imageSheZhi:
                intent.setClass(getActivity(), SheZhiActivity.class);
                startActivity(intent);
                break;
            case R.id.viewChaXunFW:
                intent.setClass(getActivity(), ChaXunFWActivity.class);
                startActivity(intent);
                break;
            case R.id.viewWoDeDD:
                intent.setClass(getActivity(), DingDanGLActivity.class);
                startActivity(intent);
                break;
            case R.id.viewDuiBi:
                intent.setClass(getActivity(), CheLiangDBActivity.class);
                startActivity(intent);
                break;
            case R.id.viewWoDeGZ:
                intent.setClass(getActivity(), WoDeGZActivity.class);
                startActivity(intent);
                break;
            default:

                break;
        }
    }
}
