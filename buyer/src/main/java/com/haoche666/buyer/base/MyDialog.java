package com.haoche666.buyer.base;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.haoche666.buyer.R;
import com.luoxudong.app.threadpool.ThreadPoolHelp;


/**
 * Created by Administrator on 2017/8/27.
 */
public class MyDialog {
    public static void showReLoginDialog(final Context context) {
        final AlertDialog reLoginDialog = new AlertDialog.Builder(context)
                .setTitle("提示")
                .setMessage("您的账号在其他设备上登录，请重新登录")
                .setCancelable(false)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ToLoginActivity.toLoginActivity(context);
                    }
                })
                .create();
        reLoginDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                    reLoginDialog.dismiss();
                    ToLoginActivity.toLoginActivity(context);
                }
                return false;
            }
        });
        reLoginDialog.show();
    }

    /**
     * 单个按钮无操作
     *
     * @param msg
     */
    public static void showTipDialog(Context context, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(msg)
                .setPositiveButton("是", null)
                .create()
                .show();
    }

    public static void dialogResultFinish(final Context context, String msg, final int code) {
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setMessage(msg)
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ((ZjbBaseActivity) context).setResult(code);
                        ((ZjbBaseActivity) context).finish();
                    }
                })
                .create();
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                    dialog.dismiss();
                    ((ZjbBaseActivity) context).setResult(code);
                    ((ZjbBaseActivity) context).finish();
                }
                return false;
            }
        });
        dialog.setCancelable(false);
        dialog.show();
    }

    public static void dialogFinish(final Activity activity, String msg) {
        AlertDialog dialog = new AlertDialog.Builder(activity)
                .setMessage(msg)
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        activity.finish();
                    }
                })
                .create();
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                    dialog.dismiss();
                    activity.finish();
                }
                return false;
            }
        });
        dialog.setCancelable(false);
        dialog.show();
    }

    public static OnChoosePicListener onChoosePicListener;

    public interface OnChoosePicListener {
        void chaKan();

        void shangChuan();
    }

    public static void setOnChoosePicListener(OnChoosePicListener onChoosePicListener) {
        MyDialog.onChoosePicListener = onChoosePicListener;
    }

    /**
     * des： 选择图片
     * author： ZhangJieBo
     * date： 2017/11/6 0006 上午 9:36
     */
    public static void choosePic(Context context) {
        View dialog_tu_pian = LayoutInflater.from(context).inflate(R.layout.dialog_tu_pian, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(context, R.style.dialog)
                .setView(dialog_tu_pian)
                .create();
        dialog_tu_pian.findViewById(R.id.textChaKan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                onChoosePicListener.chaKan();
            }
        });
        dialog_tu_pian.findViewById(R.id.textShangChuan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                onChoosePicListener.shangChuan();
            }
        });
        dialog_tu_pian.findViewById(R.id.textQuXiao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
        Window dialogWindow = alertDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialogWindow.setWindowAnimations(R.style.dialogFenXiang);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 1); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);
    }

    /**
     * des： 预览图片
     * author： ZhangJieBo
     * date： 2017/11/6 0006 上午 9:34
     */
    public static void showPicDialog(Context context, String img) {
        View dialog_img_show = LayoutInflater.from(context).inflate(R.layout.dialog_img_show, null);
        ImageView imageView = (ImageView) dialog_img_show.findViewById(R.id.imageView);
        Glide.with(context)
                .load(img)
                .asBitmap()
                .dontAnimate()
                .placeholder(R.mipmap.ic_empty)
                .into(imageView);
        final AlertDialog alertDialog = new AlertDialog.Builder(context, R.style.dialog)
                .setView(dialog_img_show)
                .create();
        alertDialog.show();
        Window dialogWindow = alertDialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        dialogWindow.setWindowAnimations(R.style.AnimFromTopToButtom);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.6); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);
    }

    public interface OnSearchDoneListener {
        void searchDone(String keywords);
    }

    public static OnSearchDoneListener onSearchDoneListener;

    public static void setOnSearchDoneListener(OnSearchDoneListener onOnSearchDoneListener){
        MyDialog.onSearchDoneListener = onOnSearchDoneListener;
    }

    /**
     * 搜索dialog
     * @param context
     * @param keywords
     */
    public static void showSearchDialog(final Context context, String keywords){
        View dialog_tu_pian = LayoutInflater.from(context).inflate(R.layout.dialog_search, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(context, R.style.dialog)
                .setView(dialog_tu_pian)
                .create();
        alertDialog.show();
        final EditText editSearch = dialog_tu_pian.findViewById(R.id.editSearch);
        editSearch.setText(keywords);
        editSearch.setSelection(keywords.length());
        dialog_tu_pian.findViewById(R.id.imageSearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) v
                        .getContext().getSystemService(
                                Context.INPUT_METHOD_SERVICE);
                if (imm.isActive()) {
                    imm.hideSoftInputFromWindow(
                            v.getApplicationWindowToken(), 0);
                }
                alertDialog.dismiss();
                onSearchDoneListener.searchDone(editSearch.getText().toString().trim());
            }
        });
        editSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent keyEvent) {
                         /*判断是否是“GO”键*/
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    /*隐藏软键盘*/
                    InputMethodManager imm = (InputMethodManager) v
                            .getContext().getSystemService(
                                    Context.INPUT_METHOD_SERVICE);
                    if (imm.isActive()) {
                        imm.hideSoftInputFromWindow(
                                v.getApplicationWindowToken(), 0);
                    }
                    alertDialog.dismiss();
                    onSearchDoneListener.searchDone(editSearch.getText().toString().trim());
                    return true;
                }
                return false;
            }
        });
        Window dialogWindow = alertDialog.getWindow();
        dialogWindow.setGravity(Gravity.TOP);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics();
        lp.width = (int) (d.widthPixels * 1);
        dialogWindow.setAttributes(lp);
        ThreadPoolHelp.Builder
                .cached()
                .builder()
                .execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(200);
                            //设置可获得焦点
                            editSearch.setFocusable(true);
                            editSearch.setFocusableInTouchMode(true);
                            //请求获得焦点
                            editSearch.requestFocus();
                            //调用系统输入法
                            InputMethodManager inputManager = (InputMethodManager) editSearch
                                    .getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            inputManager.showSoftInput(editSearch, 0);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

}
