package hudongchuangxiang.haoche666.seller.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.taobao.sophix.SophixManager;

import hudongchuangxiang.haoche666.seller.R;
import hudongchuangxiang.haoche666.seller.application.MyApplication;
import hudongchuangxiang.haoche666.seller.base.ZjbBaseNotLeftActivity;
import hudongchuangxiang.haoche666.seller.constant.Constant;
import hudongchuangxiang.haoche666.seller.fragment.ChaXunFWFragment;
import hudongchuangxiang.haoche666.seller.fragment.CheLiangGLFragment;
import hudongchuangxiang.haoche666.seller.fragment.ShouYeFragment;
import hudongchuangxiang.haoche666.seller.fragment.WoDeFragment;
import hudongchuangxiang.haoche666.seller.interfacepage.OnPatchLister;
import huisedebi.zjb.mylibrary.util.DpUtils;
import huisedebi.zjb.mylibrary.util.UpgradeUtils;

public class MainActivity extends ZjbBaseNotLeftActivity {
    private String[] tabsItem = new String[4];
    private Class[] fragment = new Class[]{
            ShouYeFragment.class,
            CheLiangGLFragment.class,
            ChaXunFWFragment.class,
            WoDeFragment.class,
    };
    private int[] imgRes = new int[]{
            R.drawable.selector_item01,
            R.drawable.selector_item02,
            R.drawable.selector_item03,
            R.drawable.selector_item04,
    };
    public FragmentTabHost mTabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //检查更新
        UpgradeUtils.checkUpgrade(MainActivity.this, Constant.HOST + Constant.Url.INDEX_VERSION);
        init();
    }

    @Override
    protected void initSP() {
        SophixManager.getInstance().queryAndLoadNewPatch();
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void findID() {
        mTabHost = findViewById(R.id.tabHost);
    }

    @Override
    protected void initViews() {
        tabsItem[0] = "首页";
        tabsItem[1] = "车辆管理";
        tabsItem[2] = "查询服务";
        tabsItem[3] = "我的";
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtab);
        for (int i = 0; i < tabsItem.length; i++) {
            View inflate = getLayoutInflater().inflate(R.layout.tabs_item, null);
            TextView tabsText =  inflate.findViewById(R.id.tabs_text);
            ImageView tabsImg =  inflate.findViewById(R.id.tabs_img);
            if (i==1||i==2){
                tabsImg.setPadding(0,(int) DpUtils.convertDpToPixel(1f,this),0,(int) DpUtils.convertDpToPixel(1f,this));
            }
            tabsText.setText(tabsItem[i]);
            tabsImg.setImageResource(imgRes[i]);
            mTabHost.addTab(mTabHost.newTabSpec(tabsItem[i]).setIndicator(inflate), fragment[i], null);
        }
//        mTabHost.setCurrentTab(0);
    }

    @Override
    protected void setListeners() {
        MyApplication.setOnPatchLister(new OnPatchLister() {
            @Override
            public void patchSuccess() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("提示")
                                .setMessage("发现新的补丁，是否立即生效,需重启应用")
                                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent mStartActivity = new Intent(MainActivity.this, MainActivity.class);
                                        int mPendingIntentId = 123456;
                                        PendingIntent mPendingIntent = PendingIntent.getActivity(MainActivity.this, mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
                                        AlarmManager mgr = (AlarmManager)MainActivity.this.getSystemService(Context.ALARM_SERVICE);
                                        if (mgr != null) {
                                            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                                        }
                                        SophixManager.getInstance().killProcessSafely();
                                    }
                                })
                                .setNegativeButton("否",null)
                                .create()
                                .show();
                    }
                });
            }
        });
    }

    @Override
    protected void initData() {

    }

}
