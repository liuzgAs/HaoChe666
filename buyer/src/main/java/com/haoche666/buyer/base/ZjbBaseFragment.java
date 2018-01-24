package com.haoche666.buyer.base;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;

import com.haoche666.buyer.R;
import com.haoche666.buyer.constant.Constant;
import com.haoche666.buyer.model.UserInfo;

import huisedebi.zjb.mylibrary.interfacepage.FragmentBackHandler;
import huisedebi.zjb.mylibrary.util.ACache;
import huisedebi.zjb.mylibrary.util.BackHandlerHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public abstract class ZjbBaseFragment extends Fragment implements FragmentBackHandler {
    public boolean isLogin = false;
    public int changeControl = 2016;
    private AlertDialog mAlertDialog;
    public UserInfo userInfo;
    public String tokenTime;
    public Activity mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //禁止横屏
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.mContext = getActivity();
    }

    public void init() {
        changeControl = Constant.changeControl - 1;
        initSP();
        initIntent();
        findID();
        initViews();
        setListeners();
    }

    protected abstract void initIntent();

    protected abstract void initSP();

    protected abstract void findID();

    protected abstract void initViews();

    protected abstract void setListeners();

    protected abstract void initData();

    private void initLogin() {
        ACache aCache = ACache.get(getActivity(), Constant.Acache.APP);
        userInfo = (UserInfo) aCache.getAsObject(Constant.Acache.USER_INFO);
        tokenTime = aCache.getAsString(Constant.Acache.TOKENTIME);
        if (userInfo != null) {
            isLogin = true;
        } else {
            isLogin = false;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (changeControl != Constant.changeControl) {
            initLogin();
            initData();
            changeControl++;
        }
    }

    public void showLoadingDialog() {
        if (mAlertDialog == null) {
            View dialog_progress = LayoutInflater.from(mContext).inflate(R.layout.view_progress01, null);
            mAlertDialog = new AlertDialog.Builder(mContext, R.style.dialog)
                    .setView(dialog_progress)
                    .setCancelable(false)
                    .create();
            mAlertDialog.show();
            mAlertDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                        cancelLoadingDialog();
                        mContext.finish();
                    }
                    return false;
                }
            });
        } else {
            mAlertDialog.show();
        }
    }

    public void cancelLoadingDialog() {
        if (mAlertDialog != null && mAlertDialog.isShowing()) {
            mAlertDialog.dismiss();
            mAlertDialog = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    @Override
    public boolean onBackPressed() {
        return BackHandlerHelper.handleBackPress(this);
    }

}
