package com.haoche666.buyer.avtivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.haoche666.buyer.R;
import com.haoche666.buyer.base.MyDialog;
import com.haoche666.buyer.base.ZjbBaseActivity;
import com.haoche666.buyer.constant.Constant;
import com.haoche666.buyer.model.AliPayBean;
import com.haoche666.buyer.model.CarCarparam;
import com.haoche666.buyer.model.Carsearch;
import com.haoche666.buyer.model.CorderCreateorder;
import com.haoche666.buyer.model.OkObject;
import com.haoche666.buyer.model.PayAlipay;
import com.haoche666.buyer.model.PayWxpay;
import com.haoche666.buyer.model.Product;
import com.haoche666.buyer.model.SimpleInfo;
import com.haoche666.buyer.util.ApiClient;
import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.HashMap;
import java.util.Map;

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
    private int type_id =1;
    final IWXAPI api = WXAPIFactory.createWXAPI(this, null);
    private BroadcastReceiver recevier = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action) {
                case Constant.BroadcastCode.PAY_RECEIVER:
                    cancelLoadingDialog();
                    int error = intent.getIntExtra("error", -1);
                    if (error == 0) {
                        paySuccess();
                    } else if (error == -1) {
                        MyDialog.showTipDialog(ChaWeiBaoActivity.this, "支付失败");
                    } else if (error == -2) {
                        MyDialog.showTipDialog(ChaWeiBaoActivity.this, "支付失败");
                    }
                    break;
                default:
                    break;
            }
        }
    };
    private String order_no;

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
        params.put("type_id", type_id + "");
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
                                order_no = corderCreateorder.getOrder_no();
                                switch (payMode) {
                                    case 0:
                                        yuEZhiFu();
                                        break;
                                    case 1:
                                        zhiFuBao();
                                        break;
                                    case 2:
                                        weiXinZF();
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
    private OkObject getWXOkObject() {
        String url = Constant.HOST + Constant.Url.PAY_WXPAY;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        params.put("order_no", order_no);
        return new OkObject(params, url);
    }

    /**
     * des： 微信支付
     * author： ZhangJieBo
     * date： 2017/12/25/025 17:25
     */
    private void weiXinZF() {
        showLoadingDialog();
        ApiClient.post(ChaWeiBaoActivity.this, getWXOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("ChaWeiBaoActivity--微信支付", s + "");
                try {
                    PayWxpay payWxpay = GsonUtils.parseJSON(s, PayWxpay.class);
                    if (payWxpay.getStatus() == 1) {
                        wechatPay(payWxpay);
                    } else if (payWxpay.getStatus() == 3) {
                        MyDialog.showReLoginDialog(ChaWeiBaoActivity.this);
                    } else {
                        Toast.makeText(ChaWeiBaoActivity.this, payWxpay.getInfo(), Toast.LENGTH_SHORT).show();
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
    }

    /**
     * 微信支付
     */
    private void wechatPay(PayWxpay payWxpay) {
        if (!checkIsSupportedWeachatPay()) {
            Toast.makeText(ChaWeiBaoActivity.this, "您暂未安装微信或您的微信版本暂不支持支付功能\n请下载安装最新版本的微信", Toast.LENGTH_SHORT).show();
        } else {
            api.registerApp(payWxpay.getAppid());
            PayReq mPayReq = new PayReq();
            mPayReq.appId = payWxpay.getAppid();
            mPayReq.partnerId = payWxpay.getPartnerid();
            mPayReq.prepayId = payWxpay.getPrepayid();
            mPayReq.packageValue = payWxpay.getPackageX();
            mPayReq.nonceStr = payWxpay.getNonceStr();
            mPayReq.timeStamp = payWxpay.getTimeStamp() + "";
            mPayReq.sign = payWxpay.getSign().toUpperCase();
            api.sendReq(mPayReq);
        }
    }

    /**
     * 检查微信版本是否支付支付或是否安装可支付的微信版本
     */
    private boolean checkIsSupportedWeachatPay() {
        boolean isPaySupported = api.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
        return isPaySupported;
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getZFBOkObject() {
        String url = Constant.HOST + Constant.Url.PAY_ALIPAY;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        params.put("order_no", order_no + "");
        return new OkObject(params, url);
    }

    /**
     * 支付宝支付
     */
    private void zhiFuBao() {
        showLoadingDialog();
        ApiClient.post(ChaWeiBaoActivity.this, getZFBOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("ChaWeiBaoActivity--支付宝", s + "");
                try {
                    final PayAlipay payAlipay = GsonUtils.parseJSON(s, PayAlipay.class);
                    if (payAlipay.getStatus() == 1) {
                        Runnable payRunnable = new Runnable() {

                            @Override
                            public void run() {
                                try {
                                    PayTask alipay = new PayTask(ChaWeiBaoActivity.this);
                                    Map<String, String> stringMap = alipay.payV2(payAlipay.getOrderinfo(), true);
                                    AliPayBean aliPayBean = GsonUtils.parseJSON(stringMap.get("result"), AliPayBean.class);
                                    LogUtil.LogShitou("ChaWeiBaoActivity--支付结果", "" + stringMap.get("result"));
                                    LogUtil.LogShitou("ChaWeiBaoActivity--支付结果码", "" + aliPayBean.getAlipay_trade_app_pay_response().getCode());
                                    switch (aliPayBean.getAlipay_trade_app_pay_response().getCode()) {
                                        case 10000:
                                            paySuccess();
                                            break;
                                        case 8000:
                                            paySuccess();
                                            break;
                                        case 4000:
                                            MyDialog.showTipDialog(ChaWeiBaoActivity.this, "订单支付失败");
                                            break;
                                        case 5000:
                                            MyDialog.showTipDialog(ChaWeiBaoActivity.this, "重复请求");
                                            break;
                                        case 6001:
                                            MyDialog.showTipDialog(ChaWeiBaoActivity.this, "取消支付");
                                            break;
                                        case 6002:
                                            MyDialog.showTipDialog(ChaWeiBaoActivity.this, "网络连接错误");
                                            break;
                                        case 6004:
                                            MyDialog.showTipDialog(ChaWeiBaoActivity.this, "支付结果未知");
                                            break;
                                        default:
                                            MyDialog.showTipDialog(ChaWeiBaoActivity.this, "支付失败");
                                            break;
                                    }
                                } catch (Exception e) {
                                }
                            }
                        };
                        // 必须异步调用
                        Thread payThread = new Thread(payRunnable);
                        payThread.start();
                    } else if (payAlipay.getStatus() == 3) {
                        MyDialog.showReLoginDialog(ChaWeiBaoActivity.this);
                    } else {
                    }
                    Toast.makeText(ChaWeiBaoActivity.this, payAlipay.getInfo(), Toast.LENGTH_SHORT).show();
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
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getJieGuoOkObject() {
        String url = Constant.HOST + Constant.Url.CARSEARCH;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime",tokenTime);
        }
        params.put("order_no",order_no);
        params.put("type_id",type_id+"");
        return new OkObject(params, url);
    }

    /**
     * des： 支付成功
     * author： ZhangJieBo
     * date： 2017/12/25/025 16:11
     */
    private void paySuccess() {
        showLoadingDialog();
        ApiClient.post(ChaWeiBaoActivity.this, getJieGuoOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("ChaWeiBaoActivity--onSuccess", s + "");
                try {
                    Carsearch carsearch = GsonUtils.parseJSON(s, Carsearch.class);
                    if (carsearch.getStatus() == 1) {
                        Intent intent = new Intent();
                        intent.setClass(ChaWeiBaoActivity.this,WebActivity.class);
                        intent.putExtra(Constant.IntentKey.TITLE,"查维保");
                        intent.putExtra(Constant.IntentKey.URL,carsearch.getUrl());
                        startActivity(intent);
                    } else if (carsearch.getStatus() == 3) {
                        MyDialog.showReLoginDialog(ChaWeiBaoActivity.this);
                    } else {
                        Toast.makeText(ChaWeiBaoActivity.this, carsearch.getInfo(), Toast.LENGTH_SHORT).show();
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
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getYuEOkObject() {
        String url = Constant.HOST + Constant.Url.PAY_BALANCEPAY;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        params.put("order_no", order_no + "");
        return new OkObject(params, url);
    }

    /**
     * 余额支付
     */
    private void yuEZhiFu() {
        showLoadingDialog();
        ApiClient.post(ChaWeiBaoActivity.this, getYuEOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("ChaWeiBaoActivity--onSuccess", s + "");
                try {
                    SimpleInfo simpleInfo = GsonUtils.parseJSON(s, SimpleInfo.class);
                    if (simpleInfo.getStatus() == 1) {
                        paySuccess();
                    } else if (simpleInfo.getStatus() == 3) {
                        MyDialog.showReLoginDialog(ChaWeiBaoActivity.this);
                    } else {
                        Toast.makeText(ChaWeiBaoActivity.this, simpleInfo.getInfo(), Toast.LENGTH_SHORT).show();
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
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.BroadcastCode.PAY_RECEIVER);
        registerReceiver(recevier, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(recevier);
    }
}
