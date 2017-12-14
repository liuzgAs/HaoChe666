package hudongchuangxiang.haoche666.seller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import hudongchuangxiang.haoche666.seller.R;
import hudongchuangxiang.haoche666.seller.base.ZjbBaseActivity;
import hudongchuangxiang.haoche666.seller.util.DataCleanManager;
import huisedebi.zjb.mylibrary.util.VersionUtils;


/**
 * 设置
 *
 * @author Administrator
 */
public class SheZhiActivity extends ZjbBaseActivity implements View.OnClickListener {

    private TextView textHuanCun;
    private TextView textBanben;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_she_zhi);
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
        textHuanCun = (TextView) findViewById(R.id.textHuanCun);
        textBanben = (TextView) findViewById(R.id.textBanben);
    }

    @Override
    protected void initViews() {
        ((TextView) findViewById(R.id.textViewTitle)).setText("设置");
        textHuanCun.setText(getSize());
        textBanben.setText("v"+ VersionUtils.getCurrVersionName(this));
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
        findViewById(R.id.viewChangJianWenTi).setOnClickListener(this);
        findViewById(R.id.viewHuanCun).setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.viewHuanCun:
                DataCleanManager.clearAllCache(this);
                textHuanCun.setText(getSize());
                Toast.makeText(this,"缓存清除完毕", Toast.LENGTH_SHORT).show();
                break;
            case R.id.viewChangJianWenTi:
                intent.setClass(this, ChangJianWenTiActivity.class);
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
     * -------------获取缓存大小-----------------
     */
    private String getSize() {
        String totalCacheSize = null;
        try {
            totalCacheSize = DataCleanManager.getTotalCacheSize(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalCacheSize;
    }
}
