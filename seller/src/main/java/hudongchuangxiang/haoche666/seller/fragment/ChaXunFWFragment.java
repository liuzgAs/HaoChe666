package hudongchuangxiang.haoche666.seller.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import hudongchuangxiang.haoche666.seller.R;
import hudongchuangxiang.haoche666.seller.activity.ChaChuXianActivity;
import hudongchuangxiang.haoche666.seller.activity.ChaWeiBaoActivity;
import hudongchuangxiang.haoche666.seller.activity.ChaWeiZhangActivity;
import hudongchuangxiang.haoche666.seller.activity.ChaXunLSActivity;
import hudongchuangxiang.haoche666.seller.base.ZjbBaseFragment;
import huisedebi.zjb.mylibrary.util.ScreenUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChaXunFWFragment extends ZjbBaseFragment implements View.OnClickListener {


    private View mInflate;
    private View mRelaTitleStatue;

    public ChaXunFWFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mInflate == null) {
            mInflate = inflater.inflate(R.layout.fragment_cha_xun_fw, container, false);
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
        mRelaTitleStatue = mInflate.findViewById(R.id.relaTitleStatue);
        ((TextView) mInflate.findViewById(R.id.textViewTitle)).setText("服务查询");
    }

    @Override
    protected void initViews() {
        ViewGroup.LayoutParams layoutParams = mRelaTitleStatue.getLayoutParams();
        layoutParams.height = (int) (getResources().getDimension(R.dimen.titleHeight) + ScreenUtils.getStatusBarHeight(getActivity()));
        mRelaTitleStatue.setLayoutParams(layoutParams);
        mRelaTitleStatue.setPadding(0, ScreenUtils.getStatusBarHeight(getActivity()), 0, 0);
    }

    @Override
    protected void setListeners() {
        mInflate.findViewById(R.id.viewChaWeiXiu).setOnClickListener(this);
        mInflate.findViewById(R.id.viewChaWeiZhang).setOnClickListener(this);
        mInflate.findViewById(R.id.viewChaChuXian).setOnClickListener(this);
        mInflate.findViewById(R.id.viewChaXunLS).setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.viewChaXunLS:
                intent.setClass(getActivity(), ChaXunLSActivity.class);
                startActivity(intent);
                break;
            case R.id.viewChaChuXian:
                intent.setClass(getActivity(), ChaChuXianActivity.class);
                startActivity(intent);
                break;
            case R.id.viewChaWeiXiu:
                intent.setClass(getActivity(), ChaWeiBaoActivity.class);
                startActivity(intent);
                break;
            case R.id.viewChaWeiZhang:
                intent.setClass(getActivity(), ChaWeiZhangActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
