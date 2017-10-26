package com.haoche666.buyer.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haoche666.buyer.R;
import com.haoche666.buyer.avtivity.ChaXunFWActivity;
import com.haoche666.buyer.avtivity.CheLiangDBActivity;
import com.haoche666.buyer.avtivity.WoDeDDActivity;
import com.haoche666.buyer.avtivity.WoDeGZActivity;
import com.haoche666.buyer.base.ZjbBaseFragment;
import com.haoche666.buyer.customview.HeaderWaveHelper;
import com.haoche666.buyer.customview.HeaderWaveView;

/**
 * A simple {@link Fragment} subclass.
 */
public class WoDeFragment extends ZjbBaseFragment implements View.OnClickListener {


    private View mInflate;
    private HeaderWaveView waveView;
    private HeaderWaveHelper mHeaderWaveHelper;

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
    }

    @Override
    protected void initViews() {
        mHeaderWaveHelper = new HeaderWaveHelper(waveView, getResources().getColor(R.color.waveBgLigth), getResources().getColor(R.color.waveBg));
    }

    @Override
    protected void setListeners() {
        mInflate.findViewById(R.id.viewWoDeGZ).setOnClickListener(this);
        mInflate.findViewById(R.id.viewDuiBi).setOnClickListener(this);
        mInflate.findViewById(R.id.viewWoDeDD).setOnClickListener(this);
        mInflate.findViewById(R.id.viewChaXunFW).setOnClickListener(this);
    }

    @Override
    protected void initData() {

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
            case R.id.viewChaXunFW:
                intent.setClass(getActivity(), ChaXunFWActivity.class);
                startActivity(intent);
                break;
            case R.id.viewWoDeDD:
                intent.setClass(getActivity(), WoDeDDActivity.class);
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
