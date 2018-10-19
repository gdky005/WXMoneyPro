package com.gdky005.wxmoneydemo;

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
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }
}
