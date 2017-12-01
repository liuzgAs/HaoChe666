package hudongchuangxiang.com.seller.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hudongchuangxiang.com.seller.R;
import hudongchuangxiang.com.seller.activity.BianJiDPActivity;
import hudongchuangxiang.com.seller.activity.DianPuShuJuActivity;
import hudongchuangxiang.com.seller.activity.DingDanGLActivity;
import hudongchuangxiang.com.seller.activity.ZhangHaoGLActivity;
import hudongchuangxiang.com.seller.base.ZjbBaseFragment;
import huisedebi.zjb.mylibrary.customview.HeadZoomScrollView;
import huisedebi.zjb.mylibrary.util.DpUtils;
import huisedebi.zjb.mylibrary.util.ScreenUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class WoDeFragment extends ZjbBaseFragment implements View.OnClickListener {


    private View mInflate;
    private HeadZoomScrollView scrollView;
    private View zoomView;
    private View viewBar;

    public WoDeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mInflate == null) {
            mInflate = inflater.inflate(R.layout.fragment_wo_de, container, false);
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
        scrollView = mInflate.findViewById(R.id.scrollView);
        viewBar = mInflate.findViewById(R.id.viewBar);
    }

    @Override
    protected void initViews() {
        ViewGroup.LayoutParams layoutParams = viewBar.getLayoutParams();
        layoutParams.height = ScreenUtils.getStatusBarHeight(getActivity()) + (int) DpUtils.convertDpToPixel(45f, getActivity());
        viewBar.setLayoutParams(layoutParams);
    }

    @Override
    protected void setListeners() {
        mInflate.findViewById(R.id.viewZhangHaoGL).setOnClickListener(this);
        mInflate.findViewById(R.id.viewDianPuBianJi).setOnClickListener(this);
        mInflate.findViewById(R.id.viewDianPuShuJu).setOnClickListener(this);
        mInflate.findViewById(R.id.viewDiangDanGL).setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.viewDiangDanGL:
                intent.setClass(getActivity(), DingDanGLActivity.class);
                startActivity(intent);
                break; case R.id.viewDianPuShuJu:
                intent.setClass(getActivity(), DianPuShuJuActivity.class);
                startActivity(intent);
                break;
            case R.id.viewDianPuBianJi:
                intent.setClass(getActivity(), BianJiDPActivity.class);
                startActivity(intent);
                break;
            case R.id.viewZhangHaoGL:
                intent.setClass(getActivity(), ZhangHaoGLActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
