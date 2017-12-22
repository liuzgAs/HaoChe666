package com.haoche666.buyer.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.haoche666.buyer.avtivity.PinPaiXCActivity;
import com.haoche666.buyer.base.MyDialog;
import com.haoche666.buyer.base.ZjbBaseFragment;
import com.haoche666.buyer.constant.Constant;
import com.haoche666.buyer.model.MaiChe;
import com.haoche666.buyer.model.PriceAge;
import com.haoche666.buyer.model.SimpleInfo;
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
    private RecyclerArrayAdapter<Integer> adapter;
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
    }

    @Override
    protected void initViews() {
        ViewGroup.LayoutParams layoutParams = mRelaTitleStatue.getLayoutParams();
        layoutParams.height = (int) (getResources().getDimension(R.dimen.titleHeight) + ScreenUtils.getStatusBarHeight(getActivity()));
        mRelaTitleStatue.setLayoutParams(layoutParams);
        mRelaTitleStatue.setPadding(0, ScreenUtils.getStatusBarHeight(getActivity()), 0, 0);
        initRecycle();
        initSortRecycler();
        List<Integer> list = new ArrayList<>();
        list.clear();
        list.add(0);
        list.add(60);
        priceList.add(new PriceAge("不限", list, true));
        list.clear();
        list.add(0);
        list.add(3);
        priceList.add(new PriceAge("0-3万", list, false));
        list.clear();
        list.add(3);
        list.add(5);
        priceList.add(new PriceAge("3-5万", list, false));
        list.clear();
        list.add(5);
        list.add(10);
        priceList.add(new PriceAge("5-10万", list, false));
        list.clear();
        list.add(10);
        list.add(15);
        priceList.add(new PriceAge("10-15万", list, false));
        list.clear();
        list.add(15);
        list.add(20);
        priceList.add(new PriceAge("15-20万", list, false));
        list.clear();
        list.add(20);
        list.add(30);
        priceList.add(new PriceAge("20-30万", list, false));
        list.clear();
        list.add(30);
        list.add(50);
        priceList.add(new PriceAge("30-50万", list, false));
        list.clear();
        list.add(50);
        list.add(60);
        priceList.add(new PriceAge("50万以上", list, false));
        priceAdapter = new PriceAdapter(priceList);
        gridPrice.setAdapter(priceAdapter);
        list.clear();
        list.add(0);
        list.add(60);
        ageList.add(new PriceAge("不限", list, true));
        list.clear();
        list.add(0);
        list.add(1);
        ageList.add(new PriceAge("1年内", list, false));
        list.clear();
        list.add(0);
        list.add(2);
        ageList.add(new PriceAge("2年内", list, false));
        list.clear();
        list.add(0);
        list.add(3);
        ageList.add(new PriceAge("3年内", list, false));
        list.clear();
        list.add(0);
        list.add(5);
        ageList.add(new PriceAge("5年内", list, false));
        list.clear();
        list.add(0);
        list.add(8);
        ageList.add(new PriceAge("8年内", list, false));
        list.clear();
        list.add(0);
        list.add(10);
        ageList.add(new PriceAge("10年内", list, false));
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
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<Integer>(getActivity()) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_shou_ye;
                return new ShouYeViewHolder(parent, layout, 1);
            }
        });
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                View view = new View(getActivity());
                view.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) DpUtils.convertDpToPixel(5f, getActivity())));
                return view;
            }

            @Override
            public void onBindView(View headerView) {

            }
        });
        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
//                page++;
//                adapter.addAll(DataProvider.getPersonList(page));
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
                rangeSeekbar.setMinStartValue(z_price.get(0));
                rangeSeekbar.setMaxStartValue(z_price.get(1));
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
                rangeSeekbar1.setMinStartValue(z_age.get(0));
                rangeSeekbar1.setMaxStartValue(z_age.get(1));
                shaiXuanVisible = -1;
                viewShaiXuan.setVisibility(View.GONE);
                initData();
            }
        });
        mInflate.findViewById(R.id.btnPrice).setOnClickListener(this);
        mInflate.findViewById(R.id.btnAge).setOnClickListener(this);
    }

    @Override
    protected void initData() {
        onRefresh();
    }

    private int bid = 0;
    private int sort_id = 0;
    private List<Integer> z_price = new ArrayList<>();
    private List<Integer> z_age = new ArrayList<>();
    private String title;

    @Override
    public void onRefresh() {
        page = 1;
        recyclerView.showProgress();
        String url = Constant.HOST + Constant.Url.CAR;
        MaiChe maiChe;
        if (isLogin) {
            maiChe = new MaiChe(1, "android", userInfo.getUid(), tokenTime, page, bid, sort_id, z_price, z_age, title);
        } else {
            maiChe = new MaiChe(1, "android", page, bid, sort_id, z_price, z_age, title);
        }
        ApiClient.postJson(getActivity(), url, GsonUtils.parseObject(maiChe), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("买车", s);
                try {
                    page++;
                    SimpleInfo simpleInfo = GsonUtils.parseJSON(s, SimpleInfo.class);
                    if (simpleInfo.getStatus() == 1) {

                    } else if (simpleInfo.getStatus() == 3) {
                        MyDialog.showReLoginDialog(getActivity());
                    } else {
                        showError(simpleInfo.getInfo());
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
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
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
                startActivity(intent);
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
}
