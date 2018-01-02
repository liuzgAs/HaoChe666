package com.haoche666.buyer.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.haoche666.buyer.R;
import com.haoche666.buyer.avtivity.CheLiangXQActivity;
import com.haoche666.buyer.avtivity.ChengShiXZActivity;
import com.haoche666.buyer.avtivity.MainActivity;
import com.haoche666.buyer.avtivity.PinPaiXCActivity;
import com.haoche666.buyer.avtivity.ZuJiActivity;
import com.haoche666.buyer.base.MyDialog;
import com.haoche666.buyer.base.ToLoginActivity;
import com.haoche666.buyer.base.ZjbBaseFragment;
import com.haoche666.buyer.constant.Constant;
import com.haoche666.buyer.model.Buyer;
import com.haoche666.buyer.model.CarGetsearchdata;
import com.haoche666.buyer.model.IndexCitylist;
import com.haoche666.buyer.model.IndexMpcity;
import com.haoche666.buyer.model.MaiChe;
import com.haoche666.buyer.model.OkObject;
import com.haoche666.buyer.util.ApiClient;
import com.haoche666.buyer.viewholder.ShouYeViewHolder;
import com.haoche666.buyer.viewholder.SortViewHolder;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import huisedebi.zjb.mylibrary.util.ACache;
import huisedebi.zjb.mylibrary.util.DpUtils;
import huisedebi.zjb.mylibrary.util.GsonUtils;
import huisedebi.zjb.mylibrary.util.LogUtil;
import huisedebi.zjb.mylibrary.util.ScreenUtils;

/**
 * A simple {@link Fragment} subclass.
 *
 * @author Administrator
 */
public class MaiCheFragment extends ZjbBaseFragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {


    private View mInflate;
    private View mRelaTitleStatue;
    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<Buyer.DataBean> adapter;
    private RecyclerArrayAdapter<CarGetsearchdata.DataBean.SortIdBean> adapterSort;
    private int page = 1;
    private TextView textRange;
    private TextView textRange1;
    private CrystalRangeSeekbar rangeSeekbar;
    private CrystalRangeSeekbar rangeSeekbar1;
    private EasyRecyclerView recyclerViewShaiXuan00;
    private View[] viewShaiXuanArr = new View[3];
    private View[] viewShaiXuan00Arr = new View[3];
    private View viewShaiXuan;
    private int shaiXuanVisible = -1;
    private View viewDismiss;
    private GridView gridPrice;
    private PriceAdapter priceAdapter;
    private GridView gridAge;
    private PriceAdapter ageAdapter;
    private TextView textSort;
    private TextView textSearch;
    private TextView textAll;
    private List<CarGetsearchdata.DataBean.SortIdBean> sortIdBeanList = new ArrayList<>();
    private List<CarGetsearchdata.AgePriceBean> zPriceBeanList = new ArrayList<>();
    private List<CarGetsearchdata.AgePriceBean> zAgeBeanList = new ArrayList<>();
    private String lat;
    private String lng;
    private TextView textLocation;

    /**
     * 是否首次启动
     */

