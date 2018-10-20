package com.gdky005.wxmoneydemo;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.BarUtils;

import team.zhuoke.sdk.base.BaseActivity;

public class LQActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_lq;
    }

    @Override
    protected void initViews() {
        BarUtils.setStatusBarAlpha(this, 0);
        BarUtils.setStatusBarLightMode(this, true);

        TextView textView = findViewById(R.id.right_btn);
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

    }
}
