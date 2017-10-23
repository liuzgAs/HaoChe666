package com.haoche666.buyer.util;

import android.content.Context;

import com.haoche666.buyer.model.OkObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/8/27.
 */
public class ApiClient {

    public interface CallBack {
        void onSuccess(String s);

        void onError(Response response);
    }

    public static void post(Context context, OkObject okObject, final CallBack callBack) {
        HashMap<String, String> params = okObject.getParams();
        params.put("platform","android");
        params.put("version",VersionUtils.getCurrVersion(context)+"");
        okObject.setParams(params);
        LogUtil.LogShitou("ApiClient--发送", "" + okObject.getJson());
        OkGo.post(okObject.getUrl())//
                .tag(context)//
                .upJson(okObject.getJson())//
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        callBack.onSuccess(s);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        callBack.onError(response);
                    }
                });
    }

    public static void postJson(Context context, String url, String json, final CallBack callBack) {
        LogUtil.LogShitou("ApiClient--发送", "" + json);
        OkGo.post(url)//
                .tag(context)//
                .upJson(json)//
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        callBack.onSuccess(s);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        callBack.onError(response);
                    }
                });
    }
}
