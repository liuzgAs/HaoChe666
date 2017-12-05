package hudongchuangxiang.haoche666.seller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import hudongchuangxiang.haoche666.seller.R;
import hudongchuangxiang.haoche666.seller.base.ZjbBaseActivity;
import hudongchuangxiang.haoche666.seller.constant.Constant;
import huisedebi.zjb.mylibrary.util.StringUtil;


/**
 * 忘记密码
 * @author Administrator
 */
public class WangJiMMActivity extends ZjbBaseActivity implements View.OnClickListener {

    private TextView textSms;
    private Runnable mR;
    private int[] mI;
    private String mPhone_sms;
    private EditText editPhone;
    private EditText editSms;
    private EditText editPsw01;
    private EditText editPsw02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wang_ji_mm);
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
        textSms = (TextView) findViewById(R.id.textSms);
        editPhone = (EditText) findViewById(R.id.editPhone);
        editSms = (EditText) findViewById(R.id.editSms);
        editPsw01 = (EditText) findViewById(R.id.editPsw01);
        editPsw02 = (EditText) findViewById(R.id.editPsw02);
    }

    @Override
    protected void initViews() {
        ((TextView)findViewById(R.id.textViewTitle)).setText("忘记密码");
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
        findViewById(R.id.buttonTiJiao).setOnClickListener(this);
        textSms.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonTiJiao:
                if (TextUtils.isEmpty(mPhone_sms)) {
                    Toast.makeText(WangJiMMActivity.this, "请先发送验证码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(editSms.getText().toString().trim())) {
                    Toast.makeText(WangJiMMActivity.this, "验证码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(editPsw01.getText().toString().trim()) || TextUtils.isEmpty(editPsw02.getText().toString().trim())) {
                    Toast.makeText(WangJiMMActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!TextUtils.equals(editPsw01.getText().toString().trim(), editPsw02.getText().toString().trim())) {
                    Toast.makeText(WangJiMMActivity.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!StringUtil.isPassword(editPsw01.getText().toString().trim(),6)) {
                    Toast.makeText(WangJiMMActivity.this, "密码太简单", Toast.LENGTH_SHORT).show();
                    return;
                }
                forgetPSW();
                break;
            case R.id.textSms:
                sendSMS();
                break;
            case R.id.imageBack:
                finish();
                break;
            default:
                break;
        }
    }

    /**
     * 忘记密码请求
     */
    private void forgetPSW() {
        Intent intent = new Intent();
        intent.putExtra(Constant.IntentKey.VALUE,mPhone_sms);
        intent.setClass(this,DengLuActivity.class);
        startActivity(intent);
    }

    /**
     * des： 短信发送按钮状态
     * author： ZhangJieBo
     * date： 2017/8/22 0022 上午 10:26
     */
    private void sendSMS() {
        textSms.removeCallbacks(mR);
        boolean mobileNO = StringUtil.isMobileNO(editPhone.getText().toString().trim());
        if (mobileNO) {
            mPhone_sms = editPhone.getText().toString().trim();
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
            Toast.makeText(WangJiMMActivity.this, "输入正确的手机号", Toast.LENGTH_SHORT).show();
            editPhone.setText("");
        }
    }

    /**
     * 获取短信请求
     */
    private void getSms() {

    }
}
