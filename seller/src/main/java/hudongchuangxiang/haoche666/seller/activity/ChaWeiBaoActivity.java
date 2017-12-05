package hudongchuangxiang.haoche666.seller.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import hudongchuangxiang.haoche666.seller.R;
import hudongchuangxiang.haoche666.seller.base.ZjbBaseActivity;


/**
 * 查维保
 * @author Administrator
 */
public class ChaWeiBaoActivity extends ZjbBaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cha_wei_bao);
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
        ((TextView) findViewById(R.id.textViewTitle)).setText("查维保");
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageBack:
                finish();
                break;
            default:
                break;
        }
    }
}
