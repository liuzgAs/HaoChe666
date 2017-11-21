package com.haoche666.buyer.avtivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.haoche666.buyer.R;
import com.haoche666.buyer.base.ZjbBaseActivity;

/**
 * @author Administrator
 */
public class ZhuCeActivity extends ZjbBaseActivity implements View.OnClickListener {
    private EditText[] editView = new EditText[5];
    private View[] lineView = new View[5];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu_ce);
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
        editView[3] = (EditText) findViewById(R.id.edit04);
        editView[4] = (EditText) findViewById(R.id.edit05);
        lineView[0] = findViewById(R.id.line01);
        lineView[1] = findViewById(R.id.line02);
        lineView[2] = findViewById(R.id.line03);
        lineView[3] = findViewById(R.id.line04);
        lineView[4] = findViewById(R.id.line05);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void setListeners() {
        findViewById(R.id.buttonZhuCe).setOnClickListener(this);
        findViewById(R.id.imageBack).setOnClickListener(this);
        for (int i = 0; i < editView.length; i++) {
            final int finalI = i;
            editView[i].setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        for (View aLineView : lineView) {
                            aLineView.setBackgroundColor(getResources().getColor(R.color.text_gray));
                        }
                        lineView[finalI].setBackgroundColor(getResources().getColor(R.color.basic_color));
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
        switch (view.getId()){
            case R.id.buttonZhuCe:
                Intent intent = new Intent();
                intent.setClass(this,DengLuActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.imageBack:
                finish();
                break;
            default:
                break;
        }
    }
}
