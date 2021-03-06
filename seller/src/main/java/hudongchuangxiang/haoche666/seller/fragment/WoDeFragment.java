package hudongchuangxiang.haoche666.seller.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.HashMap;

import hudongchuangxiang.haoche666.seller.R;
import hudongchuangxiang.haoche666.seller.activity.BianJiDPActivity;
import hudongchuangxiang.haoche666.seller.activity.ChongZhiActivity;
import hudongchuangxiang.haoche666.seller.activity.DianPuShuJuActivity;
import hudongchuangxiang.haoche666.seller.activity.DingDanGLActivity;
import hudongchuangxiang.haoche666.seller.activity.SheZhiActivity;
import hudongchuangxiang.haoche666.seller.activity.ShiMingRZActivity;
import hudongchuangxiang.haoche666.seller.activity.TipsActivity;
import hudongchuangxiang.haoche666.seller.activity.WoDeFSActivity;
import hudongchuangxiang.haoche666.seller.activity.XiaoXiZXActivity;
import hudongchuangxiang.haoche666.seller.activity.YiJianFKActivity;
import hudongchuangxiang.haoche666.seller.activity.ZhangHaoGLActivity;
import hudongchuangxiang.haoche666.seller.base.MyDialog;
import hudongchuangxiang.haoche666.seller.base.ToLoginActivity;
import hudongchuangxiang.haoche666.seller.base.ZjbBaseFragment;
import hudongchuangxiang.haoche666.seller.constant.Constant;
import hudongchuangxiang.haoche666.seller.model.OkObject;
import hudongchuangxiang.haoche666.seller.model.UserApplybefore;
import hudongchuangxiang.haoche666.seller.util.ApiClient;
import huisedebi.zjb.mylibrary.customview.HeadZoomScrollView;
import huisedebi.zjb.mylibrary.util.DpUtils;
import huisedebi.zjb.mylibrary.util.GsonUtils;
import huisedebi.zjb.mylibrary.util.LogUtil;
import huisedebi.zjb.mylibrary.util.ScreenUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class WoDeFragment extends ZjbBaseFragment implements View.OnClickListener {


    private View mInflate;
    private HeadZoomScrollView scrollView;
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
//        mInflate.findViewById(R.id.viewWoDeQianBao).setOnClickListener(this);
        mInflate.findViewById(R.id.viewXiaoXi).setOnClickListener(this);
        mInflate.findViewById(R.id.viewWoDeFS).setOnClickListener(this);
        mInflate.findViewById(R.id.viewSheZhi).setOnClickListener(this);
        mInflate.findViewById(R.id.viewYiJianFK).setOnClickListener(this);
        mInflate.findViewById(R.id.textChongZhi).setOnClickListener(this);
        mInflate.findViewById(R.id.viewShiMingRZ).setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.viewShiMingRZ:
                if (isLogin) {
                    shiMingRZ();
                } else {
                    ToLoginActivity.toLoginActivity(getActivity());
                }
                break;
            case R.id.textChongZhi:
                intent.setClass(getActivity(), ChongZhiActivity.class);
                startActivity(intent);
                break;
            case R.id.viewYiJianFK:
                if (isLogin) {
                    intent.setClass(getActivity(), YiJianFKActivity.class);
                    startActivity(intent);
                } else {
                    ToLoginActivity.toLoginActivity(getActivity());
                }
                break;
            case R.id.viewSheZhi:
                intent.setClass(getActivity(), SheZhiActivity.class);
                startActivity(intent);
                break;
            case R.id.viewWoDeFS:
                intent.setClass(getActivity(), WoDeFSActivity.class);
                startActivity(intent);
                break;
            case R.id.viewXiaoXi:
                intent.setClass(getActivity(), XiaoXiZXActivity.class);
                startActivity(intent);
                break;
//            case R.id.viewWoDeQianBao:
//                intent.setClass(getActivity(), WoDeQianBaoActivity.class);
//                startActivity(intent);
//                break;
            case R.id.viewDiangDanGL:
                intent.setClass(getActivity(), DingDanGLActivity.class);
                startActivity(intent);
                break;
            case R.id.viewDianPuShuJu:
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

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.USER_APPLYBEFORE;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        return new OkObject(params, url);
    }

    /**
     * 实名认证
     */
    private void shiMingRZ() {
        showLoadingDialog();
        ApiClient.post(getActivity(), getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("ShiMingRZActivity--实名认证请求", s + "");
                try {
                    UserApplybefore userApplybefore = GsonUtils.parseJSON(s, UserApplybefore.class);
                    if (userApplybefore.getStatus() == 1) {
                        Intent intent = new Intent();
                        switch (userApplybefore.getState()) {
                            case 0:
                                intent.putExtra(Constant.IntentKey.BEAN,userApplybefore);
                                intent.setClass(getActivity(), TipsActivity.class);
                                startActivity(intent);
                                break;
                            default:
                                intent.putExtra(Constant.IntentKey.BEAN,userApplybefore);
                                intent.setClass(getActivity(), ShiMingRZActivity.class);
                                startActivity(intent);
                                break;
                        }
                    } else if (userApplybefore.getStatus() == 3) {
                        MyDialog.showReLoginDialog(getActivity());
                    } else {
                        Toast.makeText(getActivity(), userApplybefore.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(getActivity(), "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
