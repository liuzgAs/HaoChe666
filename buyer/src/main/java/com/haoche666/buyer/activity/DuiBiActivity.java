package com.haoche666.buyer.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haoche666.buyer.R;
import com.haoche666.buyer.base.MyDialog;
import com.haoche666.buyer.base.ZjbBaseActivity;
import com.haoche666.buyer.constant.Constant;
import com.haoche666.buyer.customview.MyScrollView;
import com.haoche666.buyer.model.AttentionGetattention;
import com.haoche666.buyer.model.AttentionGetcontrastinfo;
import com.haoche666.buyer.model.DuiBICanShu;
import com.haoche666.buyer.model.DuiBi;
import com.haoche666.buyer.util.ApiClient;
import com.haoche666.buyer.viewholder.DuiBiInfoViewHolder;
import com.haoche666.buyer.viewholder.DuiBiViewHolder;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;

import java.util.ArrayList;
import java.util.List;

import huisedebi.zjb.mylibrary.util.DpUtils;
import huisedebi.zjb.mylibrary.util.GsonUtils;
import huisedebi.zjb.mylibrary.util.LogUtil;

public class DuiBiActivity extends ZjbBaseActivity implements View.OnClickListener {

    private TextView textViewRight;
    private EasyRecyclerView recyclerView;
    private EasyRecyclerView recyclerViewInfo;
    private RecyclerArrayAdapter<AttentionGetcontrastinfo.DataBean> adapter;
    private RecyclerArrayAdapter<AttentionGetcontrastinfo.DataBean> adapterInfo;
    private MyScrollView scrollView;
    private TextView textCeiling;
    private TextView[] textCeillingArr = new TextView[2];
    private float celilingHeight;
    private View viewCeilling;
    private List<AttentionGetattention.DataBean> duiBiAllData;
    private TextView[] textZhuYao = new TextView[6];
    private int[] textZhuYaoId = new int[]{
            R.id.text0000,
            R.id.text0001,
            R.id.text0002,
            R.id.text0003,
            R.id.text0004,
            R.id.text0005,
    };
    private TextView[] textJiBen = new TextView[29];
    private int[] textJiBenId = new int[]{
            R.id.text0100,
            R.id.text0101,
            R.id.text0102,
            R.id.text0103,
            R.id.text0104,
            R.id.text0105,
            R.id.text0106,
            R.id.text0107,
            R.id.text0108,
            R.id.text0109,
            R.id.text0110,
            R.id.text0111,
            R.id.text0112,
            R.id.text0113,
            R.id.text0114,
            R.id.text0115,
            R.id.text0116,
            R.id.text0117,
            R.id.text0118,
            R.id.text0119,
            R.id.text0120,
            R.id.text0121,
            R.id.text0122,
            R.id.text0123,
            R.id.text0124,
            R.id.text0125,
            R.id.text0126,
            R.id.text0127,
            R.id.text0128,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dui_bi);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {
        Intent intent = getIntent();
        DuiBi duiBi = (DuiBi) intent.getSerializableExtra(Constant.IntentKey.BEAN);
        duiBiAllData = duiBi.getAllData();
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void findID() {
        textViewRight = (TextView) findViewById(R.id.textViewRight);
        recyclerView = (EasyRecyclerView) findViewById(R.id.recyclerView);
        recyclerViewInfo = (EasyRecyclerView) findViewById(R.id.recyclerViewInfo);
        scrollView = (MyScrollView) findViewById(R.id.scrollView);
        textCeiling = (TextView) findViewById(R.id.textCeiling);
        textCeillingArr[0] = (TextView) findViewById(R.id.textCeiling01);
        textCeillingArr[1] = (TextView) findViewById(R.id.textCeiling02);
//        textCeillingArr[2] = (TextView) findViewById(R.id.textCeiling03);
//        textCeillingArr[3] = (TextView) findViewById(R.id.textCeiling04);
//        textCeillingArr[4] = (TextView) findViewById(R.id.textCeiling05);
//        textCeillingArr[5] = (TextView) findViewById(R.id.textCeiling06);
//        textCeillingArr[6] = (TextView) findViewById(R.id.textCeiling07);
//        textCeillingArr[7] = (TextView) findViewById(R.id.textCeiling08);
        celilingHeight = DpUtils.convertDpToPixel(30, DuiBiActivity.this);
        viewCeilling = findViewById(R.id.viewCeilling);
        for (int i = 0; i < textJiBen.length; i++) {
            textJiBen[i] = (TextView) findViewById(textJiBenId[i]);
        }
        for (int i = 0; i < textZhuYao.length; i++) {
            textZhuYao[i] = (TextView) findViewById(textZhuYaoId[i]);
        }
    }

    @Override
    protected void initViews() {
        ((TextView) findViewById(R.id.textViewTitle)).setText("车辆对比");
        textViewRight.setText("隐藏相同");
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter = new RecyclerArrayAdapter<AttentionGetcontrastinfo.DataBean>(DuiBiActivity.this) {

            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_duibi;
                return new DuiBiViewHolder(parent, layout);
            }
        });
        recyclerView.addItemDecoration(new SpaceDecoration((int) DpUtils.convertDpToPixel(1, this)));

