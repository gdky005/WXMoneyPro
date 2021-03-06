package com.gdky005.wxmoneydemo;


import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;

import okhttp3.Response;

public abstract class LQCallBack extends Callback<LQBean> {

    @Override
    public LQBean parseNetworkResponse(Response response, int id) throws IOException {
        String string = response.body().string();
        return new Gson().fromJson(string, LQBean.class);
    }
}
