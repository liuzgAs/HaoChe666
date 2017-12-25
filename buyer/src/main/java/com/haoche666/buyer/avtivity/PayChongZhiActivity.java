package com.haoche666.buyer.avtivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.haoche666.buyer.R;
import com.haoche666.buyer.base.MyDialog;
import com.haoche666.buyer.base.ZjbBaseActivity;
import com.haoche666.buyer.constant.Constant;
import com.haoche666.buyer.model.AliPayBean;
import com.haoche666.buyer.model.CorderRecharge;
import com.haoche666.buyer.model.OkObject;
import com.haoche666.buyer.model.PayAlipay;
import com.haoche666.buyer.util.ApiClient;

import java.util.HashMap;
import java.util.Map;

import huisedebi.zjb.mylibrary.util.GsonUtils;
import huisedebi.zjb.mylibrary.util.LogUtil;

public class PayChongZhiActivity extends ZjbBaseActivity implements View.OnClickListener {

    private TextView textJinE;
    private String jinE;
    private View[] paySelectView = new View[2];
    private View[] payView = new View[2];
    private int payMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_chong_zhi);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {
        Intent intent = getIntent();
        jinE = intent.getStringExtra(Constant.IntentKey.VALUE);
    }

    @Override
    protected void findID() {
        textJinE = (TextView) findViewById(R.id.textJinE);
        paySelectView[0] = findViewById(R.id.imageZhiFuBao);
        paySelectView[1] = findViewById(R.id.imageWeiXin);
        payView[0] = findViewById(R.id.viewZhiFuBao);
        payView[1] = findViewById(R.id.viewWeiXin);
    }

    @Override
    protected void initViews() {
        ((TextView) findViewById(R.id.textViewTitle)).setText("充值");
        textJinE.setText("¥" + jinE);
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
        findViewById(R.id.buttonChongZhi).setOnClickListener(this);
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonChongZhi:
                chongZhi();
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
        String url = Constant.HOST + Constant.Url.CORDER_RECHARGE;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        params.put("type_id", 5 + "");
        params.put("price", jinE);
        return new OkObject(params, url);
    }

    /**
     * des： 创建充值订单
     * author： ZhangJieBo
     * date： 2017/12/25/025 14:57
     */
    private void chongZhi() {
        showLoadingDialog();
        ApiClient.post(PayChongZhiActivity.this, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("PayChongZhiActivity--创建充值订单", s + "");
                try {
                    CorderRecharge corderRecharge = GsonUtils.parseJSON(s, CorderRecharge.class);
                    if (corderRecharge.getStatus() == 1) {
                        switch (payMode) {
                            case 0:
                                zhiFuBao(corderRecharge.getOrder_no());
                                break;
                            default:
                                break;
                        }
                    } else if (corderRecharge.getStatus() == 3) {
                        MyDialog.showReLoginDialog(PayChongZhiActivity.this);
                    } else {
                        Toast.makeText(PayChongZhiActivity.this, corderRecharge.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(PayChongZhiActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(PayChongZhiActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
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
            params.put("tokenTime", tokenTime);
        }
        params.put("order_no", order_no + "");
        return new OkObject(params, url);
    }

    /**
     * des： 支付成功
     * author： ZhangJieBo
     * date： 2017/12/25/025 16:11
     */
    private void paySuccess() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                MyDialog.dialogFinish(PayChongZhiActivity.this, "充值成功");
            }
        });
    }

    /**
     * des： 支付宝支付
     * author： ZhangJieBo
     * date： 2017/12/25/025 15:13
     */
    private void zhiFuBao(String order_no) {
        showLoadingDialog();
        ApiClient.post(PayChongZhiActivity.this, getZFBOkObject(order_no), new ApiClient.CallBack() {
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
                                    PayTask alipay = new PayTask(PayChongZhiActivity.this);
                                    Map<String, String> stringMap = alipay.payV2(payAlipay.getOrderinfo(), true);
                                    AliPayBean aliPayBean = GsonUtils.parseJSON(stringMap.get("result"), AliPayBean.class);
                                    LogUtil.LogShitou("ChaWeiBaoActivity--支付结果", ""+stringMap.get("result"));
                                    LogUtil.LogShitou("ChaWeiBaoActivity--支付结果码", ""+aliPayBean.getAlipay_trade_app_pay_response().getCode());
                                    switch (aliPayBean.getAlipay_trade_app_pay_response().getCode()) {
                                        case 10000:
                                            paySuccess();
                                            break;
                                        case 8000:
                                            paySuccess();
                                            break;
                                        case 4000:
                                            MyDialog.showTipDialog(PayChongZhiActivity.this, "订单支付失败");
                                            break;
                                        case 5000:
                                            MyDialog.showTipDialog(PayChongZhiActivity.this, "重复请求");
                                            break;
                                        case 6001:
                                            MyDialog.showTipDialog(PayChongZhiActivity.this, "取消支付");
                                            break;
                                        case 6002:
                                            MyDialog.showTipDialog(PayChongZhiActivity.this, "网络连接错误");
                                            break;
                                        case 6004:
                                            MyDialog.showTipDialog(PayChongZhiActivity.this, "支付结果未知");
                                            break;
                                        default:
                                            MyDialog.showTipDialog(PayChongZhiActivity.this, "支付失败");
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
                        MyDialog.showReLoginDialog(PayChongZhiActivity.this);
                    } else {
                    }
                    Toast.makeText(PayChongZhiActivity.this, payAlipay.getInfo(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(PayChongZhiActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(PayChongZhiActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
