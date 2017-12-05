package hudongchuangxiang.haoche666.seller.viewholder;

import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;

import java.util.ArrayList;
import java.util.List;

import hudongchuangxiang.haoche666.seller.R;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class DianPuShuJuViewHolder extends BaseViewHolder<Integer> {


    private final EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<Integer> adapter;

    public DianPuShuJuViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        recyclerView = $(R.id.recyclerView);
        initRecycler();
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
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<Integer>(getContext()) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_che_liang_gl;
                return new DianPuShuJuItemViewHolder(parent, layout,getDataPosition());
            }
        });
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
            }
        });
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.header_item_dian_pu_shuju, null);
                View textTip = view.findViewById(R.id.textTip);
                if (getAdapterPosition()==2){
                    textTip.setVisibility(View.VISIBLE);
                }else {
                    textTip.setVisibility(View.GONE);
                }
                return view;
            }

            @Override
            public void onBindView(View headerView) {

            }
        });
    }

    @Override
    public void setData(Integer data) {
        super.setData(data);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        adapter.clear();
        adapter.addAll(list);
    }

}
