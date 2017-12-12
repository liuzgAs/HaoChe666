package com.haoche666.buyer.avtivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.haoche666.buyer.R;
import com.haoche666.buyer.base.ZjbBaseActivity;
import com.haoche666.buyer.constant.Constant;
import com.haoche666.buyer.model.UserBuyerindex;

import huisedebi.zjb.mylibrary.util.ACache;
import huisedebi.zjb.mylibrary.util.StringUtil;

public class GeRenXXActivity extends ZjbBaseActivity implements View.OnClickListener {

    private UserBuyerindex userBuyerindex;
    private ImageView imageHeadimg;
    private TextView textNickname;
    private TextView textGrade_name;
    private TextView textMobile;
    private BroadcastReceiver reciver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action) {
                case Constant.BroadcastCode.USERINFO:
                    String username = intent.getStringExtra(Constant.IntentKey.NICKNAME);
                    if (!TextUtils.isEmpty(username)){
                        textNickname.setText(username);
                    }
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ge_ren_xx);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {
        Intent intent = getIntent();
        userBuyerindex = (UserBuyerindex) intent.getSerializableExtra(Constant.IntentKey.BEAN);
    }

    @Override
    protected void findID() {
        imageHeadimg = (ImageView) findViewById(R.id.imageHeadimg);
        textNickname = (TextView) findViewById(R.id.textNickname);
        textGrade_name = (TextView) findViewById(R.id.textGrade_name);
        textMobile = (TextView) findViewById(R.id.textMobile);
    }

    @Override
    protected void initViews() {
        ((TextView) findViewById(R.id.textViewTitle)).setText("个人信息");
        Glide.with(GeRenXXActivity.this)
                .load(userBuyerindex.getHeadimg())
                .dontAnimate()
                .placeholder(R.mipmap.ic_empty)
                .into(imageHeadimg);
        textNickname.setText(userBuyerindex.getNickname());
        textGrade_name.setText(userBuyerindex.getGrade_name());
        textMobile.setText(StringUtil.hidePhone(userBuyerindex.getMobile()));
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
        findViewById(R.id.btnExit).setOnClickListener(this);
        findViewById(R.id.viewNickName).setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.viewNickName:
                Intent intent = new Intent();
                intent.putExtra(Constant.IntentKey.VALUE, userBuyerindex.getNickname());
                intent.setClass(this, EditActivity.class);
                startActivity(intent);
                break;
            case R.id.btnExit:
                ACache aCache = ACache.get(this, Constant.Acache.APP);
                aCache.clear();
                Constant.changeControl++;
                finish();
                break;
            case R.id.imageBack:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.BroadcastCode.USERINFO);
        registerReceiver(reciver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(reciver);
    }
}
