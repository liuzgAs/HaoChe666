package com.haoche666.buyer.activity;

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
import com.haoche666.buyer.util.ApiClient;
import com.haoche666.buyer.viewholder.CheXiViewHolder;
import com.haoche666.buyer.viewholder.PinPaiViewHolder;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.jude.easyrecyclerview.decoration.StickyHeaderDecoration;

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
    public String brandName;
    public String logoPath;
    public int brand;
    public int bsid;
    private List<CarCarparam.CarBean> hotCarBeanList;
    private boolean isName;

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
        Intent intent = getIntent();
        brand = intent.getIntExtra(Constant.IntentKey.BRAND, -1);
        isName = intent.getBooleanExtra(Constant.IntentKey.NAME, false);
    }

    @Override
    protected void findID() {
        recyclerViewRight = findViewById(R.id.recyclerViewRight);
        recyclerView = findViewById(R.id.recyclerView);
        drawerLayout = findViewById(R.id.drawerLayout);
    }

    @Override
    protected void initViews() {
        if (brand == 1) {
            ((TextView) findViewById(R.id.textViewTitle)).setText("选择品牌");
        } else if (isName) {
            ((TextView) findViewById(R.id.textViewTitle)).setText("选择车辆名称");
        } else {
            ((TextView) findViewById(R.id.textViewTitle)).setText("品牌选车");
        }
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
                    layoutManager.scrollToPositionWithOffset(position - 1, 0);
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
            private View textBuXian;
            View[] reMenView = new View[10];
            TextView[] renMenText = new TextView[10];
            int[] reMenTextId = new int[]{
                    R.id.text0001,
                    R.id.text0002,
                    R.id.text0003,
                    R.id.text0004,
                    R.id.text0005,
                    R.id.text0006,
                    R.id.text0007,
                    R.id.text0008,
                    R.id.text0009,
                    R.id.text0010,
            };

            ImageView[] reMenImg = new ImageView[10];
            int[] reMenImgId = new int[]{
                    R.id.image0001,
                    R.id.image0002,
                    R.id.image0003,
                    R.id.image0004,
                    R.id.image0005,
                    R.id.image0006,
                    R.id.image0007,
                    R.id.image0008,
                    R.id.image0009,
                    R.id.image0010,
            };

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
                    renMenText[i] = view.findViewById(reMenTextId[i]);
                    reMenImg[i] = view.findViewById(reMenImgId[i]);
                    final int finalI = i;
                    reMenView[i].setVisibility(View.INVISIBLE);
                    reMenView[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            CarCarparam.CarBean hotCarBean = hotCarBeanList.get(finalI);
                            if (brand == 1) {
                                chooseBrand(hotCarBean);
                            } else {
                                drawerLayout.openDrawer(recyclerViewRight);
                                brandName = hotCarBean.getName();
                                logoPath = hotCarBean.getImg();
                                cheXi(hotCarBean.getId());
                            }
                        }
                    });
                }
                textBuXian = view.findViewById(R.id.textBuXian);
                textBuXian.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.putExtra(Constant.IntentKey.ID, 0);
                        intent.putExtra(Constant.IntentKey.BSID, 0);
                        intent.putExtra(Constant.IntentKey.NAME, "全部品牌");
                        setResult(Constant.RequestResultCode.PIN_PAI, intent);
                        finish();
                    }
                });
                View textStar = view.findViewById(R.id.textStar);
                View textLine = view.findViewById(R.id.textLine);
                View textLine1 = view.findViewById(R.id.textLine1);
                if (brand == 1) {
                    textBuXian.setVisibility(View.GONE);
                    textStar.setVisibility(View.GONE);
                    textLine.setVisibility(View.GONE);
                    textLine1.setVisibility(View.GONE);
                } else {
                    textBuXian.setVisibility(View.VISIBLE);
                    textStar.setVisibility(View.VISIBLE);
                    textLine.setVisibility(View.VISIBLE);
                    textLine1.setVisibility(View.VISIBLE);
                }
                View viewBuXian = view.findViewById(R.id.viewBuXian);
                if (isName) {
                    viewBuXian.setVisibility(View.GONE);
                } else {
                    viewBuXian.setVisibility(View.VISIBLE);
                }
                return view;
            }

            @Override
            public void onBindView(View headerView) {
                if (hotCarBeanList != null) {
                    for (int i = 0; i < hotCarBeanList.size(); i++) {
                        reMenView[i].setVisibility(View.VISIBLE);
                        Glide.with(PinPaiXCActivity.this)
                                .load(hotCarBeanList.get(i).getImg())
                                .asBitmap()
                                .placeholder(R.mipmap.ic_empty)
                                .into(reMenImg[i]);
                        renMenText[i].setText(hotCarBeanList.get(i).getName());
                    }
                }
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
        params.put("id", id + "");
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
                intent.putExtra(Constant.IntentKey.ID, adapterRight.getItem(position).getId());
                intent.putExtra(Constant.IntentKey.BSID, bsid);
                intent.putExtra(Constant.IntentKey.NAME, adapterRight.getItem(position).getName());
                setResult(Constant.RequestResultCode.PIN_PAI, intent);
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
                view.findViewById(R.id.textBuXian).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.putExtra(Constant.IntentKey.ID, 0);
                        intent.putExtra(Constant.IntentKey.BSID, bsid);
                        intent.putExtra(Constant.IntentKey.NAME, brandName);
                        setResult(Constant.RequestResultCode.PIN_PAI, intent);
                        finish();
                    }
                });
                View viewBuXian = view.findViewById(R.id.viewBuXian);
                if (isName) {
                    viewBuXian.setVisibility(View.GONE);
                } else {
                    viewBuXian.setVisibility(View.VISIBLE);
                }
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
                        hotCarBeanList = carCarparam.getHotbrand();
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
            if (position < 26) {
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

    public void chooseBrand(CarCarparam.CarBean brandBean) {
        Intent intent = new Intent();
        intent.putExtra(Constant.IntentKey.BEAN, brandBean);
        setResult(Constant.RequestResultCode.BRAND, intent);
        finish();
    }
}
