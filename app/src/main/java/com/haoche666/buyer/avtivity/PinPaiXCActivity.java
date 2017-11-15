package com.haoche666.buyer.avtivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haoche666.buyer.R;
import com.haoche666.buyer.base.ZjbBaseActivity;
import com.haoche666.buyer.constant.Constant;
import com.haoche666.buyer.customview.SideLetterBar;
import com.haoche666.buyer.model.OkObject;
import com.haoche666.buyer.model.PinPaiBean;
import com.haoche666.buyer.model.PinPaiXC;
import com.haoche666.buyer.util.ApiClient;
import com.haoche666.buyer.util.GsonUtils;
import com.haoche666.buyer.util.LogUtil;
import com.haoche666.buyer.util.ScreenUtils;
import com.haoche666.buyer.viewholder.PinPaiXCViewHolder;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.jude.easyrecyclerview.decoration.StickyHeaderDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Administrator
 */
public class PinPaiXCActivity extends ZjbBaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private EasyRecyclerView recyclerViewRight;
    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<PinPaiBean> adapter;
    private SideLetterBar mLetterBar;
    private List<PinPaiBean> pinPaiBeanList;
    private DrawerLayout drawerLayout;
    private List<Integer> letterList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_pai_xc);
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
        recyclerViewRight = (EasyRecyclerView) findViewById(R.id.recyclerViewRight);
        recyclerView = (EasyRecyclerView) findViewById(R.id.recyclerView);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
    }

    @Override
    protected void initViews() {
        ((TextView) findViewById(R.id.textViewTitle)).setText("品牌选车");
        ViewGroup.LayoutParams layoutParams = recyclerViewRight.getLayoutParams();
        int screenWidth = ScreenUtils.getScreenWidth(this);
        layoutParams.width = (int) ((float) screenWidth / 4 * 3);
        recyclerViewRight.setLayoutParams(layoutParams);
        initRecycler();
        TextView overlay = (TextView) findViewById(R.id.tv_letter_overlay);
        mLetterBar = (SideLetterBar) findViewById(R.id.side_letter_bar);
        mLetterBar.setOverlay(overlay);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            }

            @Override
            public void onDrawerStateChanged(int newState) {
            }
        });
        mLetterBar.setOnLetterChangedListener(new SideLetterBar.OnLetterChangedListener() {
            @Override
            public void onLetterChanged(String letter, int position) {
                LogUtil.LogShitou("PinPaiXCActivity--onLetterChanged position", "" + position);
                if (position > 1) {
                    RecyclerView recyclerView1 = recyclerView.getRecyclerView();
                    LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView1.getLayoutManager();
                    layoutManager.scrollToPositionWithOffset(letterList.get(position - 2)+1,0);
                } else {
                    recyclerView.scrollToPosition(0);
                }
            }
        });
    }

    @Override
    protected void initData() {
        onRefresh();
    }

    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) getResources().getDimension(R.dimen.line_width), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setRefreshingColorResources(R.color.basic_color);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<PinPaiBean>(PinPaiXCActivity.this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_pinpai_xc;
                return new PinPaiXCViewHolder(parent, layout);
            }
        });
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
            }
        });
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                View view = LayoutInflater.from(PinPaiXCActivity.this).inflate(R.layout.header_pinpai_xc, null);
                return view;
            }

            @Override
            public void onBindView(View headerView) {

            }
        });
        /**StickyHeader*/
        StickyHeaderDecoration decoration = new StickyHeaderDecoration(new StickyHeaderAdapter(this));
        decoration.setIncludeHeader(false);
        recyclerView.addItemDecoration(decoration);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
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
        String url = Constant.Url.CHE_XUN;
        HashMap<String, String> params = new HashMap<>();
        return new OkObject(params, url);
    }


    @Override
    public void onRefresh() {
        ApiClient.post(this, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("品牌选车树结构", s);
                try {
                    pinPaiBeanList = new ArrayList<>();
                    pinPaiBeanList.clear();
                    letterList.clear();
                    PinPaiXC pinPaiXC = GsonUtils.parseJSON(s, PinPaiXC.class);
                    List<PinPaiBean> pinPaiXCA = pinPaiXC.getA();
                    List<PinPaiBean> pinPaiXCB = pinPaiXC.getB();
                    List<PinPaiBean> pinPaiXCC = pinPaiXC.getC();
                    List<PinPaiBean> pinPaiXCD = pinPaiXC.getD();
                    List<PinPaiBean> pinPaiXCF = pinPaiXC.getF();
                    List<PinPaiBean> pinPaiXCG = pinPaiXC.getG();
                    List<PinPaiBean> pinPaiXCH = pinPaiXC.getH();
                    List<PinPaiBean> pinPaiXCI = pinPaiXC.getI();
                    List<PinPaiBean> pinPaiXCJ = pinPaiXC.getJ();
                    List<PinPaiBean> pinPaiXCK = pinPaiXC.getK();
                    List<PinPaiBean> pinPaiXCL = pinPaiXC.getL();
                    List<PinPaiBean> pinPaiXCM = pinPaiXC.getM();
                    List<PinPaiBean> pinPaiXCN = pinPaiXC.getN();
                    List<PinPaiBean> pinPaiXCO = pinPaiXC.getO();
                    List<PinPaiBean> pinPaiXCP = pinPaiXC.getP();
                    List<PinPaiBean> pinPaiXCQ = pinPaiXC.getQ();
                    List<PinPaiBean> pinPaiXCR = pinPaiXC.getR();
                    List<PinPaiBean> pinPaiXCS = pinPaiXC.getS();
                    List<PinPaiBean> pinPaiXCT = pinPaiXC.getT();
                    List<PinPaiBean> pinPaiXCW = pinPaiXC.getW();
                    List<PinPaiBean> pinPaiXCX = pinPaiXC.getX();
                    List<PinPaiBean> pinPaiXCY = pinPaiXC.getY();
                    List<PinPaiBean> pinPaiXCZ = pinPaiXC.getZ();
                    letterList.add(pinPaiBeanList.size());
                    for (int i = 0; i < pinPaiXCA.size(); i++) {
                        pinPaiXCA.get(i).setIndex(pinPaiBeanList.size());
                        pinPaiXCA.get(i).setLetter("A");
                    }
                    pinPaiBeanList.addAll(pinPaiXCA);
                    letterList.add(pinPaiBeanList.size());
                    for (int i = 0; i < pinPaiXCB.size(); i++) {
                        pinPaiXCB.get(i).setIndex(pinPaiBeanList.size());
                        pinPaiXCB.get(i).setLetter("B");
                    }
                    pinPaiBeanList.addAll(pinPaiXCB);
                    letterList.add(pinPaiBeanList.size());
                    for (int i = 0; i < pinPaiXCC.size(); i++) {
                        pinPaiXCC.get(i).setIndex(pinPaiBeanList.size());
                        pinPaiXCC.get(i).setLetter("C");
                    }
                    pinPaiBeanList.addAll(pinPaiXCC);
                    letterList.add(pinPaiBeanList.size());
                    for (int i = 0; i < pinPaiXCD.size(); i++) {
                        pinPaiXCD.get(i).setIndex(pinPaiBeanList.size());
                        pinPaiXCD.get(i).setLetter("D");
                    }
                    pinPaiBeanList.addAll(pinPaiXCD);
                    letterList.add(pinPaiBeanList.size());
                    for (int i = 0; i < pinPaiXCF.size(); i++) {
                        pinPaiXCF.get(i).setIndex(pinPaiBeanList.size());
                        pinPaiXCF.get(i).setLetter("F");
                    }
                    pinPaiBeanList.addAll(pinPaiXCF);
                    letterList.add(pinPaiBeanList.size());
                    for (int i = 0; i < pinPaiXCG.size(); i++) {
                        pinPaiXCG.get(i).setIndex(pinPaiBeanList.size());
                        pinPaiXCG.get(i).setLetter("G");
                    }
                    pinPaiBeanList.addAll(pinPaiXCG);
                    letterList.add(pinPaiBeanList.size());
                    for (int i = 0; i < pinPaiXCH.size(); i++) {
                        pinPaiXCH.get(i).setIndex(pinPaiBeanList.size());
                        pinPaiXCH.get(i).setLetter("H");
                    }
                    pinPaiBeanList.addAll(pinPaiXCH);
                    letterList.add(pinPaiBeanList.size());
                    for (int i = 0; i < pinPaiXCI.size(); i++) {
                        pinPaiXCI.get(i).setIndex(pinPaiBeanList.size());
                        pinPaiXCI.get(i).setLetter("I");
                    }
                    pinPaiBeanList.addAll(pinPaiXCI);
                    letterList.add(pinPaiBeanList.size());
                    for (int i = 0; i < pinPaiXCJ.size(); i++) {
                        pinPaiXCJ.get(i).setIndex(pinPaiBeanList.size());
                        pinPaiXCJ.get(i).setLetter("J");
                    }
                    pinPaiBeanList.addAll(pinPaiXCJ);
                    letterList.add(pinPaiBeanList.size());
                    for (int i = 0; i < pinPaiXCK.size(); i++) {
                        pinPaiXCK.get(i).setIndex(pinPaiBeanList.size());
                        pinPaiXCK.get(i).setLetter("K");
                    }
                    pinPaiBeanList.addAll(pinPaiXCK);
                    letterList.add(pinPaiBeanList.size());
                    for (int i = 0; i < pinPaiXCL.size(); i++) {
                        pinPaiXCL.get(i).setIndex(pinPaiBeanList.size());
                        pinPaiXCL.get(i).setLetter("L");
                    }
                    pinPaiBeanList.addAll(pinPaiXCL);
                    letterList.add(pinPaiBeanList.size());
                    for (int i = 0; i < pinPaiXCM.size(); i++) {
                        pinPaiXCM.get(i).setIndex(pinPaiBeanList.size());
                        pinPaiXCM.get(i).setLetter("M");
                    }
                    pinPaiBeanList.addAll(pinPaiXCM);
                    letterList.add(pinPaiBeanList.size());
                    for (int i = 0; i < pinPaiXCN.size(); i++) {
                        pinPaiXCN.get(i).setIndex(pinPaiBeanList.size());
                        pinPaiXCN.get(i).setLetter("N");
                    }
                    pinPaiBeanList.addAll(pinPaiXCN);
                    letterList.add(pinPaiBeanList.size());
                    for (int i = 0; i < pinPaiXCO.size(); i++) {
                        pinPaiXCO.get(i).setIndex(pinPaiBeanList.size());
                        pinPaiXCO.get(i).setLetter("O");
                    }
                    pinPaiBeanList.addAll(pinPaiXCO);
                    letterList.add(pinPaiBeanList.size());
                    for (int i = 0; i < pinPaiXCP.size(); i++) {
                        pinPaiXCP.get(i).setIndex(pinPaiBeanList.size());
                        pinPaiXCP.get(i).setLetter("P");
                    }
                    pinPaiBeanList.addAll(pinPaiXCP);
                    letterList.add(pinPaiBeanList.size());
                    for (int i = 0; i < pinPaiXCQ.size(); i++) {
                        pinPaiXCQ.get(i).setIndex(pinPaiBeanList.size());
                        pinPaiXCQ.get(i).setLetter("Q");
                    }
                    pinPaiBeanList.addAll(pinPaiXCQ);
                    letterList.add(pinPaiBeanList.size());
                    for (int i = 0; i < pinPaiXCR.size(); i++) {
                        pinPaiXCR.get(i).setIndex(pinPaiBeanList.size());
                        pinPaiXCR.get(i).setLetter("R");
                    }
                    pinPaiBeanList.addAll(pinPaiXCR);
                    letterList.add(pinPaiBeanList.size());
                    for (int i = 0; i < pinPaiXCS.size(); i++) {
                        pinPaiXCS.get(i).setIndex(pinPaiBeanList.size());
                        pinPaiXCS.get(i).setLetter("S");
                    }
                    pinPaiBeanList.addAll(pinPaiXCS);
                    letterList.add(pinPaiBeanList.size());
                    for (int i = 0; i < pinPaiXCT.size(); i++) {
                        pinPaiXCT.get(i).setIndex(pinPaiBeanList.size());
                        pinPaiXCT.get(i).setLetter("T");
                    }
                    pinPaiBeanList.addAll(pinPaiXCT);
                    letterList.add(pinPaiBeanList.size());
                    for (int i = 0; i < pinPaiXCW.size(); i++) {
                        pinPaiXCW.get(i).setIndex(pinPaiBeanList.size());
                        pinPaiXCW.get(i).setLetter("W");
                    }
                    pinPaiBeanList.addAll(pinPaiXCW);
                    letterList.add(pinPaiBeanList.size());
                    for (int i = 0; i < pinPaiXCX.size(); i++) {
                        pinPaiXCX.get(i).setIndex(pinPaiBeanList.size());
                        pinPaiXCX.get(i).setLetter("X");
                    }
                    pinPaiBeanList.addAll(pinPaiXCX);
                    letterList.add(pinPaiBeanList.size());
                    for (int i = 0; i < pinPaiXCY.size(); i++) {
                        pinPaiXCY.get(i).setIndex(pinPaiBeanList.size());
                        pinPaiXCY.get(i).setLetter("Y");
                    }
                    pinPaiBeanList.addAll(pinPaiXCY);
                    letterList.add(pinPaiBeanList.size());
                    for (int i = 0; i < pinPaiXCZ.size(); i++) {
                        pinPaiXCZ.get(i).setIndex(pinPaiBeanList.size());
                        pinPaiXCZ.get(i).setLetter("Z");
                    }
                    pinPaiBeanList.addAll(pinPaiXCZ);
                    adapter.clear();
                    adapter.addAll(pinPaiBeanList);
                } catch (Exception e) {
                    showError("数据出错");
                }
            }

            @Override
            public void onError() {
                showError("网络出错");
            }

            public void showError(String msg) {
                View viewLoader = LayoutInflater.from(PinPaiXCActivity.this).inflate(R.layout.view_loaderror, null);
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

    public class StickyHeaderAdapter implements StickyHeaderDecoration.IStickyHeaderAdapter<StickyHeaderAdapter.HeaderHolder> {

        private LayoutInflater mInflater;
        private Context context;

        public StickyHeaderAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
            this.context = context;
        }

        @Override
        public long getHeaderId(int position) {
            return pinPaiBeanList.get(position).getIndex();
        }

        @Override
        public HeaderHolder onCreateHeaderViewHolder(ViewGroup parent) {
            final View view = mInflater.inflate(R.layout.sticky_header_pin_pai, parent, false);
            return new HeaderHolder(view);
        }

        @Override
        public void onBindHeaderViewHolder(HeaderHolder viewholder, final int position) {
            viewholder.textTitle.setText(pinPaiBeanList.get(position).getLetter());
        }

        class HeaderHolder extends RecyclerView.ViewHolder {
            public TextView textTitle;

            public HeaderHolder(View itemView) {
                super(itemView);
                textTitle = itemView.findViewById(R.id.textTitle);
            }
        }
    }

    /**
     * RecyclerView 移动到当前位置，
     *
     * @param manager   设置RecyclerView对应的manager
     * @param mRecyclerView  当前的RecyclerView
     * @param n  要跳转的位置
     */
    public static void MoveToPosition(LinearLayoutManager manager, RecyclerView mRecyclerView, int n) {
        int firstItem = manager.findFirstVisibleItemPosition();
        int lastItem = manager.findLastVisibleItemPosition();
        if (n <= firstItem) {
            mRecyclerView.scrollToPosition(n);
        } else if (n <= lastItem) {
            int top = mRecyclerView.getChildAt(n - firstItem).getTop();
            mRecyclerView.scrollBy(0, top);
        } else {
            mRecyclerView.scrollToPosition(n);
        }

    }
}
