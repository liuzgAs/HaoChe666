package com.haoche666.buyer.fragment;


import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.haoche666.buyer.R;
import com.haoche666.buyer.activity.ChaXunFWActivity;
import com.haoche666.buyer.activity.CheLiangDBActivity;
import com.haoche666.buyer.activity.ChongZhiActivity;
import com.haoche666.buyer.activity.DingDanGLActivity;
import com.haoche666.buyer.activity.GeRenXXActivity;
import com.haoche666.buyer.activity.PayVipActivity;
import com.haoche666.buyer.activity.SheZhiActivity;
import com.haoche666.buyer.activity.WoDeGZActivity;
import com.haoche666.buyer.activity.WoMaiDeCheActivity;
import com.haoche666.buyer.base.MyDialog;
import com.haoche666.buyer.base.ToLoginActivity;
import com.haoche666.buyer.base.ZjbBaseFragment;
import com.haoche666.buyer.constant.Constant;
import com.haoche666.buyer.customview.HeaderWaveHelper;
import com.haoche666.buyer.customview.HeaderWaveView;
import com.haoche666.buyer.model.OkObject;
import com.haoche666.buyer.model.UserBuyerindex;
import com.haoche666.buyer.model.UserInfo;
import com.haoche666.buyer.util.ApiClient;

import java.util.HashMap;
import java.util.List;

import huisedebi.zjb.mylibrary.util.ACache;
import huisedebi.zjb.mylibrary.util.GsonUtils;
import huisedebi.zjb.mylibrary.util.LogUtil;
import huisedebi.zjb.mylibrary.util.ScreenUtils;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * A simple {@link Fragment} subclass.
 */
public class WoDeFragment extends ZjbBaseFragment implements View.OnClickListener , EasyPermissions.PermissionCallbacks{