    public MaiCheFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mInflate == null) {
            mInflate = inflater.inflate(R.layout.fragment_mai_che, container, false);
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
        ACache aCache = ACache.get(getActivity(), Constant.Acache.LOCATION);
        lat = aCache.getAsString(Constant.Acache.LAT);
        lng = aCache.getAsString(Constant.Acache.LNG);
    }

    @Override
    protected void findID() {
        mRelaTitleStatue = mInflate.findViewById(R.id.relaTitleStatue);
        recyclerView = mInflate.findViewById(R.id.recyclerView);
        textRange = mInflate.findViewById(R.id.textRange);
        textRange1 = mInflate.findViewById(R.id.textRange1);
        rangeSeekbar = mInflate.findViewById(R.id.rangeSeekbar);
        rangeSeekbar1 = mInflate.findViewById(R.id.rangeSeekbar1);
        viewShaiXuan = mInflate.findViewById(R.id.viewShaiXuan);
        viewDismiss = mInflate.findViewById(R.id.viewDismiss);
        recyclerViewShaiXuan00 = mInflate.findViewById(R.id.recyclerViewShaiXuan00);
        viewShaiXuanArr[0] = mInflate.findViewById(R.id.recyclerViewShaiXuan00);
        viewShaiXuanArr[1] = mInflate.findViewById(R.id.viewShaiXuan01);
        viewShaiXuanArr[2] = mInflate.findViewById(R.id.viewShaiXuan02);
        viewShaiXuan00Arr[0] = mInflate.findViewById(R.id.viewShaiXuan0000);
        viewShaiXuan00Arr[1] = mInflate.findViewById(R.id.viewShaiXuan0001);
        viewShaiXuan00Arr[2] = mInflate.findViewById(R.id.viewShaiXuan0002);
        gridPrice = mInflate.findViewById(R.id.gridPrice);
        gridAge = mInflate.findViewById(R.id.gridAge);
        textSort = mInflate.findViewById(R.id.textSort);
        textSearch = mInflate.findViewById(R.id.textSearch);
        textAll = mInflate.findViewById(R.id.textAll);
        textLocation = mInflate.findViewById(R.id.textLocation);
    }

    @Override
    protected void initViews() {
        ViewGroup.LayoutParams layoutParams = mRelaTitleStatue.getLayoutParams();
        layoutParams.height = (int) (getResources().getDimension(R.dimen.titleHeight) + ScreenUtils.getStatusBarHeight(getActivity()));
        mRelaTitleStatue.setLayoutParams(layoutParams);
        mRelaTitleStatue.setPadding(0, ScreenUtils.getStatusBarHeight(getActivity()), 0, 0);
        initRecycle();
        initSortRecycler();
        priceAdapter = new PriceAdapter(zPriceBeanList);
        gridPrice.setAdapter(priceAdapter);
        ageAdapter = new PriceAdapter(zAgeBeanList);
        gridAge.setAdapter(ageAdapter);
    }

    private void initRecycle() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) DpUtils.convertDpToPixel(1f, getActivity()), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setRefreshingColorResources(R.color.basic_color);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<Buyer.DataBean>(getActivity()) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_shou_ye;
                return new ShouYeViewHolder(parent, layout, 1);
            }
        });
        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                ApiClient.postJson(getActivity(), url, getOkObject(), new ApiClient.CallBack() {
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
                                MyDialog.showReLoginDialog(getActivity());
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
                intent.setClass(getActivity(), CheLiangXQActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 初始化recyclerview
     */
    private void initSortRecycler() {
        recyclerViewShaiXuan00.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) getResources().getDimension(R.dimen.line_width), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerViewShaiXuan00.addItemDecoration(itemDecoration);
        recyclerViewShaiXuan00.setRefreshingColorResources(R.color.basic_color);
        recyclerViewShaiXuan00.setAdapterWithProgress(adapterSort = new RecyclerArrayAdapter<CarGetsearchdata.DataBean.SortIdBean>(getActivity()) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_sort;
                return new SortViewHolder(parent, layout);
            }
        });
        adapterSort.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                for (int i = 0; i < adapterSort.getAllData().size(); i++) {
                    adapterSort.getItem(i).setSelect(false);
                }
                adapterSort.getItem(position).setSelect(true);
                adapterSort.notifyDataSetChanged();
                shaiXuanVisible = -1;
                viewShaiXuan.setVisibility(View.GONE);
                textSort.setText(adapterSort.getItem(position).getTitle());
                sort_id = adapterSort.getItem(position).getValue();
                onRefresh();
            }
        });
    }

    @Override
    protected void setListeners() {
        rangeSeekbar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                if (minValue.intValue() == 0 && maxValue.intValue() == 60) {
                    textRange.setText("不限");
                } else {
                    if (maxValue.intValue() == 60) {
                        textRange.setText(minValue + "万以上");
                    } else {
                        textRange.setText(minValue + "万至" + maxValue + "万");
                    }
                }
            }
        });
        rangeSeekbar1.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                if (minValue.intValue() == 0 && maxValue.intValue() == 12) {
                    textRange1.setText("不限");
                } else {
                    if (maxValue.intValue() == 12) {
                        textRange1.setText(minValue + "年以上");
                    } else {
                        textRange1.setText(minValue + "年至" + maxValue + "年");
                    }
                }
            }
        });
        mInflate.findViewById(R.id.viewAll).setOnClickListener(this);
        for (int i = 0; i < viewShaiXuan00Arr.length; i++) {
            final int finalI = i;
            viewShaiXuan00Arr[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (shaiXuanVisible != finalI) {
                        viewShaiXuan.setVisibility(View.VISIBLE);
                        shaiXuanVisible = finalI;
                        for (int j = 0; j < viewShaiXuanArr.length; j++) {
                            viewShaiXuanArr[j].setVisibility(View.GONE);
                        }
                        viewShaiXuanArr[finalI].setVisibility(View.VISIBLE);
                    } else {
                        viewShaiXuan.setVisibility(View.GONE);
                        shaiXuanVisible = -1;
                    }
                }
            });
        }
        viewDismiss.setOnClickListener(this);
        gridPrice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                for (int j = 0; j < zPriceBeanList.size(); j++) {
                    zPriceBeanList.get(j).setSelect(false);
                }
                zPriceBeanList.get(i).setSelect(true);
                priceAdapter.notifyDataSetChanged();
                z_price = zPriceBeanList.get(i).getValue();
                if (z_price.size() > 0) {
                    rangeSeekbar.setMinStartValue(z_price.get(0)).setMaxStartValue(z_price.get(1)).apply();
                } else {
                    rangeSeekbar.setMinStartValue(0).setMaxStartValue(60).apply();
                }
                shaiXuanVisible = -1;
                viewShaiXuan.setVisibility(View.GONE);
                onRefresh();
            }
        });
        gridAge.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                for (int j = 0; j < zAgeBeanList.size(); j++) {
                    zAgeBeanList.get(j).setSelect(false);
                }
                zAgeBeanList.get(i).setSelect(true);
                ageAdapter.notifyDataSetChanged();
                z_age = zAgeBeanList.get(i).getValue();
                if (z_age.size() > 0) {
                    rangeSeekbar1.setMinStartValue(z_age.get(0)).setMaxStartValue(z_age.get(1)).apply();
                } else {
                    rangeSeekbar1.setMinStartValue(0).setMaxStartValue(12).apply();
                }
                shaiXuanVisible = -1;
                viewShaiXuan.setVisibility(View.GONE);
                onRefresh();
            }
        });
        mInflate.findViewById(R.id.btnPrice).setOnClickListener(this);
        mInflate.findViewById(R.id.btnAge).setOnClickListener(this);
        mInflate.findViewById(R.id.viewSearch).setOnClickListener(this);
        mInflate.findViewById(R.id.imageZuJi).setOnClickListener(this);
        mInflate.findViewById(R.id.viewLocation).setOnClickListener(this);
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getSearchOkObject() {
        String url = Constant.HOST + Constant.Url.CAR_GETSEARCHDATA;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        return new OkObject(params, url);
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getLocationOkObject() {
        String url = Constant.HOST + Constant.Url.INDEX_MPCITY;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        params.put("lat", lat);
        params.put("lng", lng);
        return new OkObject(params, url);
    }

    @Override
    protected void initData() {

        ApiClient.post(getActivity(), getLocationOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("定位城市", s);
                try {
                    IndexMpcity indexMpcity = GsonUtils.parseJSON(s, IndexMpcity.class);
                    if (indexMpcity.getStatus() == 1) {
                        city_id = indexMpcity.getCityId();
                        textLocation.setText(indexMpcity.getCityName());
                    } else if (indexMpcity.getStatus() == 3) {
                        MyDialog.showReLoginDialog(getActivity());
                    } else {
                        showError(indexMpcity.getInfo());
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
                View viewLoader = LayoutInflater.from(getActivity()).inflate(R.layout.view_loaderror, null);
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


        ApiClient.post(getActivity(), getSearchOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("MaiCheFragment--获取价格、车龄范围数组", s + "");
                try {
                    CarGetsearchdata carGetsearchdata = GsonUtils.parseJSON(s, CarGetsearchdata.class);
                    if (carGetsearchdata.getStatus() == 1) {
                        sortIdBeanList.clear();
                        sortIdBeanList.addAll(carGetsearchdata.getData().getSort_id());
                        for (int i = 0; i < sortIdBeanList.size(); i++) {
                            sortIdBeanList.get(i).setSelect(false);
                        }
                        sortIdBeanList.get(0).setSelect(true);
                        adapterSort.clear();
                        adapterSort.addAll(sortIdBeanList);
                        zPriceBeanList = carGetsearchdata.getData().getZ_price();
                        for (int i = 0; i < zPriceBeanList.size(); i++) {
                            zPriceBeanList.get(i).setSelect(false);
                        }
                        zPriceBeanList.get(0).setSelect(true);
                        zAgeBeanList = carGetsearchdata.getData().getZ_age();
                        for (int i = 0; i < zAgeBeanList.size(); i++) {
                            zAgeBeanList.get(i).setSelect(false);
                        }
                        zAgeBeanList.get(0).setSelect(true);
                        priceAdapter.setList(zPriceBeanList);
                        ageAdapter.setList(zAgeBeanList);
                        priceAdapter.notifyDataSetChanged();
                        ageAdapter.notifyDataSetChanged();
                        onRefresh();
                    } else if (carGetsearchdata.getStatus() == 3) {
                        MyDialog.showReLoginDialog(getActivity());
                    } else {
                        Toast.makeText(getActivity(), carGetsearchdata.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    showError("数据出错");
                }
            }

            @Override
            public void onError() {
                showError("请求失败");
            }

            /**
             * 错误显示
             * @param msg
             */
            private void showError(String msg) {
                View viewLoader = LayoutInflater.from(getActivity()).inflate(R.layout.view_loaderror, null);
                TextView textMsg = viewLoader.findViewById(R.id.textMsg);
                textMsg.setText(msg);
                viewLoader.findViewById(R.id.buttonReLoad).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        recyclerView.showProgress();
                        onRefresh();
                    }
                });
                recyclerView.setErrorView(viewLoader);
                recyclerView.showError();
            }
        });
    }

    String url = Constant.HOST + Constant.Url.CAR;
    private int bid = 0;
    private int bsid = 0;
    private int sort_id = 0;
    private int hotcat_id = 0;
    private int city_id = 0;
    private List<Integer> z_price = new ArrayList<>();
    private List<Integer> z_age = new ArrayList<>();
    private String title = "";

    /**
     * 初始化筛选
     */
    private void initShaiXuan() {
        bid = 0;
        bsid = 0;
        sort_id = 0;
        z_price.clear();
        z_age.clear();
        title = "";
        textSort.setText("默认排序");
        textAll.setText("全部品牌");
        for (int i = 0; i < sortIdBeanList.size(); i++) {
            sortIdBeanList.get(i).setSelect(false);
        }
        sortIdBeanList.get(0).setSelect(true);
        for (int i = 0; i < zPriceBeanList.size(); i++) {
            zPriceBeanList.get(i).setSelect(false);
        }
        zPriceBeanList.get(0).setSelect(true);
        rangeSeekbar.setMinStartValue(0).setMaxStartValue(60).apply();
        for (int i = 0; i < zAgeBeanList.size(); i++) {
            zAgeBeanList.get(i).setSelect(false);
        }
        rangeSeekbar1.setMinStartValue(0).setMaxStartValue(12).apply();
        zAgeBeanList.get(0).setSelect(true);
        priceAdapter.notifyDataSetChanged();
        ageAdapter.notifyDataSetChanged();
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private String getOkObject() {
        MaiChe maiChe;
        if (isLogin) {
            maiChe = new MaiChe(1, "android", userInfo.getUid(), tokenTime, page, bid, bsid, sort_id, hotcat_id, city_id, z_price, z_age, title);
        } else {
            maiChe = new MaiChe(1, "android", page, bid, bsid, sort_id, hotcat_id, city_id, z_price, z_age, title);
        }
        return GsonUtils.parseObject(maiChe);
    }

    @Override
    public void onRefresh() {
        textSearch.setText(title);
        page = 1;
        recyclerView.showProgress();
        ApiClient.postJson(getActivity(), url, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("买车", s);
                try {
                    page++;
                    Buyer buyer = GsonUtils.parseJSON(s, Buyer.class);
                    if (buyer.getStatus() == 1) {
                        List<Buyer.DataBean> dataBeanList = buyer.getData();
                        adapter.clear();
                        adapter.addAll(dataBeanList);
                    } else if (buyer.getStatus() == 3) {
                        MyDialog.showReLoginDialog(getActivity());
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
                View viewLoader = LayoutInflater.from(getActivity()).inflate(R.layout.view_loaderror, null);
                TextView textMsg = viewLoader.findViewById(R.id.textMsg);
                textMsg.setText(msg);
                viewLoader.findViewById(R.id.buttonReLoad).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        recyclerView.showProgress();
                        onRefresh();
                    }
                });
                recyclerView.setErrorView(viewLoader);
                recyclerView.showError();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.RequestResultCode.PIN_PAI && resultCode == Constant.RequestResultCode.PIN_PAI) {
            bid = data.getIntExtra(Constant.IntentKey.ID, 0);
            bsid = data.getIntExtra(Constant.IntentKey.BSID, 0);
            String name = data.getStringExtra(Constant.IntentKey.NAME);
            LogUtil.LogShitou("MaiCheFragment--onActivityResult", "品牌名" + textAll);
            textAll.setText(name);
            onRefresh();
        }
        if (requestCode == Constant.RequestResultCode.CITY&&resultCode ==Constant.RequestResultCode.CITY){
            IndexCitylist.CityEntity.ListEntity cityBean = (IndexCitylist.CityEntity.ListEntity) data.getSerializableExtra(Constant.IntentKey.BEAN);
            city_id = cityBean.getId();
            textLocation.setText(cityBean.getName());
            onRefresh();
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.viewLocation:
                intent.setClass(getActivity(), ChengShiXZActivity.class);
                startActivityForResult(intent, Constant.RequestResultCode.CITY);
                break;
            case R.id.imageZuJi:
                if (isLogin) {
                    intent.setClass(getActivity(), ZuJiActivity.class);
                    startActivity(intent);
                } else {
                    ToLoginActivity.toLoginActivity(getActivity());
                }
                break;
            case R.id.viewSearch:
                shaiXuanVisible = -1;
                viewShaiXuan.setVisibility(View.GONE);
                MyDialog.showSearchDialog(getActivity(), title);
                MyDialog.setOnSearchDoneListener(new MyDialog.OnSearchDoneListener() {
                    @Override
                    public void searchDone(String key) {
                        title = key;
                        onRefresh();
                    }
                });
                break;
            case R.id.btnPrice:
                shaiXuanVisible = -1;
                viewShaiXuan.setVisibility(View.GONE);
                z_price.clear();
                z_price.add(rangeSeekbar.getSelectedMinValue().intValue());
                z_price.add(rangeSeekbar.getSelectedMaxValue().intValue());
                for (int i = 0; i < zPriceBeanList.size(); i++) {
                    zPriceBeanList.get(i).setSelect(false);
                }
                priceAdapter.notifyDataSetChanged();
                onRefresh();
                break;
            case R.id.btnAge:
                shaiXuanVisible = -1;
                viewShaiXuan.setVisibility(View.GONE);
                z_age.clear();
                z_age.add(rangeSeekbar1.getSelectedMinValue().intValue());
                z_age.add(rangeSeekbar1.getSelectedMaxValue().intValue());
                for (int i = 0; i < zAgeBeanList.size(); i++) {
                    zAgeBeanList.get(i).setSelect(false);
                }
                ageAdapter.notifyDataSetChanged();
                onRefresh();
                break;
            case R.id.viewDismiss:
                viewShaiXuan.setVisibility(View.GONE);
                shaiXuanVisible = -1;
                break;
            case R.id.viewAll:
                viewShaiXuan.setVisibility(View.GONE);
                shaiXuanVisible = -1;
                intent.setClass(getActivity(), PinPaiXCActivity.class);
                startActivityForResult(intent, Constant.RequestResultCode.PIN_PAI);
                break;
            default:
                break;
        }
    }

    class PriceAdapter extends BaseAdapter {
        List<CarGetsearchdata.AgePriceBean> list = new ArrayList<>();

        public PriceAdapter(List<CarGetsearchdata.AgePriceBean> list) {
            this.list = list;
        }

        public List<CarGetsearchdata.AgePriceBean> getList() {
            return list;
        }

        public void setList(List<CarGetsearchdata.AgePriceBean> list) {
            this.list = list;
        }

        class ViewHolder {
            public TextView textName;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.item_price_age, null);
                holder.textName = (TextView) convertView.findViewById(R.id.textName);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.textName.setText(list.get(position).getTitle());
            if (list.get(position).isSelect()) {
                holder.textName.setTextColor(ContextCompat.getColor(getActivity(), R.color.textGold));
                holder.textName.setBackgroundResource(R.drawable.shape_gold1dp_priceage3dp);
            } else {
                holder.textName.setTextColor(ContextCompat.getColor(getActivity(), R.color.light_black));
                holder.textName.setBackgroundResource(R.drawable.shape_linegray1dp_3dp);
            }
            return convertView;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (((MainActivity) getActivity()).isSearch) {
            initShaiXuan();
            shaiXuanVisible = -1;
            viewShaiXuan.setVisibility(View.GONE);
            MyDialog.showSearchDialog(getActivity(), title);
            MyDialog.setOnSearchDoneListener(new MyDialog.OnSearchDoneListener() {
                @Override
                public void searchDone(String key) {
                    title = key;
                    onRefresh();
                }
            });
            ((MainActivity) getActivity()).isSearch = false;
        }
        if (((MainActivity) getActivity()).isPinPaiXC) {
            initShaiXuan();
            viewShaiXuan.setVisibility(View.GONE);
            shaiXuanVisible = -1;
            Intent intent = new Intent();
            intent.setClass(getActivity(), PinPaiXCActivity.class);
            startActivityForResult(intent, Constant.RequestResultCode.PIN_PAI);
            ((MainActivity) getActivity()).isPinPaiXC = false;
        }
        if (((MainActivity) getActivity()).isJiaGEXC) {
            initShaiXuan();
            viewShaiXuan.setVisibility(View.VISIBLE);
            shaiXuanVisible = 1;
            for (int j = 0; j < viewShaiXuanArr.length; j++) {
                viewShaiXuanArr[j].setVisibility(View.GONE);
            }
            viewShaiXuanArr[1].setVisibility(View.VISIBLE);
            ((MainActivity) getActivity()).isJiaGEXC = false;
        }
    }

    @Override
    public boolean onBackPressed() {
        if (shaiXuanVisible != -1) {
            shaiXuanVisible = -1;
            viewShaiXuan.setVisibility(View.GONE);
            return true;
        } else {
            return super.onBackPressed();
        }
    }
}
