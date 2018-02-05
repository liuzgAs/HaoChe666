package com.haoche666.buyer.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.haoche666.buyer.R;
import com.haoche666.buyer.base.MyDialog;
import com.haoche666.buyer.base.ZjbBaseActivity;
import com.haoche666.buyer.constant.Constant;
import com.haoche666.buyer.model.ShareBean;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import huisedebi.zjb.mylibrary.util.ScreenUtils;


/**
 * des： 网页
 * author： zhangjiebo
 * date： 2017/7/6 0006 下午 1:40
 */
public class WebActivity extends ZjbBaseActivity implements View.OnClickListener {

    private WebView mWebView;
    private String mUrl;
    private String title;
    private WebSettings mSettings;
    private ProgressBar pb1;
    private TextView mTv_title;
    private View viewBar;
    private ShareBean shareBean;
    private ImageView imageShare;
    private IWXAPI api;
    private boolean isShare = false;
    private BroadcastReceiver reciver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action) {
                case Constant.BroadcastCode.USERINFO:
                    initData();
                    break;
                case Constant.BroadcastCode.WX_SHARE:
                    if (isShare) {
                        MyDialog.showTipDialog(WebActivity.this, "分享成功");
                        isShare = false;
                    }
                    break;
                case Constant.BroadcastCode.WX_SHARE_FAIL:
                    if (isShare) {
                        MyDialog.showTipDialog(WebActivity.this, "取消分享");
                        isShare = false;
                    }
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        init();
    }

    @Override
    protected void initIntent() {
        Intent intent = getIntent();
        mUrl = intent.getStringExtra(Constant.IntentKey.URL);
        title = intent.getStringExtra(Constant.IntentKey.TITLE);
        shareBean = (ShareBean) intent.getSerializableExtra(Constant.IntentKey.BEAN);
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void findID() {
        viewBar = findViewById(R.id.viewBar);
        mWebView = (WebView) findViewById(R.id.webView);
        pb1 = (ProgressBar) findViewById(R.id.progressBar2);
        mTv_title = (TextView) findViewById(R.id.textViewTitle);
        imageShare = (ImageView) findViewById(R.id.imageShare);
        api = WXAPIFactory.createWXAPI(this, Constant.WXAPPID, true);
    }

    @Override
    protected void initViews() {
        ViewGroup.LayoutParams layoutParams = viewBar.getLayoutParams();
        layoutParams.height = (int) (getResources().getDimension(R.dimen.titleHeight) + ScreenUtils.getStatusBarHeight(this));
        viewBar.setLayoutParams(layoutParams);
        if (!TextUtils.isEmpty(title)) {
            mTv_title.setText(title);
        }
        mWebView.loadUrl(mUrl);
        mWebView.setWebViewClient(new WebViewClient());//覆盖第三方浏览器
        mSettings = mWebView.getSettings();
        mSettings.setJavaScriptEnabled(true);
        mSettings.setUseWideViewPort(true);
        mSettings.setLoadWithOverviewMode(true);
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                pb1.setProgress(newProgress);
                if (newProgress == 100) {
                    pb1.setVisibility(View.GONE);
                } else {
                    pb1.setVisibility(View.VISIBLE);
                }
            }
        });
        if (shareBean != null) {
            imageShare.setVisibility(View.VISIBLE);
        } else {
            imageShare.setVisibility(View.GONE);
        }
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
        imageShare.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageShare:
                isShare = true;
                MyDialog.setOnProgressDialogListener(new MyDialog.OnProgressDialogListener() {
                    @Override
                    public void show() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showLoadingDialog();
                            }
                        });
                    }

                    @Override
                    public void hide() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                cancelLoadingDialog();
                            }
                        });
                    }
                });
                MyDialog.share01(WebActivity.this, api, shareBean.getShare_url(), shareBean.getTitle(), shareBean.getDes(), shareBean.getImg());
                break;
            case R.id.imageBack:
                back();
                break;
            default:

                break;
        }
    }

    private void back() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        back();
    }


    @Override
    public void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.BroadcastCode.WX_SHARE);
        filter.addAction(Constant.BroadcastCode.WX_SHARE_FAIL);
        registerReceiver(reciver, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(reciver);
    }
}
