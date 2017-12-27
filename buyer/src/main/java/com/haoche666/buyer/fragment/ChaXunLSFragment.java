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
import com.haoche666.buyer.avtivity.WebActivity;
import com.haoche666.buyer.base.MyDialog;
import com.haoche666.buyer.base.ZjbBaseFragment;
import com.haoche666.buyer.constant.Constant;
import com.haoche666.buyer.model.Carsearch;
import com.haoche666.buyer.model.OkObject;
import com.haoche666.buyer.model.ProductQueryhistory;
import com.haoche666.buyer.util.ApiClient;
import com.haoche666.buyer.viewholder.ChaXunLSViewHolder;
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
 */
public class ChaXunLSFragment extends ZjbBaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private View mInflate;
    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<ProductQueryhistory.DataBean> adapter;
    private int page = 1;
    private int type;

    public ChaXunLSFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public ChaXunLSFragment(int type) {
        this.type = type;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mInflate == null) {
            mInflate = inflater.inflate(R.layout.fragment_cha_xun_l, container, false);
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
        recyclerView = mInflate.findViewById(R.id.recyclerView);
    }

    @Override
    protected void initViews() {
        initRecycle();
    }

    private void initRecycle() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) DpUtils.convertDpToPixel(1f, getActivity()), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setRefreshingColorResources(R.color.basic_color);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<ProductQueryhistory.DataBean>(getActivity()) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_cha_xun_ls;
                return new ChaXunLSViewHolder(parent, layout);
            }
        });
        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
              ApiClient.post(getActivity(), getOkObject(), new ApiClient.CallBack() {
                  @Override
                  public void onSuccess(String s) {
                      try {
                          page++;
                          ProductQueryhistory productQueryhistory = GsonUtils.parseJSON(s, ProductQueryhistory.class);
                          int status = productQueryhistory.getStatus();
                          if (status == 1) {
                              List<ProductQueryhistory.DataBean> dataBeanList = productQueryhistory.getData();
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
                showLoadingDialog();
                ApiClient.post(getActivity(), getJieGuoOkObject(adapter.getItem(position)), new ApiClient.CallBack() {
                    @Override
                    public void onSuccess(String s) {
                        cancelLoadingDialog();
                        LogUtil.LogShitou("ChaWeiBaoActivity--onSuccess", s + "");
                        try {
                            Carsearch carsearch = GsonUtils.parseJSON(s, Carsearch.class);
                            if (carsearch.getStatus() == 1) {
                                Intent intent = new Intent();
                                intent.setClass(getActivity(),WebActivity.class);
                                intent.putExtra(Constant.IntentKey.TITLE,"查维保");
                                intent.putExtra(Constant.IntentKey.URL,carsearch.getUrl());
                                startActivity(intent);
                            } else if (carsearch.getStatus() == 3) {
                                MyDialog.showReLoginDialog(getActivity());
                            } else {
                                Toast.makeText(getActivity(), carsearch.getInfo(), Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Toast.makeText(getActivity(), "数据出错", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError() {
                        cancelLoadingDialog();
                        Toast.makeText(getActivity(), "请求失败", Toast.LENGTH_SHORT).show();
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
    private OkObject getJieGuoOkObject(ProductQueryhistory.DataBean dataBean) {
        String url = Constant.HOST + Constant.Url.CARSEARCH;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime",tokenTime);
        }
        params.put("order_no",dataBean.getOrder_no());
        params.put("type_id",type+"");
        return new OkObject(params, url);
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.PRODUCT_QUERYHISTORY;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        params.put("p", page + "");
        params.put("type_id", type + "");
        return new OkObject(params, url);
    }

    @Override
    public void onRefresh() {
        page = 1;
        ApiClient.post(getActivity(), getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("查询历史"+type, s);
                try {
                    page++;
                    ProductQueryhistory productQueryhistory = GsonUtils.parseJSON(s, ProductQueryhistory.class);
                    if (productQueryhistory.getStatus() == 1) {
                        List<ProductQueryhistory.DataBean> dataBeanList = productQueryhistory.getData();
                        adapter.clear();
                        adapter.addAll(dataBeanList);
                    } else if (productQueryhistory.getStatus() == 3) {
                        MyDialog.showReLoginDialog(getActivity());
                    } else {
                        showError(productQueryhistory.getInfo());
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
}
