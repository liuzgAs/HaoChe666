package com.haoche666.buyer.avtivity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bumptech.glide.Glide;
import com.haoche666.buyer.R;
import com.haoche666.buyer.base.MyDialog;
import com.haoche666.buyer.base.ZjbBaseActivity;
import com.haoche666.buyer.constant.Constant;
import com.haoche666.buyer.model.CarDetails;
import com.haoche666.buyer.model.OkObject;
import com.haoche666.buyer.util.ApiClient;
import com.haoche666.buyer.viewholder.CheLiangBannerImgHolderView;
import com.haoche666.buyer.viewholder.CheLiangXQViewHolder;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;

import java.util.HashMap;
import java.util.List;

import huisedebi.zjb.mylibrary.util.DpUtils;
import huisedebi.zjb.mylibrary.util.GsonUtils;
import huisedebi.zjb.mylibrary.util.LogUtil;
import huisedebi.zjb.mylibrary.util.RecycleViewDistancaUtil;
import huisedebi.zjb.mylibrary.util.ScreenUtils;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 车辆详情
 *
 * @author Administrator
 */
public class CheLiangXQActivity extends ZjbBaseActivity implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener, EasyPermissions.PermissionCallbacks {
    private View viewBar;
    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<CarDetails.ImgListBean> adapter;
    private TextView textViewTitle;
    private int viewBarHeight;
    private AlertDialog payVideoDialog;
    private int id;
    private ImageView imageShare;
    private View viewBottom;
    private List<CarDetails.ArchivesBean> archives;
    private List<CarDetails.BannerBean> bannerBeanList;
    private CarDetails.CarBean carBean;
    private CarDetails.StoreBean storeBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_che_liang_xq);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {
        Intent intent = getIntent();
        id = intent.getIntExtra(Constant.IntentKey.ID, 0);
    }

    @Override
    protected void findID() {
        viewBar = findViewById(R.id.viewBar);
        recyclerView = (EasyRecyclerView) findViewById(R.id.recyclerView);
        textViewTitle = (TextView) findViewById(R.id.textViewTitle);
        imageShare = (ImageView) findViewById(R.id.imageShare);
        viewBottom = findViewById(R.id.viewBottom);
    }

    @Override
    protected void initViews() {
        textViewTitle.setText("车辆详情");
        viewBarHeight = (int) (getResources().getDimension(R.dimen.titleHeight) + ScreenUtils.getStatusBarHeight(this));
        viewBar.getBackground().mutate().setAlpha(0);
        textViewTitle.setAlpha(0);
        initRecycle();
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
        findViewById(R.id.textCall).setOnClickListener(this);
    }

    @Override
    protected void initData() {
        onRefresh();
    }

    private void initRecycle() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DividerDecoration itemDecoration = new DividerDecoration(Color.WHITE, (int) DpUtils.convertDpToPixel(10f, this), 0, 0);
        itemDecoration.setDrawLastItem(true);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setRefreshingColorResources(R.color.basic_color);
        recyclerView.getSwipeToRefresh().setProgressViewOffset(true, 30, 220);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<CarDetails.ImgListBean>(this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_resizable_imageview;
                return new CheLiangXQViewHolder(parent, layout);
            }

        });
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {

            private TextView textIntro;
            private TextView textName;
            private ImageView imageLogo;
            private TextView textPurchasePrice;
            private TextView textPrice;
            private TextView textTitle;
            private TextView textVip;
            private TextView textSingle;
            private View viewSingle;
            private View viewVip;
            private TextView textZhiShiQi;
            private ConvenientBanner banner;
            private boolean isVip = true;
            private TextView[] textArchivesTitle = new TextView[6];
            private TextView[] textArchivesDes = new TextView[6];
            private int[] textArchivesTitleID = {
                    R.id.textArchives0000,
                    R.id.textArchives0100,
                    R.id.textArchives0200,
                    R.id.textArchives0300,
                    R.id.textArchives0400,
                    R.id.textArchives0500,
            };
            private int[] textArchivesDesID = {
                    R.id.textArchives0001,
                    R.id.textArchives0101,
                    R.id.textArchives0201,
                    R.id.textArchives0301,
                    R.id.textArchives0401,
                    R.id.textArchives0501,
            };

            @Override
            public View onCreateView(ViewGroup parent) {
                View header_che_liang_xq = LayoutInflater.from(CheLiangXQActivity.this).inflate(R.layout.header_che_liang_xq, null);
                banner = header_che_liang_xq.findViewById(R.id.banner);
                banner.setScrollDuration(1000);
                banner.startTurning(3000);
                textZhiShiQi = header_che_liang_xq.findViewById(R.id.textZhiShiQi);
                banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        textZhiShiQi.setText((position + 1) + "/" + bannerBeanList.size());
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
                header_che_liang_xq.findViewById(R.id.viewVideo).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        payVideo();
                    }
                });
                for (int i = 0; i < textArchivesTitle.length; i++) {
                    textArchivesTitle[i] = header_che_liang_xq.findViewById(textArchivesTitleID[i]);
                    textArchivesDes[i] = header_che_liang_xq.findViewById(textArchivesDesID[i]);
                }
                textTitle = header_che_liang_xq.findViewById(R.id.textTitle);
                textPrice = header_che_liang_xq.findViewById(R.id.textPrice);
                textPurchasePrice = header_che_liang_xq.findViewById(R.id.textPurchasePrice);
                imageLogo = header_che_liang_xq.findViewById(R.id.imageLogo);
                textName = header_che_liang_xq.findViewById(R.id.textName);
                textIntro = header_che_liang_xq.findViewById(R.id.textIntro);
                return header_che_liang_xq;
            }

            /**
             * 支付视频dialog
             */
            private void payVideo() {
                isVip = true;
                LayoutInflater inflater = LayoutInflater.from(CheLiangXQActivity.this);
                View dialog_chan_pin = inflater.inflate(R.layout.dialog_pay_video, null);
                dialog_chan_pin.findViewById(R.id.imageCancle).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        payVideoDialog.dismiss();
                    }
                });
                viewVip = dialog_chan_pin.findViewById(R.id.viewVip);
                viewSingle = dialog_chan_pin.findViewById(R.id.viewSingle);
                textSingle = dialog_chan_pin.findViewById(R.id.textSingle);
                textVip = dialog_chan_pin.findViewById(R.id.textVip);
                viewVip.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        isVip = true;
                        vipRadio();
                    }
                });
                viewSingle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        isVip = false;
                        vipRadio();
                    }
                });
                dialog_chan_pin.findViewById(R.id.textPayVideo).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.setClass(CheLiangXQActivity.this, PayVideoActivity.class);
                        startActivity(intent);
                    }
                });
                vipRadio();
                payVideoDialog = new AlertDialog.Builder(CheLiangXQActivity.this, R.style.dialog)
                        .setView(dialog_chan_pin)
                        .create();
                payVideoDialog.show();
                Window dialogWindow = payVideoDialog.getWindow();
                dialogWindow.setGravity(Gravity.BOTTOM);
                dialogWindow.setWindowAnimations(R.style.dialogFenXiang);
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                DisplayMetrics d = CheLiangXQActivity.this.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
                lp.width = (int) (d.widthPixels * 1); // 高度设置为屏幕的0.6
                dialogWindow.setAttributes(lp);
            }

            /**
             * 切换开通vip和单个视频
             */
            private void vipRadio() {
                if (isVip) {
                    viewSingle.setBackgroundResource(R.drawable.shape_gray1dp_5dp);
                    viewVip.setBackgroundResource(R.drawable.shape_basic1dp_5dp);
                    textSingle.setTextColor(ContextCompat.getColor(CheLiangXQActivity.this, R.color.light_black));
                    textVip.setTextColor(ContextCompat.getColor(CheLiangXQActivity.this, R.color.textGold));
                } else {
                    viewSingle.setBackgroundResource(R.drawable.shape_basic1dp_5dp);
                    viewVip.setBackgroundResource(R.drawable.shape_gray1dp_5dp);
                    textSingle.setTextColor(ContextCompat.getColor(CheLiangXQActivity.this, R.color.textGold));
                    textVip.setTextColor(ContextCompat.getColor(CheLiangXQActivity.this, R.color.light_black));
                }
            }

            @Override
            public void onBindView(View headerView) {
                if (banner != null) {
                    banner.setPages(new CBViewHolderCreator() {
                        @Override
                        public Object createHolder() {
                            return new CheLiangBannerImgHolderView();
                        }
                    }, bannerBeanList);
                }
                if (archives != null) {
                    for (int i = 0; i < archives.size(); i++) {
                        textArchivesTitle[i].setText(archives.get(i).getName());
                        textArchivesDes[i].setText(archives.get(i).getValue());
                    }
                }
                if (carBean != null) {
                    textTitle.setText(carBean.getTitle());
                    textPrice.setText(carBean.getPrice());
                    textPurchasePrice.setText(carBean.getPurchasePrice());
                }
                if (storeBean != null) {
                    Glide.with(CheLiangXQActivity.this)
                            .load(storeBean.getLogo())
                            .asBitmap()
                            .dontAnimate()
                            .placeholder(R.mipmap.ic_empty)
                            .into(imageLogo);
                    textName.setText(storeBean.getName());
                    textIntro.setText(storeBean.getIntro());
                }
            }

        });
        adapter.addFooter(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                View view = new View(CheLiangXQActivity.this);
                view.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) DpUtils.convertDpToPixel(15f, CheLiangXQActivity.this)));
                return view;
            }

            @Override
            public void onBindView(View headerView) {

            }
        });
        recyclerView.setRefreshListener(this);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int scrollY = RecycleViewDistancaUtil.getDistance(recyclerView, 0);
                float guangGaoHeight = getResources().getDimension(R.dimen.cheLiangXQBanner);
                if (scrollY <= guangGaoHeight - viewBarHeight && scrollY >= 0) {
                    int i = (int) ((double) scrollY / (double) (guangGaoHeight - viewBar.getHeight()) * 255);
                    viewBar.getBackground().mutate().setAlpha(i);
                    textViewTitle.setAlpha((float) i / 255f);
                } else {
                    viewBar.getBackground().mutate().setAlpha(255);
                    textViewTitle.setAlpha(1);
                }
            }
        });
    }


    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.CAR_DETAILS;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
        }
        params.put("id", id + "");
        return new OkObject(params, url);
    }

    @Override
    public void onRefresh() {
        ApiClient.post(this, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("车辆详情", s);
                try {
                    CarDetails carDetails = GsonUtils.parseJSON(s, CarDetails.class);
                    if (carDetails.getStatus() == 1) {
                        viewBar.getBackground().mutate().setAlpha(0);
                        imageShare.setVisibility(View.VISIBLE);
                        viewBottom.setVisibility(View.VISIBLE);
                        archives = carDetails.getArchives();
                        bannerBeanList = carDetails.getBanner();
                        carBean = carDetails.getCar();
                        storeBean = carDetails.getStore();
                        List<CarDetails.ImgListBean> imgListBeanList = carDetails.getImgList();
                        adapter.clear();
                        adapter.addAll(imgListBeanList);
                    } else if (carDetails.getStatus() == 3) {
                        MyDialog.showReLoginDialog(CheLiangXQActivity.this);
                    } else {
                        showError(carDetails.getInfo());
                    }
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
                View viewLoader = LayoutInflater.from(CheLiangXQActivity.this).inflate(R.layout.view_loaderror, null);
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
                viewBar.getBackground().mutate().setAlpha(255);
                imageShare.setVisibility(View.GONE);
                viewBottom.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textCall:
                requiresPermission();
                break;
            case R.id.imageBack:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (payVideoDialog != null) {
            if (payVideoDialog.isShowing()) {
                payVideoDialog.dismiss();
            } else {
                finish();
            }
        } else {
            finish();
        }
    }

    private static final int CALL_PHONE = 1991;

    /**
     * 检查权限
     */
    private void requiresPermission() {
        String[] perms = {Manifest.permission.CALL_PHONE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            // Already have permission, do the thing
            call();
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, "需要拨打电话权限",
                    CALL_PHONE, perms);
        }
    }

    /**
     * 拨打电话
     */
    private void call() {
    /*跳转到拨号界面，同时传递电话号码*/
        if (storeBean != null) {
            if (TextUtils.isEmpty(storeBean.getTel())){
                Toast.makeText(CheLiangXQActivity.this, "电话号码为空", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + storeBean.getTel()));
            startActivity(dialIntent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        call();
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this)
                    .setTitle("为了您能使用拨打电话功能，请开启打电话权限！")
                    .setPositiveButton("去设置")
                    .setNegativeButton("取消")
                    .setRequestCode(CALL_PHONE)
                    .build()
                    .show();
        }
    }
}
