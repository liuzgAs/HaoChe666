package com.haoche666.buyer.fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bumptech.glide.Glide;
import com.haoche666.buyer.R;
import com.haoche666.buyer.activity.CheHangLBActivity;
import com.haoche666.buyer.activity.CheLiangLBActivity;
import com.haoche666.buyer.activity.CheLiangXQActivity;
import com.haoche666.buyer.activity.MainActivity;
import com.haoche666.buyer.activity.ShiPinLBActivity;
import com.haoche666.buyer.activity.WebActivity;
import com.haoche666.buyer.activity.WenZhangLBActivity;
import com.haoche666.buyer.activity.ZuJiActivity;
import com.haoche666.buyer.adapter.Banner02Adapter;
import com.haoche666.buyer.adapter.BannerAdapter;
import com.haoche666.buyer.base.MyDialog;
import com.haoche666.buyer.base.ToLoginActivity;
import com.haoche666.buyer.base.ZjbBaseFragment;
import com.haoche666.buyer.constant.Constant;
import com.haoche666.buyer.model.Buyer;
import com.haoche666.buyer.model.OkObject;
import com.haoche666.buyer.util.ApiClient;
import com.haoche666.buyer.viewholder.ShouYeBannerHolderView;
import com.haoche666.buyer.viewholder.ShouYeViewHolder;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.rd.PageIndicatorView;
import com.rd.animation.AnimationType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import huisedebi.zjb.mylibrary.util.BannerSettingUtil;
import huisedebi.zjb.mylibrary.util.DpUtils;
import huisedebi.zjb.mylibrary.util.GsonUtils;
import huisedebi.zjb.mylibrary.util.LogUtil;
import huisedebi.zjb.mylibrary.util.ScreenUtils;

/**
 * A simple {@link Fragment} subclass.
 *
 * @author Administrator
 */
public class ShouYeFragment extends ZjbBaseFragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {


    private View mInflate;
    private View mRelaTitleStatue;
    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<Buyer.DataBean> adapter;
    private int page = 1;
    private List<Buyer.StoreBean> storeBeanList = new ArrayList<>();
    private List<Buyer.BannerBean> bannerBeanList;
    private List<Buyer.VideoBeanX> videoBeanXList;
    private List<Buyer.NewsBean> newsBeanList;
    private List<Buyer.HotCar> hotCarList;

