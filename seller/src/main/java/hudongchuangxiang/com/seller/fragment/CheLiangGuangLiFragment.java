package hudongchuangxiang.com.seller.fragment;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;

import hudongchuangxiang.com.seller.R;
import hudongchuangxiang.com.seller.activity.BianJiCLActivity;
import hudongchuangxiang.com.seller.activity.CheLiangXQActivity;
import hudongchuangxiang.com.seller.base.ZjbBaseFragment;
import hudongchuangxiang.com.seller.constant.Constant;
import hudongchuangxiang.com.seller.viewholder.CheLiangGLViewHolder;
import huisedebi.zjb.mylibrary.provider.DataProvider;
import huisedebi.zjb.mylibrary.util.DpUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class CheLiangGuangLiFragment extends ZjbBaseFragment implements SwipeRefreshLayout.OnRefreshListener {


    private View mInflate;
    private EasyRecyclerView recyclerView;
    private RecyclerArrayAdapter<Integer> adapter;
    private int page = 1;
    private AlertDialog guanLiDialog;
    private Dialog fenXiangDialog;
    private int positionType;
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action) {
                case Constant.BROADCASTCODE.CHE_LIANG_BIAN_JI_DIALOG:
                    int position = intent.getIntExtra(Constant.INTENT_KEY.position, -1);
                    int type = intent.getIntExtra(Constant.INTENT_KEY.TYPE, -1);
                    if (type == positionType) {
                        cheLiangGLDialog(position);
                    }
                    break;
                default:
                    break;
            }
        }
    };

    public CheLiangGuangLiFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public CheLiangGuangLiFragment(int position) {
        this.positionType = position;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mInflate == null) {
            mInflate = inflater.inflate(R.layout.fragment_che_liang_guang_li, container, false);
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
        recyclerView = mInflate.findViewById(R.id.recyclerView);
    }

    @Override
    protected void initViews() {
        initRecycler();
    }

    /**
     * 初始化recyclerview
     */
    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerDecoration itemDecoration = new DividerDecoration(Color.TRANSPARENT, (int) getResources().getDimension(R.dimen.line_width), 0, 0);
        itemDecoration.setDrawLastItem(false);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setRefreshingColorResources(R.color.basic_color);
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<Integer>(getActivity()) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_che_liang_gl;
                return new CheLiangGLViewHolder(parent, layout, positionType);
            }
        });
        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                page++;
                adapter.addAll(DataProvider.getPersonList(page));
            }

            @Override
            public void onMoreClick() {

            }
        });
        adapter.setNoMore(R.layout.view_nomore, new RecyclerArrayAdapter.OnNoMoreListener() {
            @Override
            public void onNoMoreShow() {

            }

            @Override
            public void onNoMoreClick() {
            }
        });
        adapter.setError(R.layout.view_error, new RecyclerArrayAdapter.OnErrorListener() {
            @Override
            public void onErrorShow() {
                adapter.resumeMore();
            }

            @Override
            public void onErrorClick() {
                adapter.resumeMore();
            }
        });
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), CheLiangXQActivity.class);
                startActivity(intent);
            }
        });
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                View view = new View(getActivity());
                view.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) DpUtils.convertDpToPixel(5, getActivity())));
                return view;
            }

            @Override
            public void onBindView(View headerView) {

            }
        });
        recyclerView.setRefreshListener(this);
    }

    /**
     * des： 车辆管理dialog
     * author： ZhangJieBo
     * date： 2017/11/30 0030 上午 9:56
     */
    private void cheLiangGLDialog(int position) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View dialog_chan_pin = inflater.inflate(R.layout.dialog_che_liang_gl, null);
        dialog_chan_pin.findViewById(R.id.btnCancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guanLiDialog.dismiss();
            }
        });
        guanLiDialog = new AlertDialog.Builder(getActivity(), R.style.dialog)
                .setView(dialog_chan_pin)
                .create();
        guanLiDialog.show();
        dialog_chan_pin.findViewById(R.id.viewFenXiang).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guanLiDialog.dismiss();
                showFenXiangDialog();
            }
        });
        dialog_chan_pin.findViewById(R.id.viewBianJi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), BianJiCLActivity.class);
                startActivity(intent);
                guanLiDialog.dismiss();
            }
        });
        Window dialogWindow = guanLiDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialogWindow.setWindowAnimations(R.style.dialogFenXiang);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = CheLiangGuangLiFragment.this.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 1); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);
    }

    /**
     * des： 分享dilog
     * author： ZhangJieBo
     * date： 2017/11/30 0030 上午 10:38
     */
    private void showFenXiangDialog() {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_fen_xiang_cl, null);
        final View viewDialog = inflate.findViewById(R.id.viewDialog);
        fenXiangDialog = new Dialog(getActivity(), R.style.FullScreendialog);
        fenXiangDialog.setContentView(inflate);
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.wangshanghuitan);
        animation.setFillAfter(true);
        if (animation != null) {
            viewDialog.startAnimation(animation);
        }
        inflate.findViewById(R.id.imageCancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fenXiangDialogDismiss(viewDialog);
            }
        });
        fenXiangDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                    fenXiangDialogDismiss(viewDialog);
                }
                return false;
            }
        });
        fenXiangDialog.show();
    }

    private void fenXiangDialogDismiss(View viewDialog) {
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.wangshanghuitan_ni);
        animation.setFillAfter(true);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                fenXiangDialog.dismiss();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        if (animation != null) {
            viewDialog.startAnimation(animation);
        }
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void initData() {
        onRefresh();
    }

    @Override
    public void onRefresh() {
        page++;
        adapter.clear();
        adapter.addAll(DataProvider.getPersonList(page));
    }

    @Override
    public void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.BROADCASTCODE.CHE_LIANG_BIAN_JI_DIALOG);
        getActivity().registerReceiver(receiver, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(receiver);
    }
}
