package hudongchuangxiang.com.seller.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hudongchuangxiang.com.seller.R;
import hudongchuangxiang.com.seller.activity.GuanLiDYActivity;
import hudongchuangxiang.com.seller.activity.MainActivity;
import hudongchuangxiang.com.seller.activity.YuYueSJActivity;
import hudongchuangxiang.com.seller.base.ZjbBaseFragment;
import huisedebi.zjb.mylibrary.util.ScreenUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShouYeFragment extends ZjbBaseFragment implements View.OnClickListener {


    private View mInflate;
    private View viewBar;

    public ShouYeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mInflate == null) {
            mInflate = inflater.inflate(R.layout.fragment_shou_ye, container, false);
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
        viewBar = mInflate.findViewById(R.id.viewBar);
    }

    @Override
    protected void initViews() {
        viewBar.setPadding(0, ScreenUtils.getStatusBarHeight(getActivity()), 0, 0);
    }

    @Override
    protected void setListeners() {
        mInflate.findViewById(R.id.viewYuYueSJ).setOnClickListener(this);
        mInflate.findViewById(R.id.viewCheLiang).setOnClickListener(this);
        mInflate.findViewById(R.id.viewDingYueCY).setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.viewCheLiang:
                ((MainActivity) getActivity()).mTabHost.setCurrentTab(1);
                break;
            case R.id.viewDingYueCY:
                intent.setClass(getActivity(), GuanLiDYActivity.class);
                startActivity(intent);
                break;
            case R.id.viewYuYueSJ:
                intent.setClass(getActivity(), YuYueSJActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
