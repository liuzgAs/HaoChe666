package com.haoche666.buyer.base;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.util.Log;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.haoche666.buyer.R;
import com.haoche666.buyer.constant.Constant;
import com.luoxudong.app.threadpool.ThreadPoolHelp;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import huisedebi.zjb.mylibrary.util.LogUtil;


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

    public static void setOnSearchDoneListener(OnSearchDoneListener onOnSearchDoneListener) {
        MyDialog.onSearchDoneListener = onOnSearchDoneListener;
    }

    /**
     * 搜索dialog
     *
     * @param context
     * @param keywords
     */
    public static void showSearchDialog(final Context context, String keywords) {
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

    private static void wxShare(final IWXAPI api, final int flag, String url, String title, String des, final String img) {
        api.registerApp(Constant.WXAPPID);
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = url;
        final WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = title;
        msg.description = des;
        ThreadPoolHelp.Builder
                .cached()
                .builder()
                .execute(new Runnable() {
                    @Override
                    public void run() {
                        Bitmap bitmap = netPicToBmp(img);
                        msg.setThumbImage(bitmap);
                        SendMessageToWX.Req req = new SendMessageToWX.Req();
                        req.transaction = buildTransaction("webpage");
                        req.message = msg;
                        switch (flag) {
                            case 0:
                                req.scene = SendMessageToWX.Req.WXSceneSession;
                                break;
                            case 1:
                                req.scene = SendMessageToWX.Req.WXSceneTimeline;
                                break;
                            case 2:
                                req.scene = SendMessageToWX.Req.WXSceneFavorite;
                                break;
                            default:
                                break;
                        }
                        api.sendReq(req);
                        LogUtil.LogShitou("MyDialog--run", "11111");
                    }
                });
    }

    public static Bitmap netPicToBmp(String src) {
        try {
            Log.d("FileUtil", src);
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);

            //设置固定大小
            //需要的大小
            float newWidth = 200f;
            float newHeigth = 200f;

            //图片大小
            int width = myBitmap.getWidth();
            int height = myBitmap.getHeight();

            //缩放比例
            float scaleWidth = newWidth / width;
            float scaleHeigth = newHeigth / height;
            Matrix matrix = new Matrix();
            matrix.postScale(scaleWidth, scaleHeigth);

            Bitmap bitmap = Bitmap.createBitmap(myBitmap, 0, 0, width, height, matrix, true);
            return bitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }

    private static String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    /**
     * 检查微信版本是否支付支付或是否安装可支付的微信版本
     */
    public static boolean checkIsSupportedWeachatPay(IWXAPI api) {
        boolean isPaySupported = api.getWXAppSupportAPI() >= com.tencent.mm.opensdk.constants.Build.PAY_SUPPORTED_SDK_INT;
        return isPaySupported;
    }

    public static void share(final Context context, final IWXAPI api, final String url, final String title, final String des, final String img) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialog_shengji = inflater.inflate(R.layout.dianlog_share, null);
        final AlertDialog alertDialog1 = new AlertDialog.Builder(context, R.style.dialog)
                .setView(dialog_shengji)
                .create();
        alertDialog1.show();
        dialog_shengji.findViewById(R.id.textViewCancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog1.dismiss();
            }
        });
        dialog_shengji.findViewById(R.id.weixin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkIsSupportedWeachatPay(api)) {
                    Toast.makeText(context, "您暂未安装微信,请下载安装最新版本的微信", Toast.LENGTH_SHORT).show();
                    return;
                }
                wxShare(api, 0, url, title, des, img);
                alertDialog1.dismiss();
            }
        });
        dialog_shengji.findViewById(R.id.pengyouquan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkIsSupportedWeachatPay(api)) {
                    Toast.makeText(context, "您暂未安装微信,请下载安装最新版本的微信", Toast.LENGTH_SHORT).show();
                    return;
                }
                wxShare(api, 1, url, title, des, img);
                alertDialog1.dismiss();
            }
        });
        dialog_shengji.findViewById(R.id.relaShouCang).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wxShare(api, 2, url, title, des, img);
                alertDialog1.dismiss();
            }
        });
        Window dialogWindow = alertDialog1.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialogWindow.setWindowAnimations(R.style.dialogFenXiang);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 1); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);
    }

}
