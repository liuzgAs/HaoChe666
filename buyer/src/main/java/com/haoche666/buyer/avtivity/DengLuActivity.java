package com.haoche666.buyer.avtivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.haoche666.buyer.R;
import com.haoche666.buyer.base.ZjbBaseActivity;

/**
 * 登录
 * @author Administrator
 */
public class DengLuActivity extends ZjbBaseActivity implements View.OnClickListener {
    private EditText[] editView = new EditText[3];
    private View[] lineView = new View[3];
    private ImageView imageAgreement;
    private boolean isAgreement = true;
    private TextView textLoginType;
    private boolean isMsgLogin = true;
    private View viewPsw;
    private View viewMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deng_lu2);
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
        editView[0] = (EditText) findViewById(R.id.edit01);
        editView[1] = (EditText) findViewById(R.id.edit02);
        editView[2] = (EditText) findViewById(R.id.edit03);
        lineView[0] = findViewById(R.id.line01);
        lineView[1] = findViewById(R.id.line02);
        lineView[2] = findViewById(R.id.line03);
        imageAgreement = (ImageView) findViewById(R.id.imageAgreement);
        textLoginType = (TextView) findViewById(R.id.textLoginType);
        viewPsw = findViewById(R.id.viewPsw);
        viewMsg = findViewById(R.id.viewMsg);
    }

    @Override
    protected void initViews() {
        setAgreement();
        setLoginType();
    }

    /**
     * des： 登录方式
     * author： ZhangJieBo
     * date： 2017/11/14 0014 上午 10:19
     */
    private void setLoginType() {
        if (isMsgLogin){
            textLoginType.setText("切换密码登录");
            viewPsw.setVisibility(View.GONE);
            viewMsg.setVisibility(View.VISIBLE);
            lineView[1].setVisibility(View.GONE);
            lineView[2].setVisibility(View.VISIBLE);
        }else {
            textLoginType.setText("切换验证码登录");
            viewPsw.setVisibility(View.VISIBLE);
            viewMsg.setVisibility(View.GONE);
            lineView[1].setVisibility(View.VISIBLE);
            lineView[2].setVisibility(View.GONE);
        }
    }

    /**
     * des： 控制协议选中
     * author： ZhangJieBo
     * date： 2017/11/14 0014 上午 10:17
     */
    private void setAgreement() {
        if (isAgreement){
            imageAgreement.setImageResource(R.mipmap.xuanzhong);
        }else {
            imageAgreement.setImageResource(R.mipmap.weixuanzhong);
        }
    }

    @Override
    protected void setListeners() {
        imageAgreement.setOnClickListener(this);
        textLoginType.setOnClickListener(this);
        findViewById(R.id.buttonLogin).setOnClickListener(this);
        findViewById(R.id.imageBack).setOnClickListener(this);
        findViewById(R.id.buttonZhuCe).setOnClickListener(this);
        findViewById(R.id.textWangJiMM).setOnClickListener(this);
        for (int i = 0; i < editView.length; i++) {
            final int finalI = i;
            editView[i].setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        for (View aLineView : lineView) {
                            aLineView.setBackgroundColor(ContextCompat.getColor(DengLuActivity.this,R.color.text_gray));
                        }
                        lineView[finalI].setBackgroundColor(ContextCompat.getColor(DengLuActivity.this,R.color.basic_color));
                    }
                }
            });
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.textLoginType:
                isMsgLogin = !isMsgLogin;
                setLoginType();
                break;
            case R.id.imageAgreement:
                isAgreement = !isAgreement;
                setAgreement();
                break;
            case R.id.textWangJiMM:
                intent.setClass(this,WangJiMMActivity.class);
                startActivity(intent);
                break;
            case R.id.imageBack:
                finish();
                break;
            case R.id.buttonLogin:
                if (!isAgreement){
                    Toast.makeText(DengLuActivity.this, "请阅读并同意《好车666用户协议》", Toast.LENGTH_SHORT).show();
                    return;
                }
                intent.setClass(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.buttonZhuCe:
                intent.setClass(this, ZhuCeActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
