package com.gdky005.wxmoneydemo;


import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class MXAdapter extends BaseQuickAdapter<MXBean.ResultBean, BaseViewHolder> {

    public MXAdapter(@Nullable List<MXBean.ResultBean> data) {
        super(R.layout.item_mx, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MXBean.ResultBean item) {
        helper.setText(R.id.mx_name, item.getName());
        helper.setText(R.id.mx_time, item.getTime());

        String money = " " + item.getMoney();
        if (item.isSpendState()) {
            money = "+" + money;
            helper.setTextColor(R.id.money_tv, mContext.getResources().getColor(R.color.money_green));
        } else {
            money = "-" + money;
            helper.setTextColor(R.id.money_tv, mContext.getResources().getColor(R.color.money_black));
        }

        helper.setText(R.id.money_tv, money);
    }
}
