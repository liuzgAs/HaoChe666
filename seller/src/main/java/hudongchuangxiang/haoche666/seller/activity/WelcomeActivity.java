package hudongchuangxiang.haoche666.seller.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import java.util.HashMap;
import java.util.List;

import hudongchuangxiang.haoche666.seller.R;
import hudongchuangxiang.haoche666.seller.base.MyDialog;
import hudongchuangxiang.haoche666.seller.base.ZjbBaseNotLeftActivity;
import hudongchuangxiang.haoche666.seller.constant.Constant;
import hudongchuangxiang.haoche666.seller.model.IndexStartad;
import hudongchuangxiang.haoche666.seller.model.OkObject;
import hudongchuangxiang.haoche666.seller.util.ApiClient;
import huisedebi.zjb.mylibrary.util.ACache;
import huisedebi.zjb.mylibrary.util.GsonUtils;
import huisedebi.zjb.mylibrary.util.LogUtil;
import huisedebi.zjb.mylibrary.util.VersionUtils;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class WelcomeActivity extends ZjbBaseNotLeftActivity implements EasyPermissions.PermissionCallbacks {
    private static final int LOCATION = 1991;
    private String isFirst = "1";
    private String lat;
    private String lng;
    private int GPS_REQUEST_CODE = 10;
    /**
     * 声明AMapLocationClient类对象
     */
    public AMapLocationClient mLocationClient = null;
    /**
     * 声明AMapLocationClientOption对象
     */
    public AMapLocationClientOption mLocationOption = null;
    /**
     * 声明定位回调监听器
     */
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    //可在其中解析amapLocation获取相应内容。
                    final String city = aMapLocation.getCity();
                    final ACache aCache = ACache.get(WelcomeActivity.this, Constant.Acache.LOCATION);
                    lat = aMapLocation.getLatitude() + "";
                    lng = aMapLocation.getLongitude() + "";
                    Log.e("IndexFragment", "IndexFragment--onLocationChanged--city" + city + lat + lng);
                    aCache.put(Constant.Acache.LAT, lat);
                    aCache.put(Constant.Acache.LNG, lng);
                    aCache.put(Constant.Acache.CITY, city);
                    getData();
                } else {
                    final ACache aCache = ACache.get(WelcomeActivity.this, Constant.Acache.LOCATION);
                    String city = aCache.getAsString(Constant.Acache.CITY);
                    if (TextUtils.isEmpty(city)) {
                        MyDialog.dialogFinish(WelcomeActivity.this, "定位失败");
                    } else {
                        getData();
                    }
                }
            }
        }
    };

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.INDEX_START;
        HashMap<String, String> params = new HashMap<>();
        params.put("isFirst", isFirst);
        params.put("lng", lng);
        params.put("lat", lat);
        params.put("deviceId", PushServiceFactory.getCloudPushService().getDeviceId());
        params.put("platform", "android");
        params.put("version", VersionUtils.getCurrVersionName(this));
        params.put("intro", "model=" + android.os.Build.MODEL + "sdk=" + android.os.Build.VERSION.SDK);
        params.put("mid", "");
        return new OkObject(params, url);
    }

    private void getData() {
        ApiClient.post(WelcomeActivity.this, getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                LogUtil.LogShitou("WelcomeActivity--onSuccess", "" + s);
                try {
                    final IndexStartad indexStartad = GsonUtils.parseJSON(s, IndexStartad.class);

                    if (indexStartad.getStatus() == 1) {
                        go(indexStartad);
                    } else {
                        MyDialog.dialogFinish(WelcomeActivity.this, indexStartad.getInfo());
                    }
                } catch (Exception e) {
                    MyDialog.dialogFinish(WelcomeActivity.this, "数据出错");
                }

            }

            private void go(IndexStartad indexStartad) {
//                if (TextUtils.equals(isFirst, "1")) {
//                    Intent intent = new Intent(WelcomeActivity.this, YinDaoActivity.class);
//                    startActivity(intent);
//                    finish();
//                } else {
                toMainActivity();
//                }
                ACache aCache = ACache.get(WelcomeActivity.this, Constant.Acache.LOCATION);
                aCache.put(Constant.Acache.CITY, indexStartad.getCityName());
                aCache.put(Constant.Acache.CITY_ID, indexStartad.getCityId() + "");
                aCache.put(Constant.Acache.DID, indexStartad.getDid() + "");
            }

            @Override
            public void onError() {
                try {
                    MyDialog.dialogFinish(WelcomeActivity.this, "请求失败");
                } catch (Exception e) {
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huan_ying);
        if (!checkGPSIsOpen()) {
            LogUtil.LogShitou("WelcomeActivity--onCreate", "GPS未开启");
            openGPSSettings();
        } else {
            LogUtil.LogShitou("WelcomeActivity--onCreate", "GPS开启");
            methodRequiresTwoPermission();
        }
        init();
    }

    @AfterPermissionGranted(LOCATION)
    private void methodRequiresTwoPermission() {
        String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION};
        if (EasyPermissions.hasPermissions(this, perms)) {
            // Already have permission, do the thing
            initLocation();
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, "需要开启定位权限",
                    LOCATION, perms);
        }
    }

    @Override
    protected void initSP() {
        ACache aCache = ACache.get(WelcomeActivity.this, Constant.Acache.FRIST);
        String frist = aCache.getAsString(Constant.Acache.FRIST);
        if (frist != null) {
            isFirst = frist;
        }
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void findID() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void initData() {
    }


    /**
     * des： 初始化定位参数
     * author： ZhangJieBo
     * date： 2017/7/6 0006 下午 2:41
     */
    private void initLocation() {
        //初始化定位
        mLocationClient = new AMapLocationClient(this);
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //获取一次定位结果：
        //该方法默认为false。
        mLocationOption.setOnceLocation(true);
        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        mLocationOption.setHttpTimeOut(20000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void toMainActivity() {
        Intent intent = new Intent();
        intent.setClass(WelcomeActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            // Do something after user returned from app settings screen, like showing a Toast.
            Toast.makeText(this, "开启定位成功", Toast.LENGTH_SHORT)
                    .show();
            initLocation();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        } else {
            methodRequiresTwoPermission();
        }
    }

    /**
     * 检测GPS是否打开
     *
     * @return
     */
    private boolean checkGPSIsOpen() {
        boolean isOpen;
        LocationManager locationManager = (LocationManager) this
                .getSystemService(Context.LOCATION_SERVICE);
        isOpen = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return isOpen;
    }

    /**
     * 跳转GPS设置
     */
    private void openGPSSettings() {
        if (checkGPSIsOpen()) {
            methodRequiresTwoPermission();
        } else {
            //没有打开则弹出对话框
            new AlertDialog.Builder(this)
                    .setTitle(R.string.notifyTitle)
                    .setMessage(R.string.gpsNotifyMsg)
                    // 拒绝, 退出应用
                    .setNegativeButton(R.string.cancel,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            })

                    .setPositiveButton(R.string.setting,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //跳转GPS设置界面
                                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                    startActivityForResult(intent, GPS_REQUEST_CODE);
                                }
                            })

                    .setCancelable(false)
                    .show();

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GPS_REQUEST_CODE) {
            //做需要做的事情，比如再次检测是否打开GPS了 或者定位
            openGPSSettings();
        }
    }
}
