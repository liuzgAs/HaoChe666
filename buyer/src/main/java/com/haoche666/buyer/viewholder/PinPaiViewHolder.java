package com.haoche666.buyer.viewholder;

import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ViewGroup;

import com.haoche666.buyer.R;
import com.haoche666.buyer.avtivity.PinPaiXCActivity;
import com.haoche666.buyer.model.CarCarparam;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;

import java.util.List;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class PinPaiViewHolder extends BaseViewHolder<CarCarparam.BrandBean> {

    private final EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<CarCarparam.BrandBean.ListBean> adapter;

    public PinPaiViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        recyclerView = $(R.id.recyclerView);
        initRecycler();
    }

    @Override
    public void setData(CarCarparam.BrandBean data) {
        super.setData(data);
        List<CarCarparam.BrandBean.ListBean> list = data.getList();
        adapter.clear();
        adapter.addAll(list);
    }

    /**
     * 初始化recyclerview
     */
    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) getContext().getResources().getDimension(R.dimen.line_width), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setRefreshingColorResources(R.color.basic_color);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<CarCarparam.BrandBean.ListBean>(getContext()) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_pinpai_xc;
                return new PinPaiXCViewHolder(parent, layout);
            }
        });
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                ((PinPaiXCActivity)getContext()).drawerLayout.openDrawer(((PinPaiXCActivity)getContext()).recyclerViewRight);
                ((PinPaiXCActivity)getContext()).brandName=adapter.getItem(position).getName();
                ((PinPaiXCActivity)getContext()).logoPath=adapter.getItem(position).getImg();
                ((PinPaiXCActivity)getContext()).recyclerViewRight.getRecyclerView().scrollToPosition(0);
                ((PinPaiXCActivity)getContext()).cheXi(adapter.getItem(position).getId());
            }
        });
    }

}