    private View mInflate;
    private HeaderWaveView waveView;
    private HeaderWaveHelper mHeaderWaveHelper;
    private View viewBar;
    private View viewBar01;
    private ImageView imageVip;
    private TextView textName;
    private ImageView imageHead;
    private TextView textMoney;
    private UserBuyerindex userBuyerindex;
    private BroadcastReceiver reciver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action) {
                case Constant.BroadcastCode.USERINFO:
                    initData();
                    break;
                case Constant.BroadcastCode.CHONG_ZHI:
                    initData();
                    break;
                default:
                    break;
            }
        }
    };
    private ImageView imageVip1;

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
        waveView = mInflate.findViewById(R.id.header_wave_view);
        viewBar = mInflate.findViewById(R.id.viewBar);
        viewBar01 = mInflate.findViewById(R.id.viewBar01);
        imageVip = mInflate.findViewById(R.id.imageVip);
        textName = mInflate.findViewById(R.id.textName);
        imageHead = mInflate.findViewById(R.id.imageHead);
        textMoney = mInflate.findViewById(R.id.textMoney);
    }

    @Override
    protected void initViews() {
        mHeaderWaveHelper = new HeaderWaveHelper(waveView, ContextCompat.getColor(mContext, R.color.waveBgLigth), ContextCompat.getColor(mContext, R.color.waveBg));
        ViewGroup.LayoutParams layoutParams = viewBar.getLayoutParams();
        layoutParams.height = (int) (getResources().getDimension(R.dimen.titleHeight) + ScreenUtils.getStatusBarHeight(mContext));
        viewBar.setLayoutParams(layoutParams);
        viewBar.setPadding(0, ScreenUtils.getStatusBarHeight(mContext), 0, 0);
        ViewGroup.LayoutParams layoutParams01 = viewBar01.getLayoutParams();
        layoutParams01.height = (int) (getResources().getDimension(R.dimen.titleHeight) + ScreenUtils.getStatusBarHeight(mContext));
        viewBar01.setLayoutParams(layoutParams01);
        viewBar01.setPadding(0, ScreenUtils.getStatusBarHeight(mContext), 0, 0);
    }

    @Override
    protected void setListeners() {
        mInflate.findViewById(R.id.viewWoDeGZ).setOnClickListener(this);
        mInflate.findViewById(R.id.viewDuiBi).setOnClickListener(this);
        mInflate.findViewById(R.id.viewWoDeDD).setOnClickListener(this);
        mInflate.findViewById(R.id.viewChaXunFW).setOnClickListener(this);
        mInflate.findViewById(R.id.imageSheZhi).setOnClickListener(this);
        mInflate.findViewById(R.id.textChongZhi).setOnClickListener(this);
        mInflate.findViewById(R.id.viewGeRenXX).setOnClickListener(this);
        mInflate.findViewById(R.id.viewWoMaiDeChe).setOnClickListener(this);
        mInflate.findViewById(R.id.viewLianXiKeFu).setOnClickListener(this);
        imageVip.setOnClickListener(this);
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.USER_BUYERINDEX;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        return new OkObject(params, url);
    }

    @Override
    protected void initData() {
        if (isLogin) {
            textName.setText(userInfo.getUserName());
            textMoney.setText("00.00");
            Glide.with(mContext)
                    .load(userInfo.getHeadImg())
                    .asBitmap()
                    .dontAnimate()
                    .placeholder(R.mipmap.ic_empty)
                    .into(imageHead);
            showLoadingDialog();
            ApiClient.post(mContext, getOkObject(), new ApiClient.CallBack() {
                @Override
                public void onSuccess(String s) {
                    cancelLoadingDialog();
                    LogUtil.LogShitou("WoDeFragment--我的", s + "");
                    try {
                        userBuyerindex = GsonUtils.parseJSON(s, UserBuyerindex.class);
                        if (userBuyerindex.getStatus() == 1) {
                            Glide.with(mContext)
                                    .load(userBuyerindex.getHeadimg())
                                    .asBitmap()
                                    .placeholder(R.mipmap.ic_empty)
                                    .into(imageHead);
                            textName.setText(userBuyerindex.getNickname());
                            textMoney.setText(userBuyerindex.getMoney() + "");
                            if (userBuyerindex.getGrade() > 0) {
                                imageVip.setImageResource(R.mipmap.haochehuiyuan);
                            } else {
                                imageVip.setImageResource(R.mipmap.pay_vip);
                            }
                            ACache aCache = ACache.get(mContext, Constant.Acache.APP);
                            UserInfo userInfo = (UserInfo) aCache.getAsObject(Constant.Acache.USER_INFO);
                            userInfo.setHeadImg(userBuyerindex.getHeadimg());
                            userInfo.setUserName(userBuyerindex.getNickname());
                            aCache.put(Constant.Acache.USER_INFO, userInfo);
                        } else if (userBuyerindex.getStatus() == 3) {
                            MyDialog.showReLoginDialog(mContext);
                        } else {
                            Toast.makeText(mContext, userBuyerindex.getInfo(), Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(mContext, "数据出错", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onError() {
                    cancelLoadingDialog();
                    Toast.makeText(mContext, "请求失败", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            textName.setText("未登录");
            Glide.with(mContext)
                    .load(R.mipmap.mine_head)
                    .asBitmap()
                    .dontAnimate()
                    .placeholder(R.mipmap.ic_empty)
                    .into(imageHead);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mHeaderWaveHelper.cancel();
    }

    @Override
    public void onResume() {
        super.onResume();
        mHeaderWaveHelper.start();
    }
    private static final int CALL_PHONE = 1991;

    /**
     * 检查权限
     */
    private void requiresPermission() {
        String[] perms = {Manifest.permission.CALL_PHONE};
        if (EasyPermissions.hasPermissions(mContext, perms)) {
            // Already have permission, do the thing
            call();
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, "需要拨打电话权限",
                    CALL_PHONE, perms);
        }
    }

    /**
     * 拨打电话
     */
    private void call() {
    /*跳转到拨号界面，同时传递电话号码*/
        if (userBuyerindex != null) {
            if (TextUtils.isEmpty(userBuyerindex.getService_telephone())) {
                Toast.makeText(mContext, "电话号码为空", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + userBuyerindex.getService_telephone()));
            startActivity(dialIntent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        call();
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this)
                    .setTitle("为了您能使用拨打电话功能，请开启打电话权限！")
                    .setPositiveButton("去设置")
                    .setNegativeButton("取消")
                    .setRequestCode(CALL_PHONE)
                    .build()
                    .show();
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.viewLianXiKeFu:
                requiresPermission();
                break;
            case R.id.viewWoMaiDeChe:
                if (isLogin) {
                    intent.setClass(mContext, WoMaiDeCheActivity.class);
                    startActivity(intent);
                } else {
                    ToLoginActivity.toLoginActivity(mContext);
                }
                break;
            case R.id.viewGeRenXX:
                if (isLogin) {
                    intent.setClass(mContext, GeRenXXActivity.class);
                    intent.putExtra(Constant.IntentKey.BEAN, userBuyerindex);
                    startActivity(intent);
                } else {
                    ToLoginActivity.toLoginActivity(mContext);
                }
                break;
            case R.id.textChongZhi:
                if (isLogin) {
                    intent.setClass(mContext, ChongZhiActivity.class);
                    startActivity(intent);
                } else {
                    ToLoginActivity.toLoginActivity(mContext);
                }
                break;
            case R.id.imageVip:
                if (isLogin) {
                    intent.setClass(mContext, PayVipActivity.class);
                    startActivity(intent);
                } else {
                    ToLoginActivity.toLoginActivity(mContext);
                }
                break;
            case R.id.imageSheZhi:
                intent.setClass(mContext, SheZhiActivity.class);
                startActivity(intent);
                break;
            case R.id.viewChaXunFW:
                intent.setClass(mContext, ChaXunFWActivity.class);
                startActivity(intent);
                break;
            case R.id.viewWoDeDD:
                if (isLogin) {
                    intent.setClass(mContext, DingDanGLActivity.class);
                    startActivity(intent);
                } else {
                    ToLoginActivity.toLoginActivity(mContext);
                }
                break;
            case R.id.viewDuiBi:
                if (isLogin) {
                    intent.setClass(mContext, CheLiangDBActivity.class);
                    startActivityForResult(intent,Constant.RequestResultCode.MAICHE);
                } else {
                    ToLoginActivity.toLoginActivity(mContext);
                }
                break;
            case R.id.viewWoDeGZ:
                if (isLogin) {
                    intent.setClass(mContext, WoDeGZActivity.class);
                    startActivity(intent);
                } else {
                    ToLoginActivity.toLoginActivity(mContext);
                }
                break;
            default:

                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.BroadcastCode.USERINFO);
        filter.addAction(Constant.BroadcastCode.CHONG_ZHI);
        mContext.registerReceiver(reciver, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mContext.unregisterReceiver(reciver);
    }
}
