package com.haoche666.buyer.avtivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.haoche666.buyer.R;
import com.haoche666.buyer.base.MyDialog;
import com.haoche666.buyer.base.ZjbBaseActivity;
import com.haoche666.buyer.constant.Constant;
import com.haoche666.buyer.model.OkObject;
import com.haoche666.buyer.model.RespondAppimgadd;
import com.haoche666.buyer.model.SimpleInfo;
import com.haoche666.buyer.model.UserBuyerindex;
import com.haoche666.buyer.util.ApiClient;
import com.haoche666.buyer.util.ImgToBase64;
import com.haoche666.buyer.util.PicassoImageLoader;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;

import java.util.ArrayList;
import java.util.HashMap;

import huisedebi.zjb.mylibrary.util.ACache;
import huisedebi.zjb.mylibrary.util.GsonUtils;
import huisedebi.zjb.mylibrary.util.LogUtil;
import huisedebi.zjb.mylibrary.util.StringUtil;

public class GeRenXXActivity extends ZjbBaseActivity implements View.OnClickListener {

    private UserBuyerindex userBuyerindex;
    private ImageView imageHeadimg;
    private TextView textNickname;
    private TextView textGrade_name;
    private TextView textMobile;
    private BroadcastReceiver reciver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action) {
                case Constant.BroadcastCode.USERINFO:
                    String username = intent.getStringExtra(Constant.IntentKey.NICKNAME);
                    if (!TextUtils.isEmpty(username)){
                        textNickname.setText(username);
                    }
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ge_ren_xx);
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new PicassoImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(9);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(500);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(500);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素
        imagePicker.setMultiMode(false);
        init();
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {
        Intent intent = getIntent();
        userBuyerindex = (UserBuyerindex) intent.getSerializableExtra(Constant.IntentKey.BEAN);
    }

    @Override
    protected void findID() {
        imageHeadimg = (ImageView) findViewById(R.id.imageHeadimg);
        textNickname = (TextView) findViewById(R.id.textNickname);
        textGrade_name = (TextView) findViewById(R.id.textGrade_name);
        textMobile = (TextView) findViewById(R.id.textMobile);
    }

    @Override
    protected void initViews() {
        ((TextView) findViewById(R.id.textViewTitle)).setText("个人信息");
        Glide.with(GeRenXXActivity.this)
                .load(userBuyerindex.getHeadimg())
                .asBitmap()
                .dontAnimate()
                .placeholder(R.mipmap.ic_empty)
                .into(imageHeadimg);
        textNickname.setText(userBuyerindex.getNickname());
        textGrade_name.setText(userBuyerindex.getGrade_name());
        textMobile.setText(StringUtil.hidePhone(userBuyerindex.getMobile()));
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
        findViewById(R.id.btnExit).setOnClickListener(this);
        findViewById(R.id.viewNickName).setOnClickListener(this);
        findViewById(R.id.viewHeadImg).setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject(String path) {
        String url = Constant.HOST + Constant.Url.RESPOND_APPIMGADD;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", userInfo.getUid());
        params.put("code", "headimg");
        params.put("img", ImgToBase64.toBase64(path));
        params.put("type","png");
        return new OkObject(params, url);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == Constant.RequestResultCode.IMAGE_PICKER) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                LogUtil.LogShitou("GeRenXXActivity--onActivityResult", "" + images.get(0).path);
                LogUtil.LogShitou("GeRenXXActivity--onActivityResult", "" + images.get(0).mimeType);
                showLoadingDialog();
                ApiClient.post(GeRenXXActivity.this, getOkObject(images.get(0).path), new ApiClient.CallBack() {
                    @Override
                    public void onSuccess(String s) {
                        cancelLoadingDialog();
                        LogUtil.LogShitou("GeRenXXActivity--上传图片", s + "");
                        try {
                            RespondAppimgadd respondAppimgadd = GsonUtils.parseJSON(s, RespondAppimgadd.class);
                            if (respondAppimgadd.getStatus() == 1) {
                               edit(respondAppimgadd);
                            } else if (respondAppimgadd.getStatus() == 3) {
                                MyDialog.showReLoginDialog(GeRenXXActivity.this);
                            } else {
                                Toast.makeText(GeRenXXActivity.this, respondAppimgadd.getInfo(), Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Toast.makeText(GeRenXXActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError() {
                        cancelLoadingDialog();
                        Toast.makeText(GeRenXXActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(GeRenXXActivity.this, "", Toast.LENGTH_SHORT).show();
            }
        }
    }


    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getEditOkObject(RespondAppimgadd respondAppimgadd) {
        String url = Constant.HOST + Constant.Url.USER_SVAEINFO;
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", userInfo.getUid());
        params.put("key", "headimg");
        params.put("value", respondAppimgadd.getImgId()+"");
        return new OkObject(params, url);
    }
    /**
     * 修改
     */
    private void edit(final RespondAppimgadd respondAppimgadd) {
        showLoadingDialog();
        ApiClient.post(GeRenXXActivity.this, getEditOkObject(respondAppimgadd), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("GeRenXXActivity--修改头像", s + "");
                try {
                    SimpleInfo simpleInfo = GsonUtils.parseJSON(s, SimpleInfo.class);
                    if (simpleInfo.getStatus() == 1) {
                        LogUtil.LogShitou("GeRenXXActivity--onSuccess", ""+respondAppimgadd.getImg());
                        Glide.with(GeRenXXActivity.this)
                                .load(respondAppimgadd.getImg())
                                .asBitmap()
                                .dontAnimate()
                                .placeholder(R.mipmap.ic_empty)
                                .into(imageHeadimg);
                        Intent intent = new Intent();
                        intent.setAction(Constant.BroadcastCode.USERINFO);
                        sendBroadcast(intent);
                    } else if (simpleInfo.getStatus() == 3) {
                        MyDialog.showReLoginDialog(GeRenXXActivity.this);
                    } else {
                    }
                    Toast.makeText(GeRenXXActivity.this, simpleInfo.getInfo(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(GeRenXXActivity.this, "数据出错", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError() {
                cancelLoadingDialog();
                Toast.makeText(GeRenXXActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * des： 选择图片
     * author： ZhangJieBo
     * date： 2017/7/6 0006 下午 2:31
     */
    public void chooseHead() {
        Intent intent = new Intent();
        intent.setClass(GeRenXXActivity.this, ImageGridActivity.class);
        startActivityForResult(intent, Constant.RequestResultCode.IMAGE_PICKER);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.viewHeadImg:
                if (TextUtils.isEmpty(userBuyerindex.getHeadimg())) {
                    chooseHead();
                } else {
                    MyDialog.choosePic(this);
                    MyDialog.setOnChoosePicListener(new MyDialog.OnChoosePicListener() {
                        @Override
                        public void chaKan() {
                            MyDialog.showPicDialog(GeRenXXActivity.this, userBuyerindex.getHeadimg());
                        }

                        @Override
                        public void shangChuan() {
                            chooseHead();
                        }
                    });
                }
                break;
            case R.id.viewNickName:
                Intent intent = new Intent();
                intent.putExtra(Constant.IntentKey.VALUE, userBuyerindex.getNickname());
                intent.setClass(this, EditActivity.class);
                startActivity(intent);
                break;
            case R.id.btnExit:
                ACache aCache = ACache.get(this, Constant.Acache.APP);
                aCache.clear();
                Constant.changeControl++;
                finish();
                break;
            case R.id.imageBack:
                finish();
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
        registerReceiver(reciver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(reciver);
    }
}