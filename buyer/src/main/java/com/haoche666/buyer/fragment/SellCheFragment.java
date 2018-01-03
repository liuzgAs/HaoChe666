package com.haoche666.buyer.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.haoche666.buyer.R;
import com.haoche666.buyer.avtivity.ChengShiXZActivity;
import com.haoche666.buyer.avtivity.PinPaiXCActivity;
import com.haoche666.buyer.avtivity.WebActivity;
import com.haoche666.buyer.base.ZjbBaseFragment;
import com.haoche666.buyer.constant.Constant;
import com.haoche666.buyer.model.IndexCitylist;
import com.haoche666.buyer.util.DateTransforam;

import java.text.ParseException;
import java.util.Calendar;

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
    }

    @Override
    protected void initViews() {
        ViewGroup.LayoutParams layoutParams = viewBar.getLayoutParams();
        layoutParams.height = (int) (getResources().getDimension(R.dimen.titleHeight) + ScreenUtils.getStatusBarHeight(getActivity()));
        viewBar.setLayoutParams(layoutParams);
        viewBar.setPadding(0, ScreenUtils.getStatusBarHeight(getActivity()), 0, 0);
        imageBack.setVisibility(View.GONE);
        ((TextView) mInflate.findViewById(R.id.textViewTitle)).setText("我要卖车");
        textViewRight.setText("卖车流程");
    }

    @Override
    protected void setListeners() {
        textViewRight.setOnClickListener(this);
        mInflate.findViewById(R.id.viewSellCity).setOnClickListener(this);
        mInflate.findViewById(R.id.viewChePiaCity).setOnClickListener(this);
        mInflate.findViewById(R.id.viewCarName).setOnClickListener(this);
        mInflate.findViewById(R.id.viewTime).setOnClickListener(this);
        mInflate.findViewById(R.id.viewLiCheng).setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.viewLiCheng:
                LayoutInflater inflater = LayoutInflater.from(getActivity());
                View dialog_chan_pin = inflater.inflate(R.layout.dialog_li_cheng, null);
                AlertDialog xinZengDialog = new AlertDialog.Builder(getActivity(), R.style.dialog)
                        .setView(dialog_chan_pin)
                        .create();
                xinZengDialog.show();
                Window dialogWindow = xinZengDialog.getWindow();
                dialogWindow.setGravity(Gravity.BOTTOM);
                dialogWindow.setWindowAnimations(R.style.dialogFenXiang);
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                DisplayMetrics d = getActivity().getResources().getDisplayMetrics(); // 获取屏幕宽、高用
                lp.width = (int) (d.widthPixels * 1); // 高度设置为屏幕的0.6
                dialogWindow.setAttributes(lp);
                break;
            case R.id.viewTime:
                Calendar c = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
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
                intent.setClass(getActivity(), PinPaiXCActivity.class);
                intent.putExtra(Constant.IntentKey.NAME,true);
                startActivityForResult(intent, Constant.RequestResultCode.PIN_PAI);
                break;
            case R.id.viewChePiaCity:
                intent.setClass(getActivity(), ChengShiXZActivity.class);
                startActivityForResult(intent, Constant.RequestResultCode.CITY01);
                break;
            case R.id.viewSellCity:
                intent.setClass(getActivity(), ChengShiXZActivity.class);
                startActivityForResult(intent, Constant.RequestResultCode.CITY);
                break;
            case R.id.textViewRight:
                intent.setClass(getActivity(), WebActivity.class);
                intent.putExtra(Constant.IntentKey.TITLE, "卖车流程");
                intent.putExtra(Constant.IntentKey.URL, Constant.Url.MAI_CHE_GUI_ZE);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
