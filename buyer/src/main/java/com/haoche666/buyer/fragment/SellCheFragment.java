package com.haoche666.buyer.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.haoche666.buyer.R;
import com.haoche666.buyer.activity.ChengShiXZActivity;
import com.haoche666.buyer.activity.PinPaiXCActivity;
import com.haoche666.buyer.activity.WebActivity;
import com.haoche666.buyer.base.MyDialog;
import com.haoche666.buyer.base.ZjbBaseFragment;
import com.haoche666.buyer.constant.Constant;
import com.haoche666.buyer.model.IndexCitylist;
import com.haoche666.buyer.model.OkObject;
import com.haoche666.buyer.model.SimpleInfo;
import com.haoche666.buyer.util.ApiClient;
import com.haoche666.buyer.util.DateTransforam;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;

import java.text.ParseException;
import java.util.Calendar;
import java.util.HashMap;

import huisedebi.zjb.mylibrary.util.GsonUtils;
import huisedebi.zjb.mylibrary.util.LogUtil;
import huisedebi.zjb.mylibrary.util.ScreenUtils;

public class SellCheFragment extends ZjbBaseFragment implements View.OnClickListener {

    private View mInflate;
    private View viewBar;
    private ImageView imageBack;
    private TextView textViewRight;
    private TextView textSellCity;
    private TextView textChePaiCity;
    private int sell_city_id;
    private int brand_city_id;
    private TextView textCarName;
    private String name;
    private String card_time;
    private TextView textTime;
    private TextView textCheKuang;
    private String[] cheKuangArr = new String[]{
            "非常好",
            "良好",
            "一般",
            "差劲",
            "非常差",
    };
    private SimpleRatingBar ratingbarCheKuang;
    private int cheKuang = 0;
    private int km = -1;
    private TextView textLiCheng;

