package hudongchuangxiang.haoche666.seller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import hudongchuangxiang.haoche666.seller.R;
import hudongchuangxiang.haoche666.seller.base.ZjbBaseActivity;
import hudongchuangxiang.haoche666.seller.constant.Constant;
import hudongchuangxiang.haoche666.seller.model.UserApplybefore;

public class TipsActivity extends ZjbBaseActivity implements View.OnClickListener {

    private UserApplybefore userApplybefore;
    private ImageView imageTips;
    private TextView textViewTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ti_jiao_cg);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {
        Intent intent = getIntent();
        userApplybefore = (UserApplybefore) intent.getSerializableExtra(Constant.IntentKey.BEAN);
    }

    @Override
    protected void findID() {
        imageTips = (ImageView) findViewById(R.id.imageTips);
    }

    @Override
    protected void initViews() {
        textViewTitle = (TextView) findViewById(R.id.textViewTitle);
        if (userApplybefore != null) {
            switch (userApplybefore.getState()) {
                case 0:
                    textViewTitle.setText("正在审核");
                    break;
                default:

                    break;
            }
        }
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
