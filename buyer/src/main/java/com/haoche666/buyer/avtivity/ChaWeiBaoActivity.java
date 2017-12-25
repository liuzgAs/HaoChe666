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
import com.haoche666.buyer.model.CarCarparam;
import com.haoche666.buyer.model.CorderCreateorder;
import com.haoche666.buyer.model.OkObject;
import com.haoche666.buyer.model.Product;
import com.haoche666.buyer.model.SimpleInfo;
import com.haoche666.buyer.util.ApiClient;

import java.util.HashMap;

import huisedebi.zjb.mylibrary.util.GsonUtils;
import huisedebi.zjb.mylibrary.util.LogUtil;

/**
 * 查维保
 *
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
    private int payMode = 0;
    private View[] paySelectView = new View[3];
    private View[] payView = new View[3];

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
        paySelectView[0] = findViewById(R.id.imageYuE);
        paySelectView[1] = findViewById(R.id.imageZhiFuBao);
        paySelectView[2] = findViewById(R.id.imageWeiXin);
        payView[0] = findViewById(R.id.viewYuE);
        payView[1] = findViewById(R.id.viewZhiFuBao);
        payView[2] = findViewById(R.id.viewWeiXin);
    }

    @Override
    protected void initViews() {
        ((TextView) findViewById(R.id.textViewTitle)).setText("查维保");
        textPrice.setText("¥" + dataBean.getPrice());
        textBlance.setText("\u3000|\u3000余额：¥" + blance);
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
        findViewById(R.id.viewBrand).setOnClickListener(this);
        findViewById(R.id.buttonTiJiao).setOnClickListener(this);
        for (int i = 0; i < payView.length; i++) {
            final int finalI = i;
            payView[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for (int j = 0; j < paySelectView.length; j++) {
                        paySelectView[j].setVisibility(View.GONE);
                    }
                    paySelectView[finalI].setVisibility(View.VISIBLE);
                    payMode = finalI;
                }
            });
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.RequestResultCode.BRAND && resultCode == Constant.RequestResultCode.BRAND) {
            brandBean = (CarCarparam.BrandBean.ListBean) data.getSerializableExtra(Constant.IntentKey.BEAN);
            textBrand.setText(brandBean.getName());
        }
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.CORDER_CREATEORDER;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        params.put("id", dataBean.getId() + "");
        params.put("type_id", 1 + "");
        params.put("vin", editVin.getText().toString().trim());
        params.put("brand_id", brandBean.getId() + "");
        return new OkObject(params, url);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonTiJiao:
                if (TextUtils.isEmpty(editVin.getText().toString().trim())) {
                    Toast.makeText(ChaWeiBaoActivity.this, "请输入要查询车辆的VIN码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (brandBean == null) {
                    Toast.makeText(ChaWeiBaoActivity.this, R.string.chaxunpinpai, Toast.LENGTH_SHORT).show();
                    return;
                }
                showLoadingDialog();
                ApiClient.post(ChaWeiBaoActivity.this, getOkObject(), new ApiClient.CallBack() {
                    @Override
                    public void onSuccess(String s) {
                        cancelLoadingDialog();
                        LogUtil.LogShitou("ChaWeiBaoActivity--onSuccess", s + "");
                        try {
                            CorderCreateorder corderCreateorder = GsonUtils.parseJSON(s, CorderCreateorder.class);
                            if (corderCreateorder.getStatus() == 1) {
                                switch (payMode) {
                                    case 0:
                                        yuEZhiFu(corderCreateorder.getOrder_no());
                                        break;
                                    case 1:
                                        zhiFuBao(corderCreateorder.getOrder_no());
                                        break;
                                    default:

                                        break;
                                }
                            } else if (corderCreateorder.getStatus() == 3) {
                                MyDialog.showReLoginDialog(ChaWeiBaoActivity.this);
                            } else {
                                Toast.makeText(ChaWeiBaoActivity.this, corderCreateorder.getInfo(), Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Toast.makeText(ChaWeiBaoActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError() {
                        cancelLoadingDialog();
                        Toast.makeText(ChaWeiBaoActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.viewBrand:
                Intent intent = new Intent();
                intent.setClass(this, PinPaiXCActivity.class);
                intent.putExtra(Constant.IntentKey.BRAND, 1);
                startActivityForResult(intent, Constant.RequestResultCode.BRAND);
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
    private OkObject getZFBOkObject(String order_no) {
        String url = Constant.HOST + Constant.Url.PAY_ALIPAY;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime",tokenTime);
        }
        params.put("order_no",order_no+"");
        return new OkObject(params, url);
    }

    /**
     * 支付宝支付
     */
    private void zhiFuBao(String order_no) {
        showLoadingDialog();
        ApiClient.post(ChaWeiBaoActivity.this, getZFBOkObject(order_no), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("ChaWeiBaoActivity--支付宝",s+ "");
                try {
                    SimpleInfo simpleInfo = GsonUtils.parseJSON(s, SimpleInfo.class);
                    if (simpleInfo.getStatus()==1){

                    }else if (simpleInfo.getStatus()==3){
                        MyDialog.showReLoginDialog(ChaWeiBaoActivity.this);
                    }else {
                        Toast.makeText(ChaWeiBaoActivity.this, simpleInfo.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(ChaWeiBaoActivity.this,"数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(ChaWeiBaoActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getYuEOkObject(String order_no) {
        String url = Constant.HOST + Constant.Url.PAY_BALANCEPAY;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime",tokenTime);
        }
        params.put("order_no",order_no+"");
        return new OkObject(params, url);
    }

    /**
     * 余额支付
     */
    private void yuEZhiFu(String order_no) {
        showLoadingDialog();
        ApiClient.post(ChaWeiBaoActivity.this, getYuEOkObject(order_no), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("ChaWeiBaoActivity--onSuccess",s+ "");
                try {
                    SimpleInfo simpleInfo = GsonUtils.parseJSON(s, SimpleInfo.class);
                    if (simpleInfo.getStatus()==1){
                    }else if (simpleInfo.getStatus()==3){
                        MyDialog.showReLoginDialog(ChaWeiBaoActivity.this);
                    }else {
                        Toast.makeText(ChaWeiBaoActivity.this, simpleInfo.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(ChaWeiBaoActivity.this,"数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(ChaWeiBaoActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
