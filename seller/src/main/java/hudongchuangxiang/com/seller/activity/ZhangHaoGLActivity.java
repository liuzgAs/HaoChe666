package hudongchuangxiang.com.seller.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;

import java.util.ArrayList;
import java.util.List;

import hudongchuangxiang.com.seller.R;
import hudongchuangxiang.com.seller.base.ZjbBaseActivity;
import hudongchuangxiang.com.seller.viewholder.MyBaseViewHolder;
import huisedebi.zjb.mylibrary.util.DpUtils;

public class ZhangHaoGLActivity extends ZjbBaseActivity implements View.OnClickListener {

    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<Integer> adapter;
    private AlertDialog xinZengDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhang_hao_gl);
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
    }

    @Override
    protected void initViews() {
        ((TextView) findViewById(R.id.textViewTitle)).setText("账号管理");
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
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<Integer>(ZhangHaoGLActivity.this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_zi_zhang_hao;
                return new MyBaseViewHolder(parent, layout);
            }
        });
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
            }
        });
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                View view = LayoutInflater.from(ZhangHaoGLActivity.this).inflate(R.layout.header_zhang_hao_gl, null);
                return view;
            }

            @Override
            public void onBindView(View headerView) {

            }
        });
        adapter.addFooter(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                View view = new View(ZhangHaoGLActivity.this);
                view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) DpUtils.convertDpToPixel(60f, ZhangHaoGLActivity.this)));
                return view;
            }

            @Override
            public void onBindView(View headerView) {

            }
        });
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
        findViewById(R.id.btnXinZengZH).setOnClickListener(this);
    }

    @Override
    protected void initData() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        adapter.clear();
        adapter.addAll(list);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnXinZengZH:
                LayoutInflater inflater = LayoutInflater.from(ZhangHaoGLActivity.this);
                View dialog_chan_pin = inflater.inflate(R.layout.dialog_xing_zeng_zh, null);
                xinZengDialog = new AlertDialog.Builder(ZhangHaoGLActivity.this, R.style.dialog)
                        .setView(dialog_chan_pin)
                        .create();
                xinZengDialog.show();
                Window dialogWindow = xinZengDialog.getWindow();
                dialogWindow.setGravity(Gravity.BOTTOM);
                dialogWindow.setWindowAnimations(R.style.dialogFenXiang);
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                DisplayMetrics d = ZhangHaoGLActivity.this.getResources().getDisplayMetrics();
                lp.width = (int) (d.widthPixels * 1);
                dialogWindow.setAttributes(lp);
                break;
            case R.id.imageBack:
                finish();
                break;
            default:
                break;
        }
    }
}
