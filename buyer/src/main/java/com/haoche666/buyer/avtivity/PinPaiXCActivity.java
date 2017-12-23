package com.haoche666.buyer.avtivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.haoche666.buyer.R;
import com.haoche666.buyer.base.MyDialog;
import com.haoche666.buyer.base.ZjbBaseNotLeftActivity;
import com.haoche666.buyer.constant.Constant;
import com.haoche666.buyer.customview.SideLetterBar;
import com.haoche666.buyer.model.CarCarparam;
import com.haoche666.buyer.model.CarCarstyle;
import com.haoche666.buyer.model.OkObject;
import com.haoche666.buyer.model.ReMen;
import com.haoche666.buyer.util.ApiClient;
import com.haoche666.buyer.viewholder.CheXiViewHolder;
import com.haoche666.buyer.viewholder.PinPaiViewHolder;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.jude.easyrecyclerview.decoration.StickyHeaderDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import huisedebi.zjb.mylibrary.util.GsonUtils;
import huisedebi.zjb.mylibrary.util.LogUtil;
import huisedebi.zjb.mylibrary.util.ScreenUtils;

/**
 * 品牌选车
 *
 * @author Administrator
 */
public class PinPaiXCActivity extends ZjbBaseNotLeftActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    public EasyRecyclerView recyclerViewRight;
    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<CarCarparam.BrandBean> adapter;
    private SideLetterBar mLetterBar;
    public DrawerLayout drawerLayout;
    private RecyclerArrayAdapter<CarCarstyle.DataBean> adapterRight;
    private String letter;
    private int brandId;
    public String brandName;
    public String logoPath;
    private List<ReMen> reMenList = new ArrayList<>();

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
        recyclerViewRight = findViewById(R.id.recyclerViewRight);
        recyclerView = findViewById(R.id.recyclerView);
        drawerLayout = findViewById(R.id.drawerLayout);
    }

    @Override
    protected void initViews() {
        ((TextView) findViewById(R.id.textViewTitle)).setText("品牌选车");
        ViewGroup.LayoutParams layoutParams = recyclerViewRight.getLayoutParams();
        int screenWidth = ScreenUtils.getScreenWidth(this);
        layoutParams.width = (int) ((float) screenWidth / 4 * 3);
        recyclerViewRight.setLayoutParams(layoutParams);
        initRecycler();
        TextView overlay = findViewById(R.id.tv_letter_overlay);
        mLetterBar = findViewById(R.id.side_letter_bar);
        mLetterBar.setOverlay(overlay);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        initRecyclerViewRight();
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
        mLetterBar.setOnLetterChangedListener(new SideLetterBar.OnLetterChangedListener() {
            @Override
            public void onLetterChanged(String letter, int position) {
                if (position > 1) {
                    RecyclerView recyclerView1 = recyclerView.getRecyclerView();
                    LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView1.getLayoutManager();
                    layoutManager.scrollToPositionWithOffset(position-1, 0);
                } else {
                    recyclerView.scrollToPosition(0);
                }
            }
        });
    }

    @Override
    protected void initData() {
        reMenList.add(new ReMen("大众", "http://i1.chexun.net/images/2015/0113/16627/logo_50_50_191DFF86BFE0B45C3253927FE6CAE681.jpg", "D", 23));
        reMenList.add(new ReMen("沃尔沃", "http://i2.chexun.net/images/2015/0108/16620/logo_50_50_14677CAD5F1064B46B4627B843799716.jpg", "W", 92));
        reMenList.add(new ReMen("日产", "http://i2.chexun.net/images/2015/0108/16620/logo_50_50_E078DDF08F934C9F6A708DFFB6320F42.jpg", "R", 78));
        reMenList.add(new ReMen("本田", "http://i0.chexun.net/images/2015/0113/16627/logo_50_50_70FD7F9E1ABAF0E9D82AD02F1E7CFA24.jpg", "B", 7));
        reMenList.add(new ReMen("丰田", "http://i0.chexun.net/images/2015/0113/16627/logo_50_50_91AF7AA500D18A64C2FCC92119D0C159.jpg", "F", 30));
        reMenList.add(new ReMen("奥迪", "http://i2.chexun.net/images/2015/0113/16627/logo_50_50_65BC3B6C8188EE6FE49108EA4661AA7D.jpg", "A", 2));
        reMenList.add(new ReMen("别克", "http://i2.chexun.net/images/2015/0601/16892/logo_50_50_919A034DE5C2F9C658B582854994CE67.jpg", "B", 8));
        reMenList.add(new ReMen("福特", "http://i0.chexun.net/images/2015/0108/16620/logo_50_50_323E469A0FF5C8B34A0F573CA0AFCBF8.jpg", "F", 29));
        reMenList.add(new ReMen("宝马", "http://i1.chexun.net/images/2015/0108/16620/logo_50_50_13CCD272D4978F4FF9A18DFC4870AFFE.jpg", "B", 9));
        reMenList.add(new ReMen("奔驰", "http://i0.chexun.net/images/2015/0113/16627/logo_50_50_1357F5B5774D27BF9709F2BBF96A4C6F.jpg", "B", 6));
        onRefresh();
    }

    /**
     * 初始化recyclerview
     */
    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setRefreshingColorResources(R.color.basic_color);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<CarCarparam.BrandBean>(PinPaiXCActivity.this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_pinpai;
                return new PinPaiViewHolder(parent, layout);
            }
        });
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            View[] reMenView = new View[10];

            @Override
            public View onCreateView(ViewGroup parent) {
                View view = LayoutInflater.from(PinPaiXCActivity.this).inflate(R.layout.header_pinpai_xc, null);
                reMenView[0] = view.findViewById(R.id.reMen01);
                reMenView[1] = view.findViewById(R.id.reMen02);
                reMenView[2] = view.findViewById(R.id.reMen03);
                reMenView[3] = view.findViewById(R.id.reMen04);
                reMenView[4] = view.findViewById(R.id.reMen05);
                reMenView[5] = view.findViewById(R.id.reMen06);
                reMenView[6] = view.findViewById(R.id.reMen07);
                reMenView[7] = view.findViewById(R.id.reMen08);
                reMenView[8] = view.findViewById(R.id.reMen09);
                reMenView[9] = view.findViewById(R.id.reMen10);
                for (int i = 0; i < reMenView.length; i++) {
                    final int finalI = i;
                    reMenView[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                            drawerLayout.openDrawer(recyclerViewRight);
//                            ReMen reMen = reMenList.get(finalI);
//                            brandId = reMen.getBrandId();
//                            brandName = reMen.getBrandName();
//                            letter = reMen.getLetter();
//                            logoPath = reMen.getLogoPath();
//                            cheXi();
                        }
                    });
                }
                view.findViewById(R.id.textBuXian).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.putExtra(Constant.IntentKey.ID,0);
                        intent.putExtra(Constant.IntentKey.NAME,"全部品牌");
                        setResult(Constant.RequestResultCode.PIN_PAI,intent);
                        finish();
                    }
                });
                return view;
            }

            @Override
            public void onBindView(View headerView) {

            }
        });

    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getCheXiCXOkObject(int id) {
        String url = Constant.HOST + Constant.Url.CAR_CARSTYLE;
        HashMap<String, String> params = new HashMap<>();
        params.put("id",id+"");
        return new OkObject(params, url);
    }

    /**
     * des： 车系请求
     * author： ZhangJieBo
     * date： 2017/11/15 0015 下午 2:20
     */
    public void cheXi(final int id) {
        recyclerViewRight.showProgress();
        ApiClient.post(this, getCheXiCXOkObject(id), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("车系", s);
                try {
                    CarCarstyle carCarstyle = GsonUtils.parseJSON(s, CarCarstyle.class);
                    if (carCarstyle.getStatus() == 1) {
                        List<CarCarstyle.DataBean> dataBeanList = carCarstyle.getData();
                        adapterRight.clear();
                        adapterRight.addAll(dataBeanList);
                    } else if (carCarstyle.getStatus() == 3) {
                        MyDialog.showReLoginDialog(PinPaiXCActivity.this);
                    } else {
                        showError(carCarstyle.getInfo());
                    }
                } catch (Exception e) {
                    showError("数据出错");
                }
            }

            @Override
            public void onError() {
                showError("网络出错");
            }

            private void showError(String msg) {
                View viewLoader = LayoutInflater.from(PinPaiXCActivity.this).inflate(R.layout.view_loaderror, null);
                TextView textMsg = viewLoader.findViewById(R.id.textMsg);
                textMsg.setText(msg);
                viewLoader.findViewById(R.id.buttonReLoad).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        recyclerViewRight.showProgress();
                        cheXi(id);
                    }
                });
                recyclerViewRight.setErrorView(viewLoader);
                recyclerViewRight.showError();
            }
        });
    }

    /**
     * 初始化右边侧出recyclerview
     */
    private void initRecyclerViewRight() {
        recyclerViewRight.setLayoutManager(new LinearLayoutManager(this));
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) getResources().getDimension(R.dimen.line_width), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerViewRight.addItemDecoration(itemDecoration);
        recyclerViewRight.setRefreshingColorResources(R.color.basic_color);
        recyclerViewRight.setAdapterWithProgress(adapterRight = new RecyclerArrayAdapter<CarCarstyle.DataBean>(PinPaiXCActivity.this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_chexi;
                return new CheXiViewHolder(parent, layout);
            }
        });
        adapterRight.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                drawerLayout.closeDrawer(recyclerViewRight);
                Intent intent = new Intent();
                intent.putExtra(Constant.IntentKey.ID,adapterRight.getItem(position).getId());
                intent.putExtra(Constant.IntentKey.NAME,adapterRight.getItem(position).getName());
                setResult(Constant.RequestResultCode.PIN_PAI,intent);
                finish();
            }
        });
        adapterRight.addHeader(new RecyclerArrayAdapter.ItemView() {

            private TextView textName;
            private ImageView imageLogo;

            @Override
            public View onCreateView(ViewGroup parent) {
                View view = LayoutInflater.from(PinPaiXCActivity.this).inflate(R.layout.header_che_xi, null);
                imageLogo = view.findViewById(R.id.imageLogo);
                textName = view.findViewById(R.id.textName);
                View textBar = view.findViewById(R.id.textBar);
                ViewGroup.LayoutParams layoutParams = textBar.getLayoutParams();
                layoutParams.height = ScreenUtils.getStatusBarHeight(PinPaiXCActivity.this);
                textBar.setLayoutParams(layoutParams);
                return view;
            }

            @Override
            public void onBindView(View headerView) {
                if (!TextUtils.isEmpty(logoPath)) {
                    Glide.with(PinPaiXCActivity.this)
                            .load(logoPath)
                            .asBitmap()
                            .placeholder(R.mipmap.ic_empty)
                            .into(imageLogo);
                }
                textName.setText(brandName);
            }
        });
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
        String url = Constant.HOST + Constant.Url.CAR_CARPARAM;
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
                    CarCarparam carCarparam = GsonUtils.parseJSON(s, CarCarparam.class);
                    if (carCarparam.getStatus() == 1) {
                        List<CarCarparam.BrandBean> brandBeanList = carCarparam.getBrand();
                        adapter.clear();
                        adapter.addAll(brandBeanList);
                    } else if (carCarparam.getStatus() == 3) {
                        MyDialog.showReLoginDialog(PinPaiXCActivity.this);
                    } else {
                        Toast.makeText(PinPaiXCActivity.this, carCarparam.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                         /*StickyHeader*/
                    StickyHeaderDecoration decoration = new StickyHeaderDecoration(new StickyHeaderAdapter(PinPaiXCActivity.this));
                    decoration.setIncludeHeader(false);
                    recyclerView.addItemDecoration(decoration);
                } catch (Exception e) {
                    showError("数据出错");
                }
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

    /**
     * 磁贴adapter
     */
    public class StickyHeaderAdapter implements StickyHeaderDecoration.IStickyHeaderAdapter<StickyHeaderAdapter.HeaderHolder> {

        private LayoutInflater mInflater;
        private Context context;

        private StickyHeaderAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
            this.context = context;
        }

        @Override
        public long getHeaderId(int position) {
            return position;
        }

        @Override
        public HeaderHolder onCreateHeaderViewHolder(ViewGroup parent) {
            final View view = mInflater.inflate(R.layout.sticky_header_pin_pai, parent, false);
            return new HeaderHolder(view);
        }

        @Override
        public void onBindHeaderViewHolder(HeaderHolder viewholder, final int position) {
            if (position<26){
                viewholder.textTitle.setText(adapter.getItem(position).getTitle());
            }
        }

        class HeaderHolder extends RecyclerView.ViewHolder {
            public TextView textTitle;

            private HeaderHolder(View itemView) {
                super(itemView);
                textTitle = itemView.findViewById(R.id.textTitle);
            }
        }
    }
}
