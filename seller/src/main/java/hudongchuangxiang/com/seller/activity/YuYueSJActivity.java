package hudongchuangxiang.com.seller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import hudongchuangxiang.com.seller.R;
import hudongchuangxiang.com.seller.base.ZjbBaseActivity;

public class YuYueSJActivity extends ZjbBaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yu_yue_sj);
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

    }

    @Override
    protected void initViews() {
        ((TextView) findViewById(R.id.textViewTitle)).setText("预约上架");
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
        findViewById(R.id.buttonTiJiao).setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        Intent intent =new Intent();
        switch (view.getId()) {
            case R.id.buttonTiJiao:
                intent.setClass(this,YuYueSJActivity.class);
                startActivity(intent);
                break;
            case R.id.imageBack:
                finish();
                break;
            default:
                break;
        }
    }
}
