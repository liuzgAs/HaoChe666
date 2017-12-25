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

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.haoche666.buyer.R;
import com.haoche666.buyer.avtivity.CheLiangXQActivity;
import com.haoche666.buyer.avtivity.MainActivity;
import com.haoche666.buyer.avtivity.PinPaiXCActivity;
import com.haoche666.buyer.base.MyDialog;
import com.haoche666.buyer.base.ZjbBaseFragment;
import com.haoche666.buyer.constant.Constant;
import com.haoche666.buyer.model.Buyer;
import com.haoche666.buyer.model.MaiChe;
import com.haoche666.buyer.model.PriceAge;
import com.haoche666.buyer.model.Sort;
import com.haoche666.buyer.util.ApiClient;
import com.haoche666.buyer.viewholder.ShouYeViewHolder;
import com.haoche666.buyer.viewholder.SortViewHolder;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;

import java.util.ArrayList;
import java.util.List;

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
    private RecyclerArrayAdapter<Sort> adapterSort;
    private int page = 1;
    private TextView textRange;
    private TextView textRange1;
    private CrystalRangeSeekbar rangeSeekbar;
    private CrystalRangeSeekbar rangeSeekbar1;
    private EasyRecyclerView recyclerViewShaiXuan00;
    private List<Sort> sortList = new ArrayList<>();
    private List<PriceAge> priceList = new ArrayList<>();
    private List<PriceAge> ageList = new ArrayList<>();
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
    }

    @Override
    protected void initViews() {
        ViewGroup.LayoutParams layoutParams = mRelaTitleStatue.getLayoutParams();
        layoutParams.height = (int) (getResources().getDimension(R.dimen.titleHeight) + ScreenUtils.getStatusBarHeight(getActivity()));
        mRelaTitleStatue.setLayoutParams(layoutParams);
        mRelaTitleStatue.setPadding(0, ScreenUtils.getStatusBarHeight(getActivity()), 0, 0);
        initRecycle();
        initSortRecycler();
        List<Integer> list00 = new ArrayList<>();
        list00.clear();
        list00.add(0);
        list00.add(60);
        priceList.add(new PriceAge("不限", list00, true));
        List<Integer> list01 = new ArrayList<>();
        list01.clear();
        list01.add(0);
        list01.add(3);
        priceList.add(new PriceAge("0-3万", list01, false));
        List<Integer> list02 = new ArrayList<>();
        list02.clear();
        list02.add(3);
        list02.add(5);
        priceList.add(new PriceAge("3-5万", list02, false));
        List<Integer> list03 = new ArrayList<>();
        list03.clear();
        list03.add(5);
        list03.add(10);
        priceList.add(new PriceAge("5-10万", list03, false));
        List<Integer> list04 = new ArrayList<>();
        list04.clear();
        list04.add(10);
        list04.add(15);
        priceList.add(new PriceAge("10-15万", list04, false));
        List<Integer> list05 = new ArrayList<>();
        list05.clear();
        list05.add(15);
        list05.add(20);
        priceList.add(new PriceAge("15-20万", list05, false));
        List<Integer> list06 = new ArrayList<>();
        list06.clear();
        list06.add(20);
        list06.add(30);
        priceList.add(new PriceAge("20-30万", list06, false));
        List<Integer> list07 = new ArrayList<>();
        list07.clear();
        list07.add(30);
        list07.add(50);
        priceList.add(new PriceAge("30-50万", list07, false));
        List<Integer> list08 = new ArrayList<>();
        list08.clear();
        list08.add(50);
        list08.add(60);
        priceList.add(new PriceAge("50万以上", list08, false));
        priceAdapter = new PriceAdapter(priceList);
        gridPrice.setAdapter(priceAdapter);
        List<Integer> list10 = new ArrayList<>();
        list10.clear();
        list10.add(0);
        list10.add(60);
        ageList.add(new PriceAge("不限", list10, true));
        List<Integer> list11 = new ArrayList<>();
        list11.clear();
        list11.add(0);
        list11.add(1);
        ageList.add(new PriceAge("1年内", list11, false));
        List<Integer> list12 = new ArrayList<>();
        list12.clear();
        list12.add(0);
        list12.add(2);
        ageList.add(new PriceAge("2年内", list12, false));
        List<Integer> list13 = new ArrayList<>();
        list13.clear();
        list13.add(0);
        list13.add(3);
        ageList.add(new PriceAge("3年内", list13, false));
        List<Integer> list14 = new ArrayList<>();
        list14.clear();
        list14.add(0);
        list14.add(5);
        ageList.add(new PriceAge("5年内", list14, false));
        List<Integer> list15 = new ArrayList<>();
        list15.clear();
        list15.add(0);
        list15.add(8);
        ageList.add(new PriceAge("8年内", list15, false));
        List<Integer> list16 = new ArrayList<>();
        list16.clear();
        list16.add(0);
        list16.add(10);
        ageList.add(new PriceAge("10年内", list16, false));
        ageAdapter = new PriceAdapter(ageList);
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
                intent.setClass(getActivity(), CheLiangXQActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 初始化recyclerview
     */
    private void initSortRecycler() {
        sortList.add(new Sort(0, "默认排序", true));
        sortList.add(new Sort(1, "最新上架", false));
        sortList.add(new Sort(2, "价格最低", false));
        sortList.add(new Sort(3, "价格最高", false));
        sortList.add(new Sort(4, "车龄最短", false));
        sortList.add(new Sort(5, "里程最少", false));
        recyclerViewShaiXuan00.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) getResources().getDimension(R.dimen.line_width), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerViewShaiXuan00.addItemDecoration(itemDecoration);
        recyclerViewShaiXuan00.setRefreshingColorResources(R.color.basic_color);
        recyclerViewShaiXuan00.setAdapterWithProgress(adapterSort = new RecyclerArrayAdapter<Sort>(getActivity()) {
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
                textSort.setText(adapterSort.getItem(position).getName());
                sort_id = adapterSort.getItem(position).getSort_id();
                initData();
            }
        });
        adapterSort.clear();
        adapterSort.addAll(sortList);
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
                for (int j = 0; j < priceList.size(); j++) {
                    priceList.get(j).setSelect(false);
                }
                priceList.get(i).setSelect(true);
                priceAdapter.notifyDataSetChanged();
                z_price = priceList.get(i).getValue();
                LogUtil.LogShitou("MaiCheFragment--onItemClick", "" + z_price.get(0) + "-" + z_price.get(1));
                rangeSeekbar.setMinStartValue(z_price.get(0)).setMaxStartValue(z_price.get(1)).apply();
                shaiXuanVisible = -1;
                viewShaiXuan.setVisibility(View.GONE);
                initData();
            }
        });
        gridAge.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                for (int j = 0; j < ageList.size(); j++) {
                    ageList.get(j).setSelect(false);
                }
                ageList.get(i).setSelect(true);
                ageAdapter.notifyDataSetChanged();
                z_age = ageList.get(i).getValue();
                rangeSeekbar1.setMinStartValue(z_age.get(0)).setMaxStartValue(z_age.get(1)).apply();
                shaiXuanVisible = -1;
                viewShaiXuan.setVisibility(View.GONE);
                initData();
            }
        });
        mInflate.findViewById(R.id.btnPrice).setOnClickListener(this);
        mInflate.findViewById(R.id.btnAge).setOnClickListener(this);
        mInflate.findViewById(R.id.viewSearch).setOnClickListener(this);
    }

    @Override
    protected void initData() {
        onRefresh();
    }

    String url = Constant.HOST + Constant.Url.CAR;
    private int bid = 0;
    private int bsid = 0;
    private int sort_id = 0;
    private List<Integer> z_price = new ArrayList<>();
    private List<Integer> z_age = new ArrayList<>();
    private String title = "";

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private String getOkObject() {
        MaiChe maiChe;
        if (isLogin) {
            maiChe = new MaiChe(1, "android", userInfo.getUid(), tokenTime, page, bid,bsid, sort_id, z_price, z_age, title);
        } else {
            maiChe = new MaiChe(1, "android", page, bid,bsid, sort_id, z_price, z_age, title);
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
                        initData();
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
            LogUtil.LogShitou("MaiCheFragment--onActivityResult", "品牌名"+textAll);
            textAll.setText(name);
            initData();
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.viewSearch:
                shaiXuanVisible = -1;
                viewShaiXuan.setVisibility(View.GONE);
                MyDialog.showSearchDialog(getActivity(), title);
                MyDialog.setOnSearchDoneListener(new MyDialog.OnSearchDoneListener() {
                    @Override
                    public void searchDone(String key) {
                        title = key;
                        initData();
                    }
                });
                break;
            case R.id.btnPrice:
                shaiXuanVisible = -1;
                viewShaiXuan.setVisibility(View.GONE);
                z_price.clear();
                z_price.add(rangeSeekbar.getSelectedMinValue().intValue());
                z_price.add(rangeSeekbar.getSelectedMaxValue().intValue());
                for (int i = 0; i < priceList.size(); i++) {
                    priceList.get(i).setSelect(false);
                }
                priceAdapter.notifyDataSetChanged();
                initData();
                break;
            case R.id.btnAge:
                shaiXuanVisible = -1;
                viewShaiXuan.setVisibility(View.GONE);
                z_age.clear();
                z_age.add(rangeSeekbar1.getSelectedMinValue().intValue());
                z_age.add(rangeSeekbar1.getSelectedMaxValue().intValue());
                for (int i = 0; i < ageList.size(); i++) {
                    ageList.get(i).setSelect(false);
                }
                ageAdapter.notifyDataSetChanged();
                initData();
                break;
            case R.id.viewDismiss:
                viewShaiXuan.setVisibility(View.GONE);
                shaiXuanVisible = -1;
                break;
            case R.id.viewAll:
                viewShaiXuan.setVisibility(View.GONE);
                shaiXuanVisible = -1;
                intent.setClass(getActivity(), PinPaiXCActivity.class);
                startActivityForResult(intent,Constant.RequestResultCode.PIN_PAI);
                break;
            default:
                break;
        }
    }

    class PriceAdapter extends BaseAdapter {
        List<PriceAge> list = new ArrayList<>();

        public PriceAdapter(List<PriceAge> list) {
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
            holder.textName.setText(list.get(position).getName());
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
        if (((MainActivity) getActivity()).isPinPaiXC){
            viewShaiXuan.setVisibility(View.GONE);
            shaiXuanVisible = -1;
            Intent intent = new Intent();
            intent.setClass(getActivity(),PinPaiXCActivity.class);
            startActivityForResult(intent, Constant.RequestResultCode.PIN_PAI);
            ((MainActivity) getActivity()).isPinPaiXC = false;
        }
        if (((MainActivity)getActivity()).isJiaGEXC){
            viewShaiXuan.setVisibility(View.VISIBLE);
            shaiXuanVisible = 1;
            for (int j = 0; j < viewShaiXuanArr.length; j++) {
                viewShaiXuanArr[j].setVisibility(View.GONE);
            }
            viewShaiXuanArr[1].setVisibility(View.VISIBLE);
            ((MainActivity) getActivity()).isJiaGEXC = false;
        }
    }
}
