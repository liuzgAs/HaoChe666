package hudongchuangxiang.com.seller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;

import java.util.ArrayList;
import java.util.List;

import hudongchuangxiang.com.seller.R;
import hudongchuangxiang.com.seller.base.ZjbBaseActivity;
import hudongchuangxiang.com.seller.constant.Constant;
import hudongchuangxiang.com.seller.util.PicassoImageLoader;
import huisedebi.zjb.mylibrary.customview.GridView4ScrollView;

public class BianJiCLActivity extends ZjbBaseActivity implements View.OnClickListener {

    private GridView4ScrollView gridView;
    private List<ImageItem> picList = new ArrayList<>();
    private ImagePicker mImagePicker;
    private int photoNum = 9;
    private MyAdapter picAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bian_ji_cl);
        mImagePicker = ImagePicker.getInstance();
        /*设置图片加载器*/
        mImagePicker.setImageLoader(new PicassoImageLoader());
        /*显示拍照按钮*/
        mImagePicker.setShowCamera(true);
        /*允许裁剪（单选才有效）*/
        mImagePicker.setCrop(false);
        /*是否按矩形区域保存*/
        mImagePicker.setSaveRectangle(true);
        /*裁剪框的形状*/
        mImagePicker.setStyle(CropImageView.Style.RECTANGLE);
        /*裁剪框的宽度。单位像素（圆形自动取宽高最小值）*/
        mImagePicker.setFocusWidth(500);
        /*裁剪框的高度。单位像素（圆形自动取宽高最小值）*/
        mImagePicker.setFocusHeight(500);
        /*保存文件的宽度。单位像素*/
        mImagePicker.setOutPutX(1000);
        /*保存文件的高度。单位像素*/
        mImagePicker.setOutPutY(1000);
        mImagePicker.setMultiMode(true);
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
        gridView = (GridView4ScrollView) findViewById(R.id.gridView);
    }

    @Override
    protected void initViews() {
        ((TextView) findViewById(R.id.textTitle)).setText("编辑车辆");
        picAdapter = new MyAdapter();
        gridView.setAdapter(picAdapter);
        if (picList.size() == 0) {
            gridView.setVisibility(View.GONE);
        }
    }

    @Override
    protected void setListeners() {
        findViewById(R.id.viewAddPic).setOnClickListener(this);
        findViewById(R.id.imageBack).setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == Constant.REQUEST_RESULT_CODE.IMAGE_PICKER) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                picList.addAll(images);
                if (picList.size() > 0) {
                    gridView.setVisibility(View.VISIBLE);
                }
                picAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageBack:
                finish();
                break;
            case R.id.viewAddPic:
                Intent intent = new Intent();
                /*选中数量限制*/
                mImagePicker.setSelectLimit(photoNum - picList.size());
                intent.setClass(BianJiCLActivity.this, ImageGridActivity.class);
                startActivityForResult(intent, Constant.REQUEST_RESULT_CODE.IMAGE_PICKER);
                break;
            default:
                break;
        }
    }

    class MyAdapter extends BaseAdapter {
        class ViewHolder {
            public ImageView image;
            public ImageView imageDelete;
        }

        @Override
        public int getCount() {
            return picList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(BianJiCLActivity.this).inflate(R.layout.item_grid_pic_bian_ji, null);
                holder.image = convertView.findViewById(R.id.image);
                holder.imageDelete = convertView.findViewById(R.id.imageDelete);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            Glide.with(BianJiCLActivity.this)
                    .load(picList.get(position).path)
                    .asBitmap()
                    .placeholder(R.mipmap.ic_empty)
                    .into(holder.image);
            holder.imageDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    picList.remove(position);
                    if (picList.size() == 0) {
                        gridView.setVisibility(View.GONE);
                    }
                    picAdapter.notifyDataSetChanged();
                }
            });
            return convertView;
        }
    }
}