    private ImageView imageImg;
    private BroadcastReceiver reciver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action) {
                case Constant.BroadcastCode.CHE_HANG_GUAN_ZHU:
                    shuaXinCheHang();
                    break;
                default:
                    break;
            }
        }
    };
    private List<Buyer.HotSearch> hotSearch;
    private String settled_url;

    /**
     * 刷新车行
     */
    private void shuaXinCheHang() {
        showLoadingDialog();
        ApiClient.post(mContext, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("ShouYeFragment--onSuccess", s + "");
                try {
                    Buyer buyer = GsonUtils.parseJSON(s, Buyer.class);
                    if (buyer.getStatus() == 1) {
                        storeBeanList = buyer.getStore();
                        adapter.notifyDataSetChanged();
                    } else if (buyer.getStatus() == 3) {
                        MyDialog.showReLoginDialog(mContext);
                    } else {
                        Toast.makeText(mContext, buyer.getInfo(), Toast.LENGTH_SHORT).show();
                    }
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
    }

    public ShouYeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mInflate == null) {
            mInflate = inflater.inflate(R.layout.fragment_shou_ye, container, false);
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
        mRelaTitleStatue = mInflate.findViewById(R.id.relaTitleStatue);
        recyclerView = mInflate.findViewById(R.id.recyclerView);
    }

    @Override
    protected void initViews() {
        ViewGroup.LayoutParams layoutParams = mRelaTitleStatue.getLayoutParams();
        layoutParams.height = (int) (getResources().getDimension(R.dimen.titleHeight) + ScreenUtils.getStatusBarHeight(mContext));
        mRelaTitleStatue.setLayoutParams(layoutParams);
        mRelaTitleStatue.setPadding(0, ScreenUtils.getStatusBarHeight(mContext), 0, 0);
        initRecycle();
    }

    private void initRecycle() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) DpUtils.convertDpToPixel(1f, mContext), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setRefreshingColorResources(R.color.basic_color);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<Buyer.DataBean>(mContext) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_shou_ye;
                return new ShouYeViewHolder(parent, layout, 0);
            }
        });
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {

            private Banner02Adapter banner02Adapter;
            private View viewVideo;
            private TextView textView;
            private TextView textTitle;
            private TextView textPaiDangTitle;
            private PageIndicatorView mPageIndicatorView;
            private ViewPager id_viewpager;
            private ViewPager id_viewpager01;
            private ConvenientBanner banner;
            private View[] bannerText = new View[4];
            private int zhiShiQi;
            private ImageView[] hotImg = new ImageView[4];
            private TextView[] hotText00 = new TextView[4];
            private TextView[] hotText01 = new TextView[4];
            private TextView[] searchTextView = new TextView[8];
            private int[] searchTextViewID = new int[]{
                    R.id.text0300,
                    R.id.text0301,
                    R.id.text0302,
                    R.id.text0303,
                    R.id.text0304,
                    R.id.text0305,
                    R.id.text0306,
                    R.id.text0307,
            };
            private int[] viewHotId = new int[]{
                    R.id.viewHotCar00,
                    R.id.viewHotCar01,
                    R.id.viewHotCar02,
                    R.id.viewHotCar03,
            };

            @Override
            public View onCreateView(ViewGroup parent) {
                final View header_shou_ye = LayoutInflater.from(mContext).inflate(R.layout.header_shou_ye, null);
                bannerText[0] = header_shou_ye.findViewById(R.id.bannerText01);
                bannerText[1] = header_shou_ye.findViewById(R.id.bannerText02);
                bannerText[2] = header_shou_ye.findViewById(R.id.bannerText03);
                bannerText[3] = header_shou_ye.findViewById(R.id.bannerText04);
                banner = header_shou_ye.findViewById(R.id.banner);
                banner.setScrollDuration(1000);
                banner.startTurning(3000);
                banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        for (int i = 0; i < 4; i++) {
                            bannerText[i].setBackgroundResource(R.color.transparent);
                        }
                        bannerText[zhiShiQi].setBackgroundResource(R.color.shouYeBanner);
                        zhiShiQi++;
                        if (zhiShiQi == 4) {
                            zhiShiQi = 0;
                        }
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
                id_viewpager = header_shou_ye.findViewById(R.id.id_viewpager);
                id_viewpager01 = header_shou_ye.findViewById(R.id.id_viewpager01);
                new BannerSettingUtil(id_viewpager).set();
                new BannerSettingUtil(id_viewpager01).set();
                mPageIndicatorView = header_shou_ye.findViewById(R.id.pageIndicatorView);
                mPageIndicatorView.setAnimationType(AnimationType.WORM);
                mPageIndicatorView.setCount(5);
                id_viewpager01.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        mPageIndicatorView.setSelection(position % 5);
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
                header_shou_ye.findViewById(R.id.viewPinPaiXC).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((MainActivity) mContext).mTabHost.setCurrentTab(1);
                        ((MainActivity) mContext).isPinPaiXC = true;
                    }
                });
                header_shou_ye.findViewById(R.id.textMoreCheHang).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.setClass(mContext, CheHangLBActivity.class);
                        startActivity(intent);
                    }
                });
                header_shou_ye.findViewById(R.id.textTuWenMore).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.setClass(mContext, WenZhangLBActivity.class);
                        startActivity(intent);
                    }
                });
                header_shou_ye.findViewById(R.id.textMore).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.setClass(mContext, ShiPinLBActivity.class);
                        startActivity(intent);
                    }
                });
                textPaiDangTitle = header_shou_ye.findViewById(R.id.textPaiDangTitle);
                viewVideo = header_shou_ye.findViewById(R.id.viewVideo);
                id_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        if (videoBeanXList != null) {
                            textPaiDangTitle.setText(videoBeanXList.get(position % videoBeanXList.size()).getTitle());
                        }
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
                imageImg = header_shou_ye.findViewById(R.id.imageImg);
                textTitle = header_shou_ye.findViewById(R.id.textTitle);
                textView = header_shou_ye.findViewById(R.id.textView);
                imageImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (newsBeanList != null) {
                            if (newsBeanList.size() > 0) {
                                Intent intent = new Intent();
                                intent.setClass(mContext, WebActivity.class);
                                intent.putExtra(Constant.IntentKey.TITLE, newsBeanList.get(0).getTitle());
                                intent.putExtra(Constant.IntentKey.URL, newsBeanList.get(0).getUrl());
                                startActivity(intent);
                            }
                        }
                    }
                });
                header_shou_ye.findViewById(R.id.textNewMore).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((MainActivity) mContext).mTabHost.setCurrentTab(1);
                    }
                });
                header_shou_ye.findViewById(R.id.viewJiaGeXC).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((MainActivity) mContext).mTabHost.setCurrentTab(1);
                        ((MainActivity) mContext).isJiaGEXC = true;
                    }
                });
                header_shou_ye.findViewById(R.id.viewWoYaoMC).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((MainActivity) mContext).mTabHost.setCurrentTab(2);
                    }
                });
                header_shou_ye.findViewById(R.id.viewRuZhu).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.setClass(getActivity(), WebActivity.class);
                        intent.putExtra(Constant.IntentKey.TITLE, "申请入驻");
                        intent.putExtra(Constant.IntentKey.URL, settled_url);
                        startActivity(intent);
                    }
                });
                hotImg[0] = header_shou_ye.findViewById(R.id.imageHotCar0000);
                hotImg[1] = header_shou_ye.findViewById(R.id.imageHotCar0100);
                hotImg[2] = header_shou_ye.findViewById(R.id.imageHotCar0200);
                hotImg[3] = header_shou_ye.findViewById(R.id.imageHotCar0300);
                hotText00[0] = header_shou_ye.findViewById(R.id.textHotCar0000);
                hotText00[1] = header_shou_ye.findViewById(R.id.textHotCar0100);
                hotText00[2] = header_shou_ye.findViewById(R.id.textHotCar0200);
                hotText00[3] = header_shou_ye.findViewById(R.id.textHotCar0300);
                hotText01[0] = header_shou_ye.findViewById(R.id.textHotCar0001);
                hotText01[1] = header_shou_ye.findViewById(R.id.textHotCar0101);
                hotText01[2] = header_shou_ye.findViewById(R.id.textHotCar0201);
                hotText01[3] = header_shou_ye.findViewById(R.id.textHotCar0301);
                for (int i = 0; i < viewHotId.length; i++) {
                    final int finalI = i;
                    header_shou_ye.findViewById(viewHotId[i]).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent();
                            intent.setClass(mContext,CheLiangLBActivity.class);
                            intent.putExtra(Constant.IntentKey.VALUE,hotCarList.get(finalI));
                            startActivity(intent);
                        }
                    });
                }
                for (int i = 0; i < searchTextViewID.length; i++) {
                    searchTextView[i] = header_shou_ye.findViewById(searchTextViewID[i]);
                }
                for (int i = 0; i < searchTextView.length; i++) {
                    final int finalI = i;
                    searchTextView[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent();
                            intent.setClass(mContext, CheLiangLBActivity.class);
                            intent.putExtra(Constant.IntentKey.BEAN,hotSearch.get(finalI));
                            startActivity(intent);
                        }
                    });
                }
                header_shou_ye.findViewById(R.id.viewAll).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((MainActivity)mContext).mTabHost.setCurrentTab(1);
                    }
                });
                return header_shou_ye;
            }

            @Override
            public void onBindView(View headerView) {
                if (bannerBeanList != null) {
                    banner.setPages(new CBViewHolderCreator() {
                        @Override
                        public Object createHolder() {
                            return new ShouYeBannerHolderView();
                        }
                    }, bannerBeanList);
                }
                if (newsBeanList != null) {
                    if (newsBeanList.size() > 0) {
                        Glide.with(mContext)
                                .load(newsBeanList.get(0).getImg())
                                .asBitmap()
                                .placeholder(R.mipmap.ic_empty)
                                .into(imageImg);
                        textTitle.setText(newsBeanList.get(0).getTitle());
                        textView.setText(newsBeanList.get(0).getView());
                    }
                }
                if (videoBeanXList != null) {
                    if (videoBeanXList.size() > 0) {
                        viewVideo.setVisibility(View.VISIBLE);
                        id_viewpager.setAdapter(new BannerAdapter(mContext, videoBeanXList));
                        id_viewpager.setCurrentItem(50);
                    } else {
                        viewVideo.setVisibility(View.GONE);
                    }
                }
                banner02Adapter = new Banner02Adapter(mContext, storeBeanList);
                id_viewpager01.setAdapter(banner02Adapter);
                id_viewpager01.setCurrentItem(50);
                if (hotCarList != null) {
                    for (int i = 0; i < hotCarList.size(); i++) {
                        Glide.with(mContext)
                                .load(hotCarList.get(i).getImg())
                                .asBitmap()
                                .placeholder(R.mipmap.ic_empty)
                                .into(hotImg[i]);
                        hotText00[i].setText(hotCarList.get(i).getTitle());
                        hotText01[i].setText(hotCarList.get(i).getX_title());
                    }
                }
                if(hotSearch!=null){
                    for (int i = 0; i < searchTextView.length; i++) {
                        searchTextView[i].setText(hotSearch.get(i).getTitle());
                    }
                }
            }
        });
        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                ApiClient.post(getContext(), getOkObject(), new ApiClient.CallBack() {
                    @Override
                    public void onSuccess(String s) {
                        try {
                            page++;
                            Buyer buyer = GsonUtils.parseJSON(s, Buyer.class);
                            int status = buyer.getStatus();
                            if (status == 1) {
                                List<Buyer.DataBean> dataBeanList = buyer.getData();
                                adapter.addAll(dataBeanList);
                            } else if (status == 3) {
                                MyDialog.showReLoginDialog(getContext());
                            } else {
                                adapter.pauseMore();
                            }
                        } catch (Exception e) {
                            adapter.pauseMore();
                        }
                    }

                    @Override
                    public void onError() {
                        adapter.pauseMore();
                    }
                });
            }

            @Override
            public void onMoreClick() {

            }
        });
        adapter.setNoMore(R.layout.view_nomore, new RecyclerArrayAdapter.OnNoMoreListener() {
            @Override
            public void onNoMoreShow() {

            }

            @Override
            public void onNoMoreClick() {
            }
        });
        adapter.setError(R.layout.view_error, new RecyclerArrayAdapter.OnErrorListener() {
            @Override
            public void onErrorShow() {
                adapter.resumeMore();
            }

            @Override
            public void onErrorClick() {
                adapter.resumeMore();
            }
        });
        recyclerView.setRefreshListener(this);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent();
                intent.putExtra(Constant.IntentKey.ID, adapter.getItem(position).getId());
                intent.setClass(mContext, CheLiangXQActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void setListeners() {
        mInflate.findViewById(R.id.imageZuJi).setOnClickListener(this);
        mInflate.findViewById(R.id.viewSearch).setOnClickListener(this);
        mInflate.findViewById(R.id.imageMine).setOnClickListener(this);
    }

    @Override
    protected void initData() {
        onRefresh();
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.BUYER;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        params.put("p", page + "");
        return new OkObject(params, url);
    }

    @Override
    public void onRefresh() {
        page = 1;
        ApiClient.post(mContext, getOkObject(), new ApiClient.CallBack() {

            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("买家版主页", s);
                try {
                    page++;
                    Buyer buyer = GsonUtils.parseJSON(s, Buyer.class);
                    if (buyer.getStatus() == 1) {
                        storeBeanList = buyer.getStore();
                        videoBeanXList = buyer.getVideo();
                        bannerBeanList = buyer.getBanner();
                        newsBeanList = buyer.getNews();
                        hotCarList = buyer.getHotcar();
                        hotSearch = buyer.getHotSearch();
                        List<Buyer.DataBean> dataBeanList = buyer.getData();
                        adapter.clear();
                        adapter.addAll(dataBeanList);
                        settled_url = buyer.getSettled_url();
                    } else if (buyer.getStatus() == 3) {
                        MyDialog.showReLoginDialog(mContext);
                    } else {
                        showError(buyer.getInfo());
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
                View viewLoader = LayoutInflater.from(mContext).inflate(R.layout.view_loaderror, null);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageMine:
                ((MainActivity) mContext).mTabHost.setCurrentTab(4);
                break;
            case R.id.viewSearch:
                ((MainActivity) mContext).mTabHost.setCurrentTab(1);
                ((MainActivity) mContext).isSearch = true;
                break;
            case R.id.imageZuJi:
                if (isLogin) {
                    Intent intent = new Intent();
                    intent.setClass(mContext, ZuJiActivity.class);
                    startActivity(intent);
                } else {
                    ToLoginActivity.toLoginActivity(mContext);
                }
                break;
            default:

                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.BroadcastCode.CHE_HANG_GUAN_ZHU);
        mContext.registerReceiver(reciver, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mContext.unregisterReceiver(reciver);
    }
}
