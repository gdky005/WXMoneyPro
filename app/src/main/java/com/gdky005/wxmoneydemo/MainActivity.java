package com.gdky005.wxmoneydemo;

import android.content.Intent;
import android.view.View;

import com.blankj.utilcode.util.BarUtils;

import team.zhuoke.sdk.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {

        BarUtils.setStatusBarAlpha(this, 0);
        BarUtils.setStatusBarLightMode(this, true);
    }

    @Override
    protected void initListener() {
        findViewById(R.id.mx_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MXActivity.class);
                mContext.startActivity(intent);
            }
        });

        findViewById(R.id.lq_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, LQActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {

    }
}
