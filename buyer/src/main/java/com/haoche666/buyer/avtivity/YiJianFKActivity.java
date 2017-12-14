package com.haoche666.buyer.avtivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.haoche666.buyer.R;
import com.haoche666.buyer.base.MyDialog;
import com.haoche666.buyer.base.ZjbBaseActivity;
import com.haoche666.buyer.constant.Constant;
import com.haoche666.buyer.model.OkObject;
import com.haoche666.buyer.model.SimpleInfo;
import com.haoche666.buyer.util.ApiClient;

import java.util.HashMap;

import huisedebi.zjb.mylibrary.util.GsonUtils;
import huisedebi.zjb.mylibrary.util.LogUtil;
import huisedebi.zjb.mylibrary.util.StringUtil;


public class YiJianFKActivity extends ZjbBaseActivity implements View.OnClickListener {

    private EditText editEmail;
    private EditText editContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yi_jian_fk);
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
        editEmail = (EditText) findViewById(R.id.editEmail);
        editContent = (EditText) findViewById(R.id.editContent);
    }

    @Override
    protected void initViews() {
        ((TextView) findViewById(R.id.textViewTitle)).setText("意见反馈");
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
        findViewById(R.id.btnTiJiao).setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnTiJiao:
                if (!StringUtil.checkEmail(editEmail.getText().toString().trim())) {
                    Toast.makeText(YiJianFKActivity.this, "请输入正确的邮箱", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(editContent.getText().toString().trim())) {
                    Toast.makeText(YiJianFKActivity.this, "反馈内容不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                faKui();
                break;
            case R.id.imageBack:
                finish();
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
        String url = Constant.HOST + Constant.Url.USER_FEEDBACK;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime",tokenTime);
        }
        params.put("email",editEmail.getText().toString().trim());
        params.put("content",editContent.getText().toString().trim());
        return new OkObject(params, url);
    }

    /**
     * 反馈提交
     */
    private void faKui() {
        showLoadingDialog();
        ApiClient.post(YiJianFKActivity.this, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("YiJianFKActivity--意见反馈", s + "");
                try {
                    SimpleInfo simpleInfo = GsonUtils.parseJSON(s, SimpleInfo.class);
                    if (simpleInfo.getStatus() == 1) {
                        MyDialog.dialogFinish(YiJianFKActivity.this,simpleInfo.getInfo());
                    } else if (simpleInfo.getStatus() == 3) {
                        MyDialog.showReLoginDialog(YiJianFKActivity.this);
                    } else {
                        Toast.makeText(YiJianFKActivity.this, simpleInfo.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(YiJianFKActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(YiJianFKActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
