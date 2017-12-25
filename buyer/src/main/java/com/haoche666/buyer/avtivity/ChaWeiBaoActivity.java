package com.haoche666.buyer.avtivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.haoche666.buyer.R;
import com.haoche666.buyer.base.ZjbBaseActivity;
import com.haoche666.buyer.constant.Constant;
import com.haoche666.buyer.model.CarCarparam;
import com.haoche666.buyer.model.Product;

/**
 * 查维保
 * @author Administrator
 */
public class ChaWeiBaoActivity extends ZjbBaseActivity implements View.OnClickListener {

    private Product.DataBean dataBean;
    private TextView textPrice;
    private double blance;
    private TextView textBlance;
    private CarCarparam.BrandBean.ListBean brandBean;
    private TextView textBrand;
    private EditText editVin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cha_wei_bao);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {
        Intent intent = getIntent();
        dataBean = (Product.DataBean) intent.getSerializableExtra(Constant.IntentKey.BEAN);
        blance = intent.getDoubleExtra(Constant.IntentKey.VALUE, 0);
    }

    @Override
    protected void findID() {
        textPrice = (TextView) findViewById(R.id.textPrice);
        textBlance = (TextView) findViewById(R.id.textBlance);
        textBrand = (TextView) findViewById(R.id.textBrand);
        editVin = (EditText) findViewById(R.id.editVin);
    }

    @Override
    protected void initViews() {
        ((TextView) findViewById(R.id.textViewTitle)).setText("查维保");
        textPrice.setText("¥"+dataBean.getPrice());
        textBlance.setText("\u3000|\u3000余额：¥"+blance);
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
        findViewById(R.id.viewBrand).setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==Constant.RequestResultCode.BRAND&&resultCode==Constant.RequestResultCode.BRAND){
            brandBean = (CarCarparam.BrandBean.ListBean) data.getSerializableExtra(Constant.IntentKey.BEAN);
            textBrand.setText(brandBean.getName());
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.viewBrand:
                Intent intent = new Intent();
                intent.setClass(this,PinPaiXCActivity.class);
                intent.putExtra(Constant.IntentKey.BRAND,1);
                startActivityForResult(intent,Constant.RequestResultCode.BRAND);
                break;
            case R.id.imageBack:
                finish();
                break;
            default:
                break;
        }
    }
}
