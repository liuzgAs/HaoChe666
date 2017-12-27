package com.haoche666.buyer.avtivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.haoche666.buyer.R;
import com.haoche666.buyer.base.MyDialog;
import com.haoche666.buyer.base.ZjbBaseActivity;
import com.haoche666.buyer.constant.Constant;
import com.haoche666.buyer.model.CarsearchGetcity;
import com.haoche666.buyer.model.OkObject;
import com.haoche666.buyer.model.Product;
import com.haoche666.buyer.util.ApiClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import huisedebi.zjb.mylibrary.util.GsonUtils;
import huisedebi.zjb.mylibrary.util.LogUtil;

/**
 * 查违章
 *
 * @author Administrator
 */
public class ChaWeiZhangActivity extends ZjbBaseActivity implements View.OnClickListener {

    private Product.DataBean dataBean;
    private TextView textPrice;
    private double blance;
    private TextView textBlance;
    private TextView textCity;
    private List<CarsearchGetcity.DataBean> carsearchGetcityData = new ArrayList<>();
    private List<List<CarsearchGetcity.DataBean.CitysBean>> citySecondList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cha_wei_zhang);
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
        textCity = (TextView) findViewById(R.id.textCity);
    }

    @Override
    protected void initViews() {
        ((TextView) findViewById(R.id.textViewTitle)).setText("查违章");
        textPrice.setText("¥" + dataBean.getPrice());
        textBlance.setText("\u3000|\u3000余额：¥" + blance);
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
        findViewById(R.id.viewCity).setOnClickListener(this);
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.CARSEARCH_GETCITY;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        return new OkObject(params, url);
    }

    @Override
    protected void initData() {
        showLoadingDialog();
        ApiClient.post(ChaWeiZhangActivity.this, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("ChaWeiZhangActivity--城市列表", s + "");
                try {
                    CarsearchGetcity carsearchGetcity = GsonUtils.parseJSON(s, CarsearchGetcity.class);
                    if (carsearchGetcity.getStatus() == 1) {
                        carsearchGetcityData.clear();
                        carsearchGetcityData.addAll(carsearchGetcity.getData());
                        citySecondList.clear();
                        for (int i = 0; i < carsearchGetcityData.size(); i++) {
                            List<CarsearchGetcity.DataBean.CitysBean> citysBeanList = carsearchGetcityData.get(i).getCitys();
                            citySecondList.add(citysBeanList);
                        }
                    } else if (carsearchGetcity.getStatus() == 3) {
                        MyDialog.showReLoginDialog(ChaWeiZhangActivity.this);
                    } else {
                        Toast.makeText(ChaWeiZhangActivity.this, carsearchGetcity.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(ChaWeiZhangActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(ChaWeiZhangActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.viewCity:
                showCityPicker();
                break;
            case R.id.imageBack:
                finish();
                break;
            default:
                break;
        }
    }

    private void showCityPicker() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),
                    0);
        }
        /**
         * 注意 ：如果是三级联动的数据(省市区等)，请参照 JsonDataActivity 类里面的写法。
         */

        OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                textCity.setText(carsearchGetcityData.get(options1).getPickerViewText() + "\u3000" + citySecondList.get(options1).get(options2).getCity_name());
                LogUtil.LogShitou("ChaWeiZhangActivity--onOptionsSelect", ""+citySecondList.get(options1).get(options2).getCity_code());
            }
        })
                .setTitleText("地区选择")
                .setContentTextSize(20)//设置滚轮文字大小
                .setDividerColor(Color.LTGRAY)//设置分割线的颜色
                .setSelectOptions(0, 0)//默认选中项
                .setBgColor(getResources().getColor(R.color.background))
                .setTitleBgColor(getResources().getColor(R.color.white))
                .setTitleColor(getResources().getColor(R.color.light_black))
                .setCancelColor(getResources().getColor(R.color.text_gray))
                .setSubmitColor(getResources().getColor(R.color.basic_color))
                .setTextColorCenter(getResources().getColor(R.color.basic_color))
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setBackgroundId(0x66000000) //设置外部遮罩颜色
                .build();

        //pvOptions.setSelectOptions(1,1);
        /*pvOptions.setPicker(options1Items);//一级选择器*/
//                pvOptions.setPicker(options1Items, options2Items);//二级选择器
        pvOptions.setPicker(carsearchGetcityData, citySecondList);//三级选择器
        pvOptions.show();
    }
}
