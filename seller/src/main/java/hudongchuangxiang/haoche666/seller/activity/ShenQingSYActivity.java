package hudongchuangxiang.haoche666.seller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;

import java.util.ArrayList;

import hudongchuangxiang.haoche666.seller.R;
import hudongchuangxiang.haoche666.seller.base.ZjbBaseActivity;
import hudongchuangxiang.haoche666.seller.constant.Constant;
import hudongchuangxiang.haoche666.seller.util.PicassoImageLoader;

/**
 * 申请试用
 *
 * @author Administrator
 */
public class ShenQingSYActivity extends ZjbBaseActivity implements View.OnClickListener {

    private ImageView imageYingYeZZ;
    private ImageView imageMenTouZP;
    private ImagePicker mImagePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shen_qing_sy);
        initImagePicker();
        init();
    }

    private void initImagePicker() {
        mImagePicker = ImagePicker.getInstance();
        /*设置图片加载器*/
        mImagePicker.setImageLoader(new PicassoImageLoader());
        /*显示拍照按钮*/
        mImagePicker.setShowCamera(true);
        /*允许裁剪（单选才有效）*/
        mImagePicker.setCrop(false);
        mImagePicker.setMultiMode(false);
    }

    @Override
    protected void initSP() {

    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void findID() {
        imageYingYeZZ = (ImageView) findViewById(R.id.imageYingYeZZ);
        imageMenTouZP = (ImageView) findViewById(R.id.imageMenTouZP);
    }

    @Override
    protected void initViews() {
        ((TextView) findViewById(R.id.textViewTitle)).setText("免费申请");
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.imageBack).setOnClickListener(this);
        findViewById(R.id.btnTiJiao).setOnClickListener(this);
        imageYingYeZZ.setOnClickListener(this);
        imageMenTouZP.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == Constant.RequestResultCode.IMAGE_PICKER) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                Glide.with(ShenQingSYActivity.this)
                        .load(images.get(0).path)
                        .asBitmap()
                        .placeholder(R.mipmap.ic_empty)
                        .into(imageMenTouZP);
            } else {
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
            }
        }
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == Constant.RequestResultCode.IMAGE_PICKER01) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                Glide.with(ShenQingSYActivity.this)
                        .load(images.get(0).path)
                        .asBitmap()
                        .placeholder(R.mipmap.ic_empty)
                        .into(imageYingYeZZ);
            } else {
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.btnTiJiao:
                intent.setClass(this,TiJiaoCGActivity.class);
                startActivity(intent);
                break;
            case R.id.imageMenTouZP:
                intent.setClass(ShenQingSYActivity.this, ImageGridActivity.class);
                startActivityForResult(intent, Constant.RequestResultCode.IMAGE_PICKER);
                break;
            case R.id.imageYingYeZZ:
                intent.setClass(ShenQingSYActivity.this, ImageGridActivity.class);
                startActivityForResult(intent, Constant.RequestResultCode.IMAGE_PICKER01);
                break;
            case R.id.imageBack:
                finish();
                break;
            default:
                break;
        }
    }
}
