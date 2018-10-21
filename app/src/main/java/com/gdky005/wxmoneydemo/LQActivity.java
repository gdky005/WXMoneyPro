package com.gdky005.wxmoneydemo;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.BarUtils;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.Call;
import team.zhuoke.sdk.base.BaseActivity;

public class LQActivity extends BaseActivity {
    private static final String TAG = "LQActivity";

    TextView lqTv;
    TextView lqMoneyTv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_lq;
    }

    @Override
    protected void initViews() {
        BarUtils.setStatusBarAlpha(this, 0);
        BarUtils.setStatusBarLightMode(this, true);

        TextView textView = findViewById(R.id.right_btn);

        lqTv = findViewById(R.id.lq_tv);
        lqMoneyTv = findViewById(R.id.lq_money_tv);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MXActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

        String url = "https://www.zkteam.cc/WXMoney/qb";
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new LQCallBack() {
                    @Override
                    public void onError(Call call, Exception e, int id) {


                        Log.e(TAG, "onError: ", e);
                    }

                    @Override
                    public void onResponse(LQBean response, int id) {

                        if (response != null) {
                            LQBean.ResultBean lqBean = response.getResult();
                            if (lqBean != null) {
                                lqTv.setText(lqBean.getName());
                                lqMoneyTv.setText(lqBean.getMoney());
                            }
                        }

                    }
                });

    }
}
