package com.haoche666.buyer.avtivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.haoche666.buyer.R;
import com.haoche666.buyer.base.MyDialog;
import com.haoche666.buyer.base.ZjbBaseActivity;
import com.haoche666.buyer.constant.Constant;
import com.haoche666.buyer.model.AttentionGetattention;
import com.haoche666.buyer.model.DuiBi;
import com.haoche666.buyer.model.OkObject;
import com.haoche666.buyer.util.ApiClient;
import com.haoche666.buyer.viewholder.CheLiangDBViewHolder;
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
 * 车辆对比
 *
 * @author Administrator
 */
public class CheLiangDBActivity extends ZjbBaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<AttentionGetattention.DataBean> adapter;
    private int page = 1;
    private ImageView imageBianJi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_che_liang_db);
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
        recyclerView = (EasyRecyclerView) findViewById(R.id.recyclerView);
        imageBianJi = (ImageView) findViewById(R.id.imageBianJi);
    }

    @Override
    protected void initViews() {
        initRecycle();
    }

    private void initRecycle() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(CheLiangDBActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) DpUtils.convertDpToPixel(1f, CheLiangDBActivity.this), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setRefreshingColorResources(R.color.basic_color);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<AttentionGetattention.DataBean>(CheLiangDBActivity.this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_che_liang_db;
                return new CheLiangDBViewHolder(parent, layout);
            }
        });
        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                ApiClient.post(CheLiangDBActivity.this, getOkObject(), new ApiClient.CallBack() {
                    @Override
                    public void onSuccess(String s) {
                        try {
                            page++;
                            AttentionGetattention attentionGetattention = GsonUtils.parseJSON(s, AttentionGetattention.class);
                            int status = attentionGetattention.getStatus();
                            if (status == 1) {
                                List<AttentionGetattention.DataBean> dataBeanList = attentionGetattention.getData();
                                for (int i = 0; i < dataBeanList.size(); i++) {
                                    dataBeanList.get(i).setSelect(false);
                                }
                                adapter.addAll(dataBeanList);
                            } else if (status == 3) {
                                MyDialog.showReLoginDialog(CheLiangDBActivity.this);
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
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (adapter.getItem(position).isSelect()) {
                    adapter.getItem(position).setSelect(false);
                } else {
                    adapter.getItem(position).setSelect(true);
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
        findViewById(R.id.buttonDuiBi).setOnClickListener(this);
        findViewById(R.id.imageAdd).setOnClickListener(this);
        imageBianJi.setOnClickListener(this);
        recyclerView.getEmptyView().findViewById(R.id.buttonAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCar();
            }
        });
    }

    /**
     * des： 添加对比车辆
     * author： ZhangJieBo
     * date： 2017/12/26/026 16:07
     */
    private void addCar() {
        Intent intent = new Intent();
        intent.setAction(Constant.BroadcastCode.DUI_BI);
        sendBroadcast(intent);
    }

    @Override
    protected void initData() {
        onRefresh();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageBianJi:
                showDeleteDialog();
                break;
            case R.id.imageAdd:
                addCar();
                break;
            case R.id.buttonDuiBi:
                List<AttentionGetattention.DataBean> allData = adapter.getAllData();
                int count = 0;
                for (int i = 0; i < allData.size(); i++) {
                    if (allData.get(i).isSelect()) {
                        count++;
                    }
                }
                if (count < 2) {
                    Toast.makeText(CheLiangDBActivity.this, "请选择至少两辆车辆", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (count > 10) {
                    Toast.makeText(CheLiangDBActivity.this, "对比车辆不能超过十辆", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra(Constant.IntentKey.BEAN, new DuiBi(allData));
                intent.setClass(CheLiangDBActivity.this, DuiBiActivity.class);
                startActivity(intent);
                break;
            case R.id.imageBack:
                finish();
                break;

            default:

                break;
        }
    }

    /**
     * 删除pop
     */
    private void showDeleteDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_delete, null);
        Dialog dialog = new Dialog(this);
        dialog.setContentView(view);
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.x = (int) DpUtils.convertDpToPixel(20, this);
        lp.y = (int) DpUtils.convertDpToPixel(20, this);
        dialogWindow.setGravity(Gravity.RIGHT | Gravity.TOP);
        dialogWindow.setAttributes(lp);
        dialog.show();
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.ATTENTION_GETATTENTION;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        params.put("type_id", 4 + "");
        params.put("p", page + "");
        return new OkObject(params, url);
    }

    @Override
    public void onRefresh() {
        page = 1;
        ApiClient.post(this, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("对比车辆", s);
                try {
                    page++;
                    AttentionGetattention attentionGetattention = GsonUtils.parseJSON(s, AttentionGetattention.class);
                    if (attentionGetattention.getStatus() == 1) {
                        List<AttentionGetattention.DataBean> dataBeanList = attentionGetattention.getData();
                        for (int i = 0; i < dataBeanList.size(); i++) {
                            dataBeanList.get(i).setSelect(false);
                        }
                        adapter.clear();
                        adapter.addAll(dataBeanList);
                    } else if (attentionGetattention.getStatus() == 3) {
                        MyDialog.showReLoginDialog(CheLiangDBActivity.this);
                    } else {
                        showError(attentionGetattention.getInfo());
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
                View viewLoader = LayoutInflater.from(CheLiangDBActivity.this).inflate(R.layout.view_loaderror, null);
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
