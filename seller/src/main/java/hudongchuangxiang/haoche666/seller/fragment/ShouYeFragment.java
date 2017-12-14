package hudongchuangxiang.haoche666.seller.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

import hudongchuangxiang.haoche666.seller.R;
import hudongchuangxiang.haoche666.seller.activity.GuanLiDYActivity;
import hudongchuangxiang.haoche666.seller.activity.JinRiXZActivity;
import hudongchuangxiang.haoche666.seller.activity.MainActivity;
import hudongchuangxiang.haoche666.seller.activity.YuYueSJActivity;
import hudongchuangxiang.haoche666.seller.base.MyDialog;
import hudongchuangxiang.haoche666.seller.base.ZjbBaseFragment;
import hudongchuangxiang.haoche666.seller.constant.Constant;
import hudongchuangxiang.haoche666.seller.model.IndexIndex;
import hudongchuangxiang.haoche666.seller.model.OkObject;
import hudongchuangxiang.haoche666.seller.util.ApiClient;
import huisedebi.zjb.mylibrary.util.GsonUtils;
import huisedebi.zjb.mylibrary.util.LogUtil;
import huisedebi.zjb.mylibrary.util.ScreenUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShouYeFragment extends ZjbBaseFragment implements View.OnClickListener {


    private View mInflate;
    private View viewBar;
    private TextView[] textViewNumValue = new TextView[4];
    private TextView[] textViewNumName = new TextView[4];
    private TextView[] textViewTabTitle = new TextView[2];
    private TextView[] textViewTabDes = new TextView[2];
    private int[] textViewNumValueId = new int[]{
            R.id.textNumValue01,
            R.id.textNumValue02,
            R.id.textNumValue03,
            R.id.textNumValue04,
    };
    private int[] textViewNumNameId = new int[]{
            R.id.textNumName01,
            R.id.textNumName02,
            R.id.textNumName03,
            R.id.textNumName04,
    };
    private int[] textViewTabTitleId = new int[]{
            R.id.textJinRiXZ,
            R.id.textDingYueCY,
    };
    private int[] textViewTabDesId = new int[]{
            R.id.textTabDes01,
            R.id.textTabDes02,
    };

    public ShouYeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mInflate == null) {
            mInflate = inflater.inflate(R.layout.fragment_shou_ye, container, false);
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
        viewBar = mInflate.findViewById(R.id.viewBar);
        for (int i = 0; i < textViewNumValue.length; i++) {
            textViewNumValue[i] = mInflate.findViewById(textViewNumValueId[i]);
            textViewNumName[i] = mInflate.findViewById(textViewNumNameId[i]);
        }
        for (int i = 0; i < textViewTabTitle.length; i++) {
            textViewTabTitle[i] = mInflate.findViewById(textViewTabTitleId[i]);
            textViewTabDes[i] = mInflate.findViewById(textViewTabDesId[i]);
        }
    }

    @Override
    protected void initViews() {
        viewBar.setPadding(0, ScreenUtils.getStatusBarHeight(getActivity()), 0, 0);
    }

    @Override
    protected void setListeners() {
        mInflate.findViewById(R.id.viewYuYueSJ).setOnClickListener(this);
        mInflate.findViewById(R.id.viewCheLiang).setOnClickListener(this);
        mInflate.findViewById(R.id.viewDingYueCY).setOnClickListener(this);
        mInflate.findViewById(R.id.viewJinRiXZ).setOnClickListener(this);
    }

    /**
     * des： 网络请求参数
     * author： ZhangJieBo
     * date： 2017/8/28 0028 上午 9:55
     */
    private OkObject getOkObject() {
        String url = Constant.HOST + Constant.Url.INDEX_INDEX;
        HashMap<String, String> params = new HashMap<>();
        if (isLogin) {
            params.put("uid", userInfo.getUid());
            params.put("tokenTime", tokenTime);
        }
        return new OkObject(params, url);
    }

    @Override
    protected void initData() {
        showLoadingDialog();
        ApiClient.post(getActivity(), getOkObject(), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                cancelLoadingDialog();
                LogUtil.LogShitou("ShouYeFragment--卖家版首页", s + "");
                try {
                    IndexIndex indexIndex = GsonUtils.parseJSON(s, IndexIndex.class);
                    if (indexIndex.getStatus() == 1) {
                        List<IndexIndex.NumBean> numBeanList = indexIndex.getNum();
                        for (int i = 0; i < numBeanList.size(); i++) {
                            textViewNumName[i].setText(numBeanList.get(i).getName());
                            textViewNumValue[i].setText(numBeanList.get(i).getValue()+"");
                        }
                        List<IndexIndex.TabBean> tabBeanList = indexIndex.getTab();
                        for (int i = 0; i < tabBeanList.size(); i++) {
                            textViewTabTitle[i].setText(tabBeanList.get(i).getTitle());
                            textViewTabDes[i].setText(tabBeanList.get(i).getDes());
                        }
                    } else if (indexIndex.getStatus() == 3) {
                        MyDialog.showReLoginDialog(getActivity());
                    } else {
                        Toast.makeText(getActivity(), indexIndex.getInfo(), Toast.LENGTH_SHORT).show();
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

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.viewCheLiang:
                ((MainActivity) getActivity()).mTabHost.setCurrentTab(1);
                break;
            case R.id.viewJinRiXZ:
                intent.setClass(getActivity(), JinRiXZActivity.class);
                startActivity(intent);
                break;
            case R.id.viewDingYueCY:
                intent.setClass(getActivity(), GuanLiDYActivity.class);
                startActivity(intent);
                break;
            case R.id.viewYuYueSJ:
                intent.setClass(getActivity(), YuYueSJActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
