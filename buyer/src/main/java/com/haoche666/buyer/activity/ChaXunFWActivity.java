package com.haoche666.buyer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.haoche666.buyer.R;
import com.haoche666.buyer.base.MyDialog;
import com.haoche666.buyer.base.ToLoginActivity;
import com.haoche666.buyer.base.ZjbBaseActivity;
import com.haoche666.buyer.constant.Constant;
import com.haoche666.buyer.model.OkObject;
import com.haoche666.buyer.model.Product;
import com.haoche666.buyer.model.ProductGetbalance;
import com.haoche666.buyer.util.ApiClient;

import java.util.HashMap;

import huisedebi.zjb.mylibrary.util.GsonUtils;
import huisedebi.zjb.mylibrary.util.LogUtil;

/**
 * 查询服务
 *
 * @author Administrator
 */
public class ChaXunFWActivity extends ZjbBaseActivity implements View.OnClickListener {
//    private ConvenientBanner banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cha_xun_fw);
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
//        banner = (ConvenientBanner) findViewById(R.id.banner);
    }

    @Override
    protected void initViews() {
        ((TextView) findViewById(R.id.textViewTitle)).setText("查询服务");
//        banner.setScrollDuration(1000);
//        banner.startTurning(3000);
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
        findViewById(R.id.viewChaWeiXiu).setOnClickListener(this);
        findViewById(R.id.viewChaWeiZhang).setOnClickListener(this);
        findViewById(R.id.viewChaChuXian).setOnClickListener(this);
        findViewById(R.id.viewChaXunLS).setOnClickListener(this);
    }

//    /**
//     * des： 网络请求参数
//     * author： ZhangJieBo
//     * date： 2017/8/28 0028 上午 9:55
//     */
//    private OkObject getBannerOkObject() {
//        String url = Constant.HOST + Constant.Url.PRODUCT_GETBANNER;
//        HashMap<String, String> params = new HashMap<>();
//        if (isLogin) {
//            params.put("uid", userInfo.getUid());
//            params.put("tokenTime", tokenTime);
//        }
//        return new OkObject(params, url);
//    }

    @Override
    protected void initData() {
//        showLoadingDialog();
//        ApiClient.post(ChaXunFWActivity.this, getBannerOkObject(), new ApiClient.CallBack() {
//            @Override
//            public void onSuccess(String s) {
//                cancelLoadingDialog();
//                LogUtil.LogShitou("SellCheFragment--onSuccess", s + "");
//                try {
//                    ProductGetbanner productGetbanner = GsonUtils.parseJSON(s, ProductGetbanner.class);
//                    if (productGetbanner.getStatus() == 1) {
//                        List<ProductGetbanner.BannerBean> productGetbannerBanner = productGetbanner.getBanner();
//                        banner.setPages(new CBViewHolderCreator() {
//                            @Override
//                            public Object createHolder() {
//                                return new ChaXunBannerImgHolderView();
//                            }
//                        }, productGetbannerBanner);
//                        banner.setPageIndicator(new int[]{R.drawable.shape_indicator_normal, R.drawable.shape_indicator_selected});
//                    } else if (productGetbanner.getStatus() == 3) {
//                        MyDialog.showReLoginDialog(ChaXunFWActivity.this);
//                    } else {
//                        Toast.makeText(ChaXunFWActivity.this, productGetbanner.getInfo(), Toast.LENGTH_SHORT).show();
//                    }
//                } catch (Exception e) {
//                    Toast.makeText(ChaXunFWActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onError() {
//                cancelLoadingDialog();
//                Toast.makeText(ChaXunFWActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.viewChaWeiXiu:
                if (isLogin) {
                    chaCun(1);
                } else {
                    ToLoginActivity.toLoginActivity(this);
                }
                break;
            case R.id.viewChaChuXian:
                if (isLogin) {
                    chaCun(2);
                } else {
                    ToLoginActivity.toLoginActivity(this);
                }
                break;
            case R.id.viewChaWeiZhang:
                if (isLogin) {
                    chaCun(3);
                } else {
                    ToLoginActivity.toLoginActivity(this);
                }
                break;
            case R.id.viewChaXunLS:
                if (isLogin) {
                    intent.setClass(this, ChaXunLSActivity.class);
                    startActivity(intent);
                } else {
                    ToLoginActivity.toLoginActivity(this);
                }
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
    private OkObject getOkObject(int type_id) {
        String url = Constant.HOST + Constant.Url.PRODUCT;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        params.put("type_id", type_id + "");
        return new OkObject(params, url);
    }

    /**
     * 查询前请求
     */
    private void chaCun(final int type_id) {
        showLoadingDialog();
        ApiClient.post(ChaXunFWActivity.this, getOkObject(type_id), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("ChaXunFWActivity--获取付费类型信息", s + "");
                try {
                    Product product = GsonUtils.parseJSON(s, Product.class);
                    if (product.getStatus() == 1) {
                        chaXunYE(product, type_id);
                    } else if (product.getStatus() == 3) {
                        MyDialog.showReLoginDialog(ChaXunFWActivity.this);
                    } else {
                        Toast.makeText(ChaXunFWActivity.this, product.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(ChaXunFWActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(ChaXunFWActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getYEOkObject() {
        String url = Constant.HOST + Constant.Url.PRODUCT_GETBALANCE;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        return new OkObject(params, url);
    }

    private void chaXunYE(final Product product, final int type_id) {
        showLoadingDialog();
        ApiClient.post(ChaXunFWActivity.this, getYEOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("ChaXunFWActivity--余额", s + "");
                try {
                    ProductGetbalance productGetbalance = GsonUtils.parseJSON(s, ProductGetbalance.class);
                    if (productGetbalance.getStatus() == 1) {
                        double balance = productGetbalance.getBalance();
                        Intent intent = new Intent();
                        intent.putExtra(Constant.IntentKey.BEAN, product.getData().get(0));
                        intent.putExtra(Constant.IntentKey.VALUE, balance);
                        switch (type_id) {
                            case 1:
                                intent.setClass(ChaXunFWActivity.this, ChaWeiBaoActivity.class);
                                startActivity(intent);
                                break;
                            case 2:
                                intent.setClass(ChaXunFWActivity.this, ChaChuXianActivity.class);
                                startActivity(intent);
                                break;
                            case 3:
                                intent.setClass(ChaXunFWActivity.this, ChaWeiZhangActivity.class);
                                startActivity(intent);
                                break;
                            default:

                                break;
                        }
                    } else if (productGetbalance.getStatus() == 3) {
                        MyDialog.showReLoginDialog(ChaXunFWActivity.this);
                    } else {
                        Toast.makeText(ChaXunFWActivity.this, productGetbalance.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(ChaXunFWActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(ChaXunFWActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