    public SellCheFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mInflate == null) {
            mInflate = inflater.inflate(R.layout.fragment_sell_che, container, false);
            init();
        }
        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) mInflate.getParent();
        if (parent != null) {
            parent.removeView(mInflate);
        }
        return mInflate;
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void findID() {
        viewBar = mInflate.findViewById(R.id.viewBar);
        imageBack = mInflate.findViewById(R.id.imageBack);
        textViewRight = mInflate.findViewById(R.id.textViewRight);
        textSellCity = mInflate.findViewById(R.id.textSellCity);
        textChePaiCity = mInflate.findViewById(R.id.textChePaiCity);
        textCarName = mInflate.findViewById(R.id.textCarName);
        textTime = mInflate.findViewById(R.id.textTime);
        textCheKuang = mInflate.findViewById(R.id.textCheKuang);
        ratingbarCheKuang = mInflate.findViewById(R.id.ratingbarCheKuang);
        textLiCheng = mInflate.findViewById(R.id.textLiCheng);
    }

    @Override
    protected void initViews() {
        ViewGroup.LayoutParams layoutParams = viewBar.getLayoutParams();
        layoutParams.height = (int) (getResources().getDimension(R.dimen.titleHeight) + ScreenUtils.getStatusBarHeight(mContext));
        viewBar.setLayoutParams(layoutParams);
        viewBar.setPadding(0, ScreenUtils.getStatusBarHeight(mContext), 0, 0);
        imageBack.setVisibility(View.GONE);
        ((TextView) mInflate.findViewById(R.id.textViewTitle)).setText("我要卖车");
        textViewRight.setText("卖车流程");
        textCheKuang.setText("车况等级：请选择");
        ratingbarCheKuang.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void setListeners() {
        textViewRight.setOnClickListener(this);
        mInflate.findViewById(R.id.viewSellCity).setOnClickListener(this);
        mInflate.findViewById(R.id.viewChePiaCity).setOnClickListener(this);
        mInflate.findViewById(R.id.viewCarName).setOnClickListener(this);
        mInflate.findViewById(R.id.viewTime).setOnClickListener(this);
        mInflate.findViewById(R.id.viewLiCheng).setOnClickListener(this);
        mInflate.findViewById(R.id.viewCheKuang).setOnClickListener(this);
        mInflate.findViewById(R.id.buttonTiJiao).setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.RequestResultCode.CITY && resultCode == Constant.RequestResultCode.CITY) {
            IndexCitylist.CityEntity.ListEntity cityBean = (IndexCitylist.CityEntity.ListEntity) data.getSerializableExtra(Constant.IntentKey.BEAN);
            sell_city_id = cityBean.getId();
            textSellCity.setText(cityBean.getName());
        }
        if (requestCode == Constant.RequestResultCode.CITY01 && resultCode == Constant.RequestResultCode.CITY) {
            IndexCitylist.CityEntity.ListEntity cityBean = (IndexCitylist.CityEntity.ListEntity) data.getSerializableExtra(Constant.IntentKey.BEAN);
            brand_city_id = cityBean.getId();
            textChePaiCity.setText(cityBean.getName());
        }
        if (requestCode == Constant.RequestResultCode.PIN_PAI && resultCode == Constant.RequestResultCode.PIN_PAI) {
            name = data.getStringExtra(Constant.IntentKey.NAME);
            textCarName.setText(name);
        }
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.SELLCAR;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        params.put("name", name);
        params.put("card_time", card_time);
        params.put("km", km + "");
        params.put("grade", cheKuang + "");
        params.put("sell_city_id", sell_city_id + "");
        params.put("brand_city_id", brand_city_id + "");
        return new OkObject(params, url);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.buttonTiJiao:
                if (sell_city_id == 0) {
                    Toast.makeText(mContext, "请选择出售城市", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (brand_city_id == 0) {
                    Toast.makeText(mContext, "请选择车牌城市", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(mContext, "请选择车辆名称", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(card_time)) {
                    Toast.makeText(mContext, "请选择上牌时间", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (km == -1) {
                    Toast.makeText(mContext, "请选择表显里程", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (cheKuang == 0) {
                    Toast.makeText(mContext, "请选择车况等级", Toast.LENGTH_SHORT).show();
                    return;
                }
                showLoadingDialog();
                ApiClient.post(mContext, getOkObject(), new ApiClient.CallBack() {
                    @Override
                    public void onSuccess(String s) {
                        cancelLoadingDialog();
                        LogUtil.LogShitou("SellCheFragment--onSuccess", s + "");
                        try {
                            SimpleInfo simpleInfo = GsonUtils.parseJSON(s, SimpleInfo.class);
                            if (simpleInfo.getStatus() == 1) {
                            } else if (simpleInfo.getStatus() == 3) {
                                MyDialog.showReLoginDialog(mContext);
                            } else {
                            }
                            MyDialog.showTipDialog(mContext, simpleInfo.getInfo());
                        } catch (Exception e) {
                            Toast.makeText(mContext, "数据出错", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError() {
                        cancelLoadingDialog();
                        Toast.makeText(mContext, "请求失败", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.viewCheKuang:
                LayoutInflater inflater1 = LayoutInflater.from(mContext);
                View dialog_che_kuang = inflater1.inflate(R.layout.dialog_che_kuang, null);
                final AlertDialog cheKuangDialog = new AlertDialog.Builder(mContext, R.style.dialog)
                        .setView(dialog_che_kuang)
                        .create();
                cheKuangDialog.show();
                TextView[] textCheKuangArr = new TextView[5];
                textCheKuangArr[0] = dialog_che_kuang.findViewById(R.id.textCheKuang01);
                textCheKuangArr[1] = dialog_che_kuang.findViewById(R.id.textCheKuang02);
                textCheKuangArr[2] = dialog_che_kuang.findViewById(R.id.textCheKuang03);
                textCheKuangArr[3] = dialog_che_kuang.findViewById(R.id.textCheKuang04);
                textCheKuangArr[4] = dialog_che_kuang.findViewById(R.id.textCheKuang05);
                for (int i = 0; i < textCheKuangArr.length; i++) {
                    final int finalI = i;
                    textCheKuangArr[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            cheKuang = 5 - finalI;
                            textCheKuang.setText("车况等级：" + cheKuangArr[finalI]);
                            ratingbarCheKuang.setVisibility(View.VISIBLE);
                            ratingbarCheKuang.setNumberOfStars(cheKuang);
                            ratingbarCheKuang.setRating(cheKuang);
                            cheKuangDialog.dismiss();
                        }
                    });
                }
                Window dialogWindow1 = cheKuangDialog.getWindow();
                dialogWindow1.setGravity(Gravity.BOTTOM);
                dialogWindow1.setWindowAnimations(R.style.dialogFenXiang);
                WindowManager.LayoutParams lp1 = dialogWindow1.getAttributes();
                DisplayMetrics d1 = mContext.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
                lp1.width = (int) (d1.widthPixels * 1); // 高度设置为屏幕的0.6
                dialogWindow1.setAttributes(lp1);
                break;
            case R.id.viewLiCheng:
                LayoutInflater inflater = LayoutInflater.from(mContext);
                View dialog_chan_pin = inflater.inflate(R.layout.dialog_li_cheng, null);
                final NumberPicker numPicker01 = dialog_chan_pin.findViewById(R.id.numPicker01);
                numPicker01.setMinValue(0);
                numPicker01.setMaxValue(9);
                final NumberPicker numPicker02 = dialog_chan_pin.findViewById(R.id.numPicker02);
                numPicker02.setMinValue(0);
                numPicker02.setMaxValue(9);
                final NumberPicker numPicker03 = dialog_chan_pin.findViewById(R.id.numPicker03);
                numPicker03.setMinValue(0);
                numPicker03.setMaxValue(9);
                final NumberPicker numPicker04 = dialog_chan_pin.findViewById(R.id.numPicker04);
                numPicker04.setMinValue(0);
                numPicker04.setMaxValue(9);
                final NumberPicker numPicker05 = dialog_chan_pin.findViewById(R.id.numPicker05);
                numPicker05.setMinValue(0);
                numPicker05.setMaxValue(9);
                final NumberPicker numPicker06 = dialog_chan_pin.findViewById(R.id.numPicker06);
                numPicker06.setMinValue(0);
                numPicker06.setMaxValue(9);
                final AlertDialog xinZengDialog = new AlertDialog.Builder(mContext, R.style.dialog)
                        .setView(dialog_chan_pin)
                        .create();
                xinZengDialog.show();
                dialog_chan_pin.findViewById(R.id.textCancle).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        xinZengDialog.dismiss();
                    }
                });
                dialog_chan_pin.findViewById(R.id.textSure).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        km = Integer.parseInt("" + numPicker01.getValue() + numPicker02.getValue() + numPicker03.getValue() + numPicker04.getValue() + numPicker05.getValue()+ numPicker06.getValue());
                        textLiCheng.setText(km + "公里");
                        xinZengDialog.dismiss();
                    }
                });
                Window dialogWindow = xinZengDialog.getWindow();
                dialogWindow.setGravity(Gravity.BOTTOM);
                dialogWindow.setWindowAnimations(R.style.dialogFenXiang);
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                DisplayMetrics d = mContext.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
                lp.width = (int) (d.widthPixels * 1); // 高度设置为屏幕的0.6
                dialogWindow.setAttributes(lp);
                break;
            case R.id.viewTime:
                Calendar c = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        try {
                            card_time = DateTransforam.dateToStamp(year + "-" + (month + 1) + "-" + dayOfMonth);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        textTime.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
                break;
            case R.id.viewCarName:
                intent.setClass(mContext, PinPaiXCActivity.class);
                intent.putExtra(Constant.IntentKey.NAME, true);
                startActivityForResult(intent, Constant.RequestResultCode.PIN_PAI);
                break;
            case R.id.viewChePiaCity:
                intent.setClass(mContext, ChengShiXZActivity.class);
                startActivityForResult(intent, Constant.RequestResultCode.CITY01);
                break;
            case R.id.viewSellCity:
                intent.setClass(mContext, ChengShiXZActivity.class);
                startActivityForResult(intent, Constant.RequestResultCode.CITY);
                break;
            case R.id.textViewRight:
                intent.setClass(mContext, WebActivity.class);
                intent.putExtra(Constant.IntentKey.TITLE, "卖车流程");
                intent.putExtra(Constant.IntentKey.URL, Constant.Url.MAI_CHE_GUI_ZE);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
