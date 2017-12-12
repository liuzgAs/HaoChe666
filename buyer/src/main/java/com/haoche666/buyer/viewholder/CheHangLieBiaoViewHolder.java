package com.haoche666.buyer.viewholder;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.haoche666.buyer.R;
import com.haoche666.buyer.avtivity.CheHangXXActivity;
import com.haoche666.buyer.constant.Constant;
import com.haoche666.buyer.model.Store;
import com.haoche666.buyer.util.GlideRoundTransform;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;

import java.util.List;

import huisedebi.zjb.mylibrary.util.DpUtils;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class CheHangLieBiaoViewHolder extends BaseViewHolder<Store.DataBean> {

    private final EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<Store.DataBean.CarBean> adapter;
    private final ImageView imageLogo;
    private final TextView textName;
    private final TextView textText;
    Store.DataBean data;
    private final View viewHeng;

    public CheHangLieBiaoViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        imageLogo = $(R.id.imageLogo);
        recyclerView = $(R.id.recyclerView);
        textName = $(R.id.textName);
        textText = $(R.id.textText);
        viewHeng = $(R.id.viewHeng);
        $(R.id.viewJinRuCH).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(Constant.IntentKey.ID, data.getId());
                intent.setClass(getContext(), CheHangXXActivity.class);
                getContext().startActivity(intent);
            }
        });
        initRecycler();
    }

    @Override
    public void setData(Store.DataBean data) {
        super.setData(data);
        this.data = data;
        Glide.with(getContext())
                .load(data.getLogo())
                .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(),(int)DpUtils.convertDpToPixel(6f,getContext())))
                .placeholder(R.mipmap.ic_empty)
                .crossFade()
                .into(imageLogo);
        textName.setText(data.getName());
        textText.setText(data.getText());
        List<Store.DataBean.CarBean> carBeanList = data.getCar();
        if (carBeanList!=null){
            if (carBeanList.size() > 0) {
                adapter.clear();
                adapter.addAll(carBeanList);
            } else {
                viewHeng.setVisibility(View.GONE);
            }
        }else {
            viewHeng.setVisibility(View.GONE);
        }
    }

    /**
     * 初始化recyclerview
     */
    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter = new RecyclerArrayAdapter<Store.DataBean.CarBean>(getContext()) {

            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_chehangliebiaoimg;
                return new CheHangXiaoImgViewHolder(parent, layout);
            }
        });
        SpaceDecoration spaceDecoration = new SpaceDecoration((int) DpUtils.convertDpToPixel(10, getContext()));
        spaceDecoration.setPaddingEdgeSide(false);
        recyclerView.addItemDecoration(spaceDecoration);
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                View view = new View(getContext());
                view.setLayoutParams(new ViewGroup.LayoutParams((int) DpUtils.convertDpToPixel(10f, getContext()), ViewGroup.LayoutParams.MATCH_PARENT));
                return view;
            }

            @Override
            public void onBindView(View headerView) {

            }
        });
    }

}
