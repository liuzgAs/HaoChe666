package com.haoche666.buyer.avtivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.haoche666.buyer.R;
import com.haoche666.buyer.base.MyDialog;
import com.haoche666.buyer.base.ZjbBaseActivity;
import com.haoche666.buyer.constant.Constant;
import com.haoche666.buyer.model.OkObject;
import com.haoche666.buyer.model.SimpleInfo;
import com.haoche666.buyer.model.UserInfo;
import com.haoche666.buyer.util.ApiClient;

import java.util.HashMap;

import huisedebi.zjb.mylibrary.util.ACache;
import huisedebi.zjb.mylibrary.util.GsonUtils;
import huisedebi.zjb.mylibrary.util.LogUtil;
import huisedebi.zjb.mylibrary.util.StringUtil;

/**
 * 登录
 *
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
    private TextView textSms;
    private Runnable mR;
    private int[] mI;
    private String mPhone_sms;

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
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        String phone = intent.getStringExtra(Constant.IntentKey.PHONE);
        editView[0].setText(phone);
        editView[1].requestFocus();
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
        textSms = (TextView) findViewById(R.id.textMsg);
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
        if (isMsgLogin) {
            textLoginType.setText("切换密码登录");
            viewPsw.setVisibility(View.GONE);
            viewMsg.setVisibility(View.VISIBLE);
            lineView[1].setVisibility(View.GONE);
            lineView[2].setVisibility(View.VISIBLE);
        } else {
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
        if (isAgreement) {
            imageAgreement.setImageResource(R.mipmap.xuanzhong);
        } else {
            imageAgreement.setImageResource(R.mipmap.weixuanzhong);
        }
    }

    @Override
    protected void setListeners() {
        textSms.setOnClickListener(this);
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
                            aLineView.setBackgroundColor(ContextCompat.getColor(DengLuActivity.this, R.color.text_gray));
                        }
                        lineView[finalI].setBackgroundColor(ContextCompat.getColor(DengLuActivity.this, R.color.basic_color));
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
            case R.id.textMsg:
                sendSMS();
                break;
            case R.id.textLoginType:
                isMsgLogin = !isMsgLogin;
                setLoginType();
                break;
            case R.id.imageAgreement:
                isAgreement = !isAgreement;
                setAgreement();
                break;
            case R.id.textWangJiMM:
                intent.setClass(this, WangJiMMActivity.class);
                startActivity(intent);
                break;
            case R.id.imageBack:
                finish();
                break;
            case R.id.buttonLogin:

                if (!StringUtil.isMobileNO(editView[0].getText().toString().trim())) {
                    Toast.makeText(DengLuActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (isMsgLogin){
                    if (TextUtils.isEmpty(editView[1].getText().toString().trim())) {
                        Toast.makeText(DengLuActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }else {
                    if (TextUtils.isEmpty(editView[2].getText().toString().trim())) {
                        Toast.makeText(DengLuActivity.this, "请输入验证码", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                if (!isAgreement) {
                    Toast.makeText(DengLuActivity.this, "请阅读并同意《好车666用户协议》", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(isMsgLogin){
                    loginSms();
                }else {
                    loginPsw();
                }
                break;
            case R.id.buttonZhuCe:
                intent.setClass(this, ZhuCeActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.LOGIN_FORGETSMS;
        HashMap<String, String> params = new HashMap<>();
        params.put("userName", mPhone_sms);
        return new OkObject(params, url);
    }

    /**
     * des： 获取短信
     * author： ZhangJieBo
     * date： 2017/7/6 0006 下午 2:45
     */
    private void getSms() {
        showLoadingDialog();
        ApiClient.post(this, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("ZhuCeActivity--获取短信", "" + s);
                try {
                    SimpleInfo simpleInfo = GsonUtils.parseJSON(s, SimpleInfo.class);
                    Toast.makeText(DengLuActivity.this, simpleInfo.getInfo(), Toast.LENGTH_SHORT).show();
                    if (simpleInfo.getStatus() == 1) {

                    }
                } catch (Exception e) {
                    Toast.makeText(DengLuActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(DengLuActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * des： 短信发送按钮状态
     * author： ZhangJieBo
     * date： 2017/8/22 0022 上午 10:26
     */
    private void sendSMS() {
        textSms.removeCallbacks(mR);
        boolean mobileNO = StringUtil.isMobileNO(editView[0].getText().toString().trim());
        if (mobileNO) {
            mPhone_sms = editView[0].getText().toString().trim();
            textSms.setEnabled(false);
            mI = new int[]{60};

            mR = new Runnable() {
                @Override
                public void run() {
                    textSms.setText((mI[0]--) + "秒后重发");
                    if (mI[0] == 0) {
                        textSms.setEnabled(true);
                        textSms.setText("重新发送");
                        return;
                    } else {
                    }
                    textSms.postDelayed(mR, 1000);
                }
            };
            textSms.postDelayed(mR, 0);
            getSms();
        } else {
            Toast.makeText(DengLuActivity.this, "输入正确的手机号", Toast.LENGTH_SHORT).show();
            editView[0].setText("");
        }
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObjectPsw() {
        String url = Constant.HOST + Constant.Url.LOGIN_INDEX;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid",userInfo.getUid());
        params.put("tokenTime",tokenTime);
        return new OkObject(params, url);
    }

    /**
     * 密码登录
     */
    private void loginPsw() {
        showLoadingDialog();
        ApiClient.post(DengLuActivity.this, getOkObjectPsw(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("DengLuActivity--onSuccess",s+ "");
                try {
                    SimpleInfo simpleInfo = GsonUtils.parseJSON(s, SimpleInfo.class);
                    if (simpleInfo.getStatus()==1){
                    }else if (simpleInfo.getStatus()==3){
                        MyDialog.showReLoginDialog(DengLuActivity.this);
                    }else {
                        Toast.makeText(DengLuActivity.this, simpleInfo.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(DengLuActivity.this,"数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(DengLuActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObjectSms(String tokenTime) {
        String url = Constant.HOST + Constant.Url.LOGIN_SMS;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid",userInfo.getUid());
        params.put("tokenTime",tokenTime);
        return new OkObject(params, url);
    }

    /**
     * 短信登录
     */
    private void loginSms() {
        showLoadingDialog();
        final String tokenTime = System.currentTimeMillis() + "";
        ApiClient.post(this, getOkObjectSms(tokenTime), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("DengLuActivity--登录返回", "" + s);
                cancelLoadingDialog();
                try {
                    loginSuccess(s, tokenTime);
                } catch (Exception e) {
                    Toast.makeText(DengLuActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(DengLuActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * des： 登录成功处理
     * author： ZhangJieBo
     * date： 2017/9/8 0008 下午 5:27
     */
    private void loginSuccess(String s, String tokenTime) {
        final UserInfo userInfo = GsonUtils.parseJSON(s, UserInfo.class);
        if (userInfo.getStatus() == 1) {
            ACache aCache = ACache.get(DengLuActivity.this, Constant.Acache.APP);
            aCache.put(Constant.Acache.USER_INFO, userInfo);
            aCache.put(Constant.Acache.TOKENTIME, tokenTime);
            Constant.changeControl++;
            finish();
        }
        Toast.makeText(DengLuActivity.this, userInfo.getInfo(), Toast.LENGTH_SHORT).show();
    }
}
