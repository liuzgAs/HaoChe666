package com.haoche666.buyer.avtivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.haoche666.buyer.R;
import com.haoche666.buyer.base.ZjbBaseActivity;


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
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageBack:
                finish();
                break;
            default:
                break;
        }
    }
}
