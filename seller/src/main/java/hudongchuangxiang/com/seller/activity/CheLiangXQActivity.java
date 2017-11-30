package hudongchuangxiang.com.seller.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;

import java.util.ArrayList;
import java.util.List;

import hudongchuangxiang.com.seller.R;
import hudongchuangxiang.com.seller.base.ZjbBaseActivity;
import hudongchuangxiang.com.seller.viewholder.CheLiangXQImgHolderView;
import hudongchuangxiang.com.seller.viewholder.CheLiangXQViewHolder;
import huisedebi.zjb.mylibrary.util.DpUtils;
import huisedebi.zjb.mylibrary.util.RecycleViewDistancaUtil;
import huisedebi.zjb.mylibrary.util.ScreenUtils;

/**
 * 车辆详情
 *
 * @author Administrator
 */
public class CheLiangXQActivity extends ZjbBaseActivity implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {
    private View viewBar;
    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<Integer> adapter;
    private TextView textViewTitle;
    private int viewBarHeight;
    private List<Integer> imgList = new ArrayList<>();
    private AlertDialog payVideoDialog;

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

    }

    @Override
    protected void findID() {
        viewBar = findViewById(R.id.viewBar);
        recyclerView = (EasyRecyclerView) findViewById(R.id.recyclerView);
        textViewTitle = (TextView) findViewById(R.id.textViewTitle);
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
        int red = getResources().getColor(R.color.basic_color01);
        recyclerView.setRefreshingColor(red);
        recyclerView.getSwipeToRefresh().setProgressViewOffset(true, 30, 220);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<Integer>(this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_resizable_imageview;
                return new CheLiangXQViewHolder(parent, layout);
            }

        });
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {

            private TextView textVip;
            private TextView textSingle;
            private View viewSingle;
            private View viewVip;
            private TextView textZhiShiQi;
            private ConvenientBanner banner;
            private List<Integer> imgList = new ArrayList<>();
            private boolean isVip = true;

            @Override
            public View onCreateView(ViewGroup parent) {
                View header_che_liang_xq = LayoutInflater.from(CheLiangXQActivity.this).inflate(R.layout.header_che_liang_xq, null);
                banner = header_che_liang_xq.findViewById(R.id.banner);
                banner.setScrollDuration(1000);
                banner.startTurning(3000);
                imgList.add(R.mipmap.videofengmain);
                imgList.add(R.mipmap.videofengmain);
                imgList.add(R.mipmap.videofengmain);
                imgList.add(R.mipmap.videofengmain);
                imgList.add(R.mipmap.videofengmain);
                imgList.add(R.mipmap.videofengmain);
                textZhiShiQi = header_che_liang_xq.findViewById(R.id.textZhiShiQi);
                banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        textZhiShiQi.setText((position + 1) + "/" + imgList.size());
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
                        intent.setClass(CheLiangXQActivity.this,PayVideoActivity.class);
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
                banner.setPages(new CBViewHolderCreator() {
                    @Override
                    public Object createHolder() {
                        return new CheLiangXQImgHolderView();
                    }
                }, imgList);
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

    @Override
    public void onRefresh() {
        imgList.clear();
        imgList.add(R.mipmap.cheliangxiangqing01);
        imgList.add(R.mipmap.cheliangxiangqing02);
        imgList.add(R.mipmap.cheliangxiangqing03);
        adapter.clear();
        adapter.addAll(imgList);
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
}
