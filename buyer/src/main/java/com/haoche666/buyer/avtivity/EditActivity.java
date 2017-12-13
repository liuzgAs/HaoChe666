package com.haoche666.buyer.avtivity;

import android.content.Intent;
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

public class EditActivity extends ZjbBaseActivity implements View.OnClickListener {

    private String value;
    private EditText editNickName;
    private TextView textViewRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {
        Intent intent = getIntent();
        value = intent.getStringExtra(Constant.IntentKey.VALUE);
    }

    @Override
    protected void findID() {
        editNickName = (EditText) findViewById(R.id.editNickName);
        textViewRight = (TextView) findViewById(R.id.textViewRight);
    }

    @Override
    protected void initViews() {
        ((TextView) findViewById(R.id.textViewTitle)).setText("修改昵称");
        textViewRight.setText("完成");
        editNickName.setText(value);
        editNickName.setSelection(value.length());
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
        textViewRight.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textViewRight:
                if (TextUtils.isEmpty(editNickName.getText().toString().trim())){
                    Toast.makeText(EditActivity.this, "昵称不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                edit();
                break;
            case R.id.imageBack:

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
        String url = Constant.HOST + Constant.Url.USER_SVAEINFO;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime",tokenTime);
        }
        params.put("key", "nickname");
        params.put("value", editNickName.getText().toString().trim());
        return new OkObject(params, url);
    }

    /**
     * 修改
     */
    private void edit() {
        showLoadingDialog();
        ApiClient.post(EditActivity.this, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("EditActivity--修改昵称", s + "");
                try {
                    SimpleInfo simpleInfo = GsonUtils.parseJSON(s, SimpleInfo.class);
                    if (simpleInfo.getStatus() == 1) {
                        Intent intent = new Intent();
                        intent.setAction(Constant.BroadcastCode.USERINFO);
                        intent.putExtra(Constant.IntentKey.NICKNAME, editNickName.getText().toString().trim());
                        sendBroadcast(intent);
                        finish();
                    } else if (simpleInfo.getStatus() == 3) {
                        MyDialog.showReLoginDialog(EditActivity.this);
                    } else {
                        Toast.makeText(EditActivity.this, simpleInfo.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(EditActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(EditActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
