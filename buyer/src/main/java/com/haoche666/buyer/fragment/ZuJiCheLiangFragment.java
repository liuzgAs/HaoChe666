package com.haoche666.buyer.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.haoche666.buyer.R;
import com.haoche666.buyer.activity.CheLiangXQActivity;
import com.haoche666.buyer.base.MyDialog;
import com.haoche666.buyer.base.ZjbBaseFragment;
import com.haoche666.buyer.constant.Constant;
import com.haoche666.buyer.model.Attention;
import com.haoche666.buyer.model.CarHistory;
import com.haoche666.buyer.model.OkObject;
import com.haoche666.buyer.util.ApiClient;
import com.haoche666.buyer.viewholder.ZuJiCLViewHolder;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;

import java.util.HashMap;
import java.util.List;

import huisedebi.zjb.mylibrary.util.DpUtils;
import huisedebi.zjb.mylibrary.util.GsonUtils;
import huisedebi.zjb.mylibrary.util.LogUtil;

/**
 * A simple {@link Fragment} subclass.
 *
 * @author Administrator
 */
public class ZuJiCheLiangFragment extends ZjbBaseFragment implements SwipeRefreshLayout.OnRefreshListener {


    private View mInflate;
    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<CarHistory.DataBean> adapter;
    private int page = 1;
    private boolean isFromDuiBi = false;

    public ZuJiCheLiangFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public ZuJiCheLiangFragment(boolean isFromDuiBi) {
        this.isFromDuiBi = isFromDuiBi;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mInflate == null) {
            mInflate = inflater.inflate(R.layout.fragment_zu_ji, container, false);
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
    protected void initSP() {

    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void findID() {
        recyclerView = mInflate.findViewById(R.id.recyclerView);
    }

    @Override
    protected void initViews() {
        initRecycle();
    }

    private void initRecycle() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) DpUtils.convertDpToPixel(1f, mContext), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setRefreshingColorResources(R.color.basic_color);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<CarHistory.DataBean>(mContext) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_zu_ji_che_liang;
                return new ZuJiCLViewHolder(parent, layout);
            }
        });
        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                page++;
                ApiClient.post(mContext, getOkObject(), new ApiClient.CallBack() {
                    @Override
                    public void onSuccess(String s) {
                        try {
                            page++;
                            CarHistory carHistory = GsonUtils.parseJSON(s, CarHistory.class);
                            int status = carHistory.getStatus();
                            if (status == 1) {
                                List<CarHistory.DataBean> dataBeanList = carHistory.getData();
                                adapter.addAll(dataBeanList);
                            } else if (status == 3) {
                                MyDialog.showReLoginDialog(mContext);
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
                if (isFromDuiBi) {
                    guanZhuCheLiang(adapter.getItem(position).getId());
                } else {
                    Intent intent = new Intent();
                    intent.setClass(mContext, CheLiangXQActivity.class);
                    intent.putExtra(Constant.IntentKey.ID, adapter.getItem(position).getId());
                    startActivity(intent);
                }
            }

            /**
             * des： 网络请求参数
             * author： ZhangJieBo
             * date： 2017/8/28 0028 上午 9:55
             */
            private OkObject getGuanZhuCLOkObject(int id) {
                String url = Constant.HOST + Constant.Url.ATTENTION;
                HashMap<String, String> params = new HashMap<>();
                if (isLogin) {
                    params.put("uid", userInfo.getUid());
                    params.put("tokenTime", tokenTime);
                }
                params.put("type_id", "4");
                params.put("car_store_id",id+ "");
                params.put("a_status", "1");
                return new OkObject(params, url);
            }

            /**
             * des： 关注车行
             * author： ZhangJieBo
             * date： 2017/12/25/025 13:45
             */
            private void guanZhuCheLiang(int id) {
                showLoadingDialog();
                ApiClient.post(mContext, getGuanZhuCLOkObject(id), new ApiClient.CallBack() {
                    @Override
                    public void onSuccess(String s) {
                        cancelLoadingDialog();
                        LogUtil.LogShitou("CheHangXXActivity--onSuccess", s + "");
                        try {
                            Attention simpleInfo = GsonUtils.parseJSON(s, Attention.class);
                            if (simpleInfo.getStatus() == 1) {
                                mContext.setResult(Constant.RequestResultCode.DUI_BI);
                                mContext.finish();
                            } else if (simpleInfo.getStatus() == 3) {
                                MyDialog.showReLoginDialog(mContext);
                            } else {
                            }
                            Toast.makeText(mContext, simpleInfo.getInfo(), Toast.LENGTH_SHORT).show();
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
        });
    }

    @Override
    protected void setListeners() {

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
        String url = Constant.HOST + Constant.Url.CAR_HISTORY;
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
                LogUtil.LogShitou("足迹车辆", s);
                try {
                    page++;
                    CarHistory carHistory = GsonUtils.parseJSON(s, CarHistory.class);
                    if (carHistory.getStatus() == 1) {
                        adapter.clear();
                        List<CarHistory.DataBean> dataBeanList = carHistory.getData();
                        adapter.addAll(dataBeanList);
                    } else if (carHistory.getStatus() == 3) {
                        MyDialog.showReLoginDialog(mContext);
                    } else {
                        showError(carHistory.getInfo());
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
}