        recyclerViewInfo.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewInfo.setAdapter(adapterInfo = new RecyclerArrayAdapter<AttentionGetcontrastinfo.DataBean>(DuiBiActivity.this) {

            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_duibi_info;
                return new DuiBiInfoViewHolder(parent, layout);
            }
        });
        recyclerViewInfo.addItemDecoration(new SpaceDecoration((int) DpUtils.convertDpToPixel(1, this)));
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
        recyclerView.getRecyclerView().addOnScrollListener(new MyOnScrollListener());
        recyclerViewInfo.getRecyclerView().addOnScrollListener(new MyInfoOnScrollListener());
        scrollView.setOnScrollChangedListener(new MyScrollView.OnScrollChangedListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onScrollChanged(int i) {
//                if (i>textCeillingArr[7].getY()){
//                    textCeiling.setText(getResources().getText(R.string.duiBiTitle08));
//                    return;
//                }
//                if (i>textCeillingArr[6].getY()){
//                    textCeiling.setText(getResources().getText(R.string.duiBiTitle07));
//                    setCeiling(i,7);
//                    return;
//                }
//                if (i>textCeillingArr[5].getY()){
//                    textCeiling.setText(getResources().getText(R.string.duiBiTitle06));
//                    setCeiling(i,6);
//                    return;
//                }
//                if (i>textCeillingArr[4].getY()){
//                    textCeiling.setText(getResources().getText(R.string.duiBiTitle05));
//                    setCeiling(i,5);
//                    return;
//                }
//                if (i>textCeillingArr[3].getY()){
//                    textCeiling.setText(getResources().getText(R.string.duiBiTitle04));
//                    setCeiling(i,4);
//                    return;
//                }
//                if (i>textCeillingArr[2].getY()){
//                    textCeiling.setText(getResources().getText(R.string.duiBiTitle03));
//                    setCeiling(i,3);
//                    return;
//                }
//                if (i > textCeillingArr[1].getY()) {
//                    textCeiling.setText(getResources().getText(R.string.duiBiTitle02));
//                    setCeiling(i, 2);
//                    return;
//                }
                if (i > textCeillingArr[0].getY()) {
                    textCeiling.setText(getResources().getText(R.string.duiBiTitle01));
                    setCeiling(i, 1);
                    return;
                }
            }

            private void setCeiling(int i, int position) {
                if (i > (textCeillingArr[position].getY() - celilingHeight) && i < textCeillingArr[position].getY()) {
                    viewCeilling.setTranslationY(-1 * (i - (textCeillingArr[position].getY() - celilingHeight)));
                } else {
                    viewCeilling.setTranslationY(0);
                }
            }
        });
        textViewRight.setOnClickListener(this);
    }

    /**
     * 第一个被拖拽
     */
    private boolean isDrag = false;
    /**
     * 第二个被拖拽
     */
    private boolean isInfoDrag = false;

    /**
     * @创建者 CSDN_LQR
     * @描述 实现一个RecyclerView.OnScrollListener的子类，当RecyclerView空闲时取消自身的滚动监听
     */
    public class MyOnScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                isDrag = true;
                isInfoDrag = false;
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (!isInfoDrag) {
                recyclerViewInfo.getRecyclerView().scrollBy(dx, dy);
            }
        }
    }

    /**
     * @创建者 CSDN_LQR
     * @描述 实现一个RecyclerView.OnScrollListener的子类，当RecyclerView空闲时取消自身的滚动监听
     */
    public class MyInfoOnScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView1, int newState) {
            super.onScrollStateChanged(recyclerView1, newState);
            if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                isDrag = false;
                isInfoDrag = true;
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView1, int dx, int dy) {
            super.onScrolled(recyclerView1, dx, dy);
            if (!isDrag) {
                recyclerView.getRecyclerView().scrollBy(dx, dy);
            }
        }
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private String getOkObject() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < duiBiAllData.size(); i++) {
            list.add(duiBiAllData.get(i).getId());
        }
        DuiBICanShu duiBICanShu = new DuiBICanShu(1, "android", userInfo.getUid(), tokenTime, list);
        return GsonUtils.parseObject(duiBICanShu);
    }

    @Override
    protected void initData() {
        String url = Constant.HOST + Constant.Url.ATTENTION_GETCONTRASTINFO;
        ApiClient.postJson(this, url, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("对比参数", s);
//                s = "{\n" +
//                        "\t\"data\":[\n" +
//                        "\t\t{\n" +
//                        "\t\t\t\"img\":\"https://www.haoche666.com/Uploads/attachment/20180104/1dddf19ee9419c1694eb9697072e5660.jpg\",\n" +
//                        "\t\t\t\"params_v\":[\n" +
//                        "\t\t\t\t[\n" +
//                        "\t\t\t\t\t\"4.00万\",\n" +
//                        "\t\t\t\t\t\"9.58万\",\n" +
//                        "\t\t\t\t\t\"9.50万公里\",\n" +
//                        "\t\t\t\t\t\"3年\",\n" +
//                        "\t\t\t\t\t\"北京\",\n" +
//                        "\t\t\t\t\t\"现代\"\n" +
//                        "\t\t\t\t],\n" +
//                        "\t\t\t\t[\n" +
//                        "\t\t\t\t\t\"北京现代\",\n" +
//                        "\t\t\t\t\t\"合资\",\n" +
//                        "\t\t\t\t\t\"领动\",\n" +
//                        "\t\t\t\t\t\"领动(16/03-)\",\n" +
//                        "\t\t\t\t\t\"XDA1AK01\",\n" +
//                        "\t\t\t\t\t\"AP_4028b2b653a2f3280153bba629682ab6\",\n" +
//                        "\t\t\t\t\t\"2016款 北京现代 领动 三厢 1.6L 手自一体 智炫精英型 (BH7167TAV)\",\n" +
//                        "\t\t\t\t\t\"北京现代BH7167TAV轿车\",\n" +
//                        "\t\t\t\t\t\"1.591\",\n" +
//                        "\t\t\t\t\t\"2016\",\n" +
//                        "\t\t\t\t\t\"手自一体\",\n" +
//                        "\t\t\t\t\t\"直喷\",\n" +
//                        "\t\t\t\t\t\"直喷\",\n" +
//                        "\t\t\t\t\t\"北京现代G4FD\",\n" +
//                        "\t\t\t\t\t\"前置前驱\",\n" +
//                        "\t\t\t\t\t\"手自一体 智炫·精英型 GLX 国Ⅴ\",\n" +
//                        "\t\t\t\t\t\"1\",\n" +
//                        "\t\t\t\t\t\"119800\",\n" +
//                        "\t\t\t\t\t\"103800\",\n" +
//                        "\t\t\t\t\t\"5\",\n" +
//                        "\t\t\t\t\t\"智炫精英型\",\n" +
//                        "\t\t\t\t\t\"201603\",\n" +
//                        "\t\t\t\t\t\"4610*1800*1450\",\n" +
//                        "\t\t\t\t\t\"2700\",\n" +
//                        "\t\t\t\t\t\"6\",\n" +
//                        "\t\t\t\t\t\"1317\",\n" +
//                        "\t\t\t\t\t\"95.3\",\n" +
//                        "\t\t\t\t\t\"国五\",\n" +
//                        "\t\t\t\t\t\"优雅白\"\n" +
//                        "\t\t\t\t]\n" +
//                        "\t\t\t],\n" +
//                        "\t\t\t\"title\":\"2016款 北京现代 领动 三厢 1.6L 手自一体 智炫精英型 (BH7167TAV)\"\n" +
//                        "\t\t},\n" +
//                        "\t\t{\n" +
//                        "\t\t\t\"img\":\"https://www.haoche666.com/Uploads/attachment/20180104/1dddf19ee9419c1694eb9697072e5660.jpg\",\n" +
//                        "\t\t\t\"params_v\":[\n" +
//                        "\t\t\t\t[\n" +
//                        "\t\t\t\t\t\"4.00万\",\n" +
//                        "\t\t\t\t\t\"9.58万\",\n" +
//                        "\t\t\t\t\t\"9.50万公里\",\n" +
//                        "\t\t\t\t\t\"3年\",\n" +
//                        "\t\t\t\t\t\"北京\",\n" +
//                        "\t\t\t\t\t\"现代\"\n" +
//                        "\t\t\t\t],\n" +
//                        "\t\t\t\t[\n" +
//                        "\t\t\t\t\t\"北京现代\",\n" +
//                        "\t\t\t\t\t\"合资\",\n" +
//                        "\t\t\t\t\t\"领动\",\n" +
//                        "\t\t\t\t\t\"领动(16/03-)\",\n" +
//                        "\t\t\t\t\t\"XDA1AK01\",\n" +
//                        "\t\t\t\t\t\"AP_4028b2b653a2f3280153bba629682ab6\",\n" +
//                        "\t\t\t\t\t\"2016款 北京现代 领动 三厢 1.6L 手自一体 智炫精英型 (BH7167TAV)\",\n" +
//                        "\t\t\t\t\t\"北京现代BH7167TAV轿车\",\n" +
//                        "\t\t\t\t\t\"1.591\",\n" +
//                        "\t\t\t\t\t\"2016\",\n" +
//                        "\t\t\t\t\t\"手自一体\",\n" +
//                        "\t\t\t\t\t\"直喷\",\n" +
//                        "\t\t\t\t\t\"直喷\",\n" +
//                        "\t\t\t\t\t\"北京现代G4FD\",\n" +
//                        "\t\t\t\t\t\"前置前驱\",\n" +
//                        "\t\t\t\t\t\"手自一体 智炫·精英型 GLX 国Ⅴ\",\n" +
//                        "\t\t\t\t\t\"1\",\n" +
//                        "\t\t\t\t\t\"119800\",\n" +
//                        "\t\t\t\t\t\"103800\",\n" +
//                        "\t\t\t\t\t\"5\",\n" +
//                        "\t\t\t\t\t\"智炫精英型\",\n" +
//                        "\t\t\t\t\t\"201603\",\n" +
//                        "\t\t\t\t\t\"4610*1800*1450\",\n" +
//                        "\t\t\t\t\t\"2700\",\n" +
//                        "\t\t\t\t\t\"6\",\n" +
//                        "\t\t\t\t\t\"1317\",\n" +
//                        "\t\t\t\t\t\"95.3\",\n" +
//                        "\t\t\t\t\t\"国五\",\n" +
//                        "\t\t\t\t\t\"优雅白\"\n" +
//                        "\t\t\t\t]\n" +
//                        "\t\t\t],\n" +
//                        "\t\t\t\"title\":\"2016款 北京现代 领动 三厢 1.6L 手自一体 智炫精英型 (BH7167TAV)\"\n" +
//                        "\t\t}\n" +
//                        "\t],\n" +
//                        "\t\"info\":\"获取成功！\",\n" +
//                        "\t\"left_nav\":[\n" +
//                        "\t\t{\n" +
//                        "\t\t\t\"item_name\":\"主要参数\",\n" +
//                        "\t\t\t\"item_v\":[\n" +
//                        "\t\t\t\t\"车主报价\",\n" +
//                        "\t\t\t\t\"新车购置价\",\n" +
//                        "\t\t\t\t\"行驶里程\",\n" +
//                        "\t\t\t\t\"车龄\",\n" +
//                        "\t\t\t\t\"车牌城市\",\n" +
//                        "\t\t\t\t\"车牌号\"\n" +
//                        "\t\t\t]\n" +
//                        "\t\t},\n" +
//                        "\t\t{\n" +
//                        "\t\t\t\"item_name\":\"基本参数\",\n" +
//                        "\t\t\t\"item_v\":[\n" +
//                        "\t\t\t\t\"品牌名称\",\n" +
//                        "\t\t\t\t\"国产/进口\",\n" +
//                        "\t\t\t\t\"车系\",\n" +
//                        "\t\t\t\t\"车组名称\",\n" +
//                        "\t\t\t\t\"车组编码\",\n" +
//                        "\t\t\t\t\"车型代码，唯一标识\",\n" +
//                        "\t\t\t\t\"车型名称\",\n" +
//                        "\t\t\t\t\"车型俗称\",\n" +
//                        "\t\t\t\t\"排量\",\n" +
//                        "\t\t\t\t\"年款\",\n" +
//                        "\t\t\t\t\"变速箱类型\",\n" +
//                        "\t\t\t\t\"供油方式\",\n" +
//                        "\t\t\t\t\"燃油类型\",\n" +
//                        "\t\t\t\t\"发动机型号\",\n" +
//                        "\t\t\t\t\"驱动形式\",\n" +
//                        "\t\t\t\t\"备注\",\n" +
//                        "\t\t\t\t\"是否有更多配置\",\n" +
//                        "\t\t\t\t\"厂商指导价\",\n" +
//                        "\t\t\t\t\"新车购置价\",\n" +
//                        "\t\t\t\t\"座位数\",\n" +
//                        "\t\t\t\t\"配置等级\",\n" +
//                        "\t\t\t\t\"上市年份\",\n" +
//                        "\t\t\t\t\"外形尺寸\",\n" +
//                        "\t\t\t\t\"轴距\",\n" +
//                        "\t\t\t\t\"变速器档数\",\n" +
//                        "\t\t\t\t\"整备质量（千克）\",\n" +
//                        "\t\t\t\t\"功率\",\n" +
//                        "\t\t\t\t\"排放标准\",\n" +
//                        "\t\t\t\t\"车身颜色\"\n" +
//                        "\t\t\t]\n" +
//                        "\t\t}\n" +
//                        "\t],\n" +
//                        "\t\"status\":1\n" +
//                        "}";
//                try {
                    AttentionGetcontrastinfo attentionGetcontrastinfo = GsonUtils.parseJSON(s, AttentionGetcontrastinfo.class);
                    if (attentionGetcontrastinfo.getStatus() == 1) {
                        AttentionGetcontrastinfo.LeftNavBean leftNavBean0 = attentionGetcontrastinfo.getLeft_nav().get(0);
                        if (leftNavBean0 != null) {
                            textCeillingArr[0].setText(leftNavBean0.getItem_name());
                            for (int i = 0; i < leftNavBean0.getItem_v().size(); i++) {
                                textZhuYao[i].setText(leftNavBean0.getItem_v().get(i));
                            }
                        } else {
                            for (int i = 0; i < leftNavBean0.getItem_v().size(); i++) {
                                textZhuYao[i].setVisibility(View.GONE);
                            }
                            textCeillingArr[0].setVisibility(View.GONE);
                        }
                        if (attentionGetcontrastinfo.getLeft_nav().size()>1){
                            AttentionGetcontrastinfo.LeftNavBean leftNavBean1 = attentionGetcontrastinfo.getLeft_nav().get(1);
                            if (attentionGetcontrastinfo.getLeft_nav().get(1) != null) {
                                for (int i = 0; i < leftNavBean1.getItem_v().size(); i++) {
                                    textJiBen[i].setText(leftNavBean1.getItem_v().get(i));
                                }
                                textCeillingArr[1].setText(attentionGetcontrastinfo.getLeft_nav().get(1).getItem_name());
                            } else {
                                for (int i = 0; i < leftNavBean1.getItem_v().size(); i++) {
                                    textJiBen[i].setVisibility(View.GONE);
                                }
                                textCeillingArr[1].setVisibility(View.GONE);
                            }
                        }else {
                            for (int i = 0; i < textJiBen.length; i++) {
                                textJiBen[i].setVisibility(View.GONE);
                            }
                            textCeillingArr[1].setVisibility(View.GONE);
                        }
                        List<AttentionGetcontrastinfo.DataBean> dataBeanList = attentionGetcontrastinfo.getData();
                        for (int i = 0; i < dataBeanList.size(); i++) {
                            List<List<Boolean>> list = new ArrayList<>();
                            for (int j = 0; j < dataBeanList.get(i).getParams_v().size(); j++) {
                                List<Boolean> booleanList = new ArrayList<>();
                                for (int k = 0; k < dataBeanList.get(i).getParams_v().get(j).size(); k++) {
                                    booleanList.add(true);
                                }
                                list.add(booleanList);
                            }
                            dataBeanList.get(i).setParams_vBoolean(list);
                        }
                        adapter.clear();
                        adapter.addAll(dataBeanList);
                        adapterInfo.addAll(dataBeanList);
                    } else if (attentionGetcontrastinfo.getStatus() == 3) {
                        MyDialog.showReLoginDialog(DuiBiActivity.this);
                    } else {
                        showError(attentionGetcontrastinfo.getInfo());
                    }
//                } catch (Exception e) {
//                    showError("数据出错");
//                }
            }

            @Override
            public void onError() {
                showError("网络出错");
            }

            /**
             * 错误显示
             * @param msg
             */
            private void showError(String msg) {
                View viewLoader = LayoutInflater.from(DuiBiActivity.this).inflate(R.layout.view_loaderror, null);
                TextView textMsg = viewLoader.findViewById(R.id.textMsg);
                textMsg.setText(msg);
                viewLoader.findViewById(R.id.buttonReLoad).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        recyclerView.showProgress();
                        initData();
                    }
                });
                recyclerView.setErrorView(viewLoader);
                recyclerView.showError();
            }
        });
    }


    private boolean isHide = false;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textViewRight:
                if (!isHide) {
                    textViewRight.setText("显示所有");
                    for (int i = 0; i < adapterInfo.getAllData().size() - 1; i++) {
                        AttentionGetcontrastinfo.DataBean dataBean = adapterInfo.getAllData().get(i);
                        AttentionGetcontrastinfo.DataBean dataBean01 = adapterInfo.getAllData().get(i + 1);
                        List<String> strings00 = dataBean.getParams_v().get(0);
                        List<String> strings10 = dataBean01.getParams_v().get(0);
                        for (int j = 0; j < strings00.size(); j++) {
                            if (TextUtils.equals(strings00.get(i), strings10.get(i))) {
                                if (i == adapterInfo.getAllData().size() - 2) {
                                    textZhuYao[j].setVisibility(View.GONE);
                                    adapterInfo.getItem(i).getParams_vBoolean().get(0).set(j, false);
                                }
                            }
                        }
                        if (dataBean.getParams_v().size()>1&&dataBean01.getParams_v().size()>1){
                            List<String> strings01 = dataBean.getParams_v().get(1);
                            List<String> strings11 = dataBean01.getParams_v().get(1);
                            for (int j = 0; j < strings01.size(); j++) {
                                if (TextUtils.equals(strings01.get(i), strings11.get(i))) {
                                    if (i == adapterInfo.getAllData().size() - 2) {
                                        textJiBen[j].setVisibility(View.GONE);
                                        adapterInfo.getItem(i).getParams_vBoolean().get(1).set(j, false);
                                    }
                                }
                            }
                        }
                    }
                    for (int i = 1; i < adapterInfo.getAllData().size(); i++) {
                        for (int j = 0; j < adapterInfo.getItem(0).getParams_vBoolean().size(); j++) {
                            for (int k = 0; k < adapterInfo.getItem(0).getParams_vBoolean().get(0).size(); k++) {
                                adapterInfo.getItem(i).getParams_vBoolean().get(0).set(k, adapterInfo.getItem(0).getParams_vBoolean().get(0).get(k));
                            }
                            if (adapterInfo.getItem(0).getParams_vBoolean().size()>1){
                                for (int k = 0; k < adapterInfo.getItem(0).getParams_vBoolean().get(1).size(); k++) {
                                    adapterInfo.getItem(i).getParams_vBoolean().get(1).set(k, adapterInfo.getItem(0).getParams_vBoolean().get(1).get(k));
                                }
                            }
                        }
                    }
                    adapterInfo.notifyDataSetChanged();
                } else {
                    textViewRight.setText("隐藏相同");
                    for (int i = 0; i < adapterInfo.getAllData().size(); i++) {
                        List<List<Boolean>> list = new ArrayList<>();
                        for (int j = 0; j < adapterInfo.getAllData().get(i).getParams_v().size(); j++) {
                            List<Boolean> booleanList = new ArrayList<>();
                            for (int k = 0; k < adapterInfo.getAllData().get(i).getParams_v().get(j).size(); k++) {
                                booleanList.add(true);
                            }
                            list.add(booleanList);
                        }
                        adapterInfo.getAllData().get(i).setParams_vBoolean(list);
                    }
                    for (int i = 0; i < textZhuYao.length; i++) {
                        textZhuYao[i].setVisibility(View.VISIBLE);
                    }
                    if (adapterInfo.getItem(0).getParams_vBoolean().size()>1){
                        for (int i = 0; i < textJiBen.length; i++) {
                            textJiBen[i].setVisibility(View.VISIBLE);
                        }
                    }
                }
                adapterInfo.notifyDataSetChanged();
                isHide = !isHide;
                break;
            case R.id.imageBack:
                finish();
                break;
            default:
                break;
        }
    }
}
