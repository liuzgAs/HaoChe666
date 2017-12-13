package com.haoche666.buyer.avtivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.haoche666.buyer.R;
import com.haoche666.buyer.base.MyDialog;
import com.haoche666.buyer.base.ZjbBaseActivity;
import com.haoche666.buyer.constant.Constant;
import com.haoche666.buyer.model.Article;
import com.haoche666.buyer.model.OkObject;
import com.haoche666.buyer.util.ApiClient;
import com.haoche666.buyer.viewholder.WenZhangViewHolder;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.luoxudong.app.threadpool.ThreadPoolHelp;

import java.util.HashMap;
import java.util.List;

import huisedebi.zjb.mylibrary.util.GsonUtils;
import huisedebi.zjb.mylibrary.util.LogUtil;

public class WenZhangLBActivity extends ZjbBaseActivity implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<Article.DataBean> adapter;
    private View viewSearch;
    private TextView textSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wen_zhang_lb);
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
        viewSearch = findViewById(R.id.viewSearch);
        textSearch = (TextView) findViewById(R.id.textSearch);
    }

    @Override
    protected void initViews() {
        ((TextView) findViewById(R.id.textViewTitle)).setText("文章列表");
        initRecycler();
    }

    /**
     * 初始化recyclerview
     */
    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) getResources().getDimension(R.dimen.line_width), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setRefreshingColorResources(R.color.basic_color);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<Article.DataBean>(WenZhangLBActivity.this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_wenzhang;
                return new WenZhangViewHolder(parent, layout);
            }
        });
        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                ApiClient.post(WenZhangLBActivity.this, getOkObject(), new ApiClient.CallBack() {
                    @Override
                    public void onSuccess(String s) {
                        try {
                            page++;
                            Article article = GsonUtils.parseJSON(s, Article.class);
                            int status = article.getStatus();
                            if (status == 1) {
                                List<Article.DataBean> dataBeanList = article.getData();
                                adapter.addAll(dataBeanList);
                            } else if (status == 3) {
                                MyDialog.showReLoginDialog(WenZhangLBActivity.this);
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
                Intent intent = new Intent();
                intent.setClass(WenZhangLBActivity.this,WebActivity.class);
                intent.putExtra(Constant.IntentKey.TITLE,adapter.getItem(position).getTitle());
                intent.putExtra(Constant.IntentKey.URL,adapter.getItem(position).getUrl());
                startActivity(intent);
            }
        });
        recyclerView.setRefreshListener(this);
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
        viewSearch.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        onRefresh();
    }

    private int page = 1;
    private String keywords = "";

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.ARTICLE;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime",tokenTime);
        }
        params.put("p", page + "");
        params.put("keywords", keywords + "");
        return new OkObject(params, url);
    }

    @Override
    public void onRefresh() {
        textSearch.setText(keywords);
        page = 1;
        ApiClient.post(this, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("文章列表", s);
                try {
                    page++;
                    Article article = GsonUtils.parseJSON(s, Article.class);
                    if (article.getStatus() == 1) {
                        List<Article.DataBean> dataBeanList = article.getData();
                        adapter.clear();
                        adapter.addAll(dataBeanList);
                    } else if (article.getStatus() == 3) {
                        MyDialog.showReLoginDialog(WenZhangLBActivity.this);
                    } else {
                        showError(article.getInfo());
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
                View viewLoader = LayoutInflater.from(WenZhangLBActivity.this).inflate(R.layout.view_loaderror, null);
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

    public void showKeyboard(EditText editSearch) {
        if(editSearch!=null){
            //设置可获得焦点
            editSearch.setFocusable(true);
            editSearch.setFocusableInTouchMode(true);
            //请求获得焦点
            editSearch.requestFocus();
            //调用系统输入法
            InputMethodManager inputManager = (InputMethodManager) editSearch
                    .getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.showSoftInput(editSearch, 0);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.viewSearch:
                View dialog_tu_pian = LayoutInflater.from(this).inflate(R.layout.dialog_search, null);
                final AlertDialog alertDialog = new AlertDialog.Builder(this, R.style.dialog)
                        .setView(dialog_tu_pian)
                        .create();
                alertDialog.show();
                final EditText editSearch = dialog_tu_pian.findViewById(R.id.editSearch);
                editSearch.setText(keywords);
                editSearch.setSelection(keywords.length());
                dialog_tu_pian.findViewById(R.id.imageSearch).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        InputMethodManager imm = (InputMethodManager) v
                                .getContext().getSystemService(
                                        Context.INPUT_METHOD_SERVICE);
                        if (imm.isActive()) {
                            imm.hideSoftInputFromWindow(
                                    v.getApplicationWindowToken(), 0);
                        }
                        keywords = editSearch.getText().toString().trim();
                        alertDialog.dismiss();
                        initData();
                    }
                });
                editSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent keyEvent) {
                         /*判断是否是“GO”键*/
                        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    /*隐藏软键盘*/
                            InputMethodManager imm = (InputMethodManager) v
                                    .getContext().getSystemService(
                                            Context.INPUT_METHOD_SERVICE);
                            if (imm.isActive()) {
                                imm.hideSoftInputFromWindow(
                                        v.getApplicationWindowToken(), 0);
                            }
                            keywords = editSearch.getText().toString().trim();
                            alertDialog.dismiss();
                            initData();
                            return true;
                        }
                        return false;
                    }
                });
                Window dialogWindow = alertDialog.getWindow();
                dialogWindow.setGravity(Gravity.TOP);
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                DisplayMetrics d = getResources().getDisplayMetrics();
                lp.width = (int) (d.widthPixels * 1);
                dialogWindow.setAttributes(lp);
                ThreadPoolHelp.Builder
                        .cached()
                        .builder()
                        .execute(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(200);
                                    showKeyboard(editSearch);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                break;
            case R.id.imageBack:
                finish();
                break;
            default:
                break;
        }
    }
}
