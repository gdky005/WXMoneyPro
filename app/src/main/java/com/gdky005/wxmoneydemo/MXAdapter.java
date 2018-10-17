package com.gdky005.wxmoneydemo;


import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class MXAdapter extends BaseQuickAdapter<MXItem, BaseViewHolder> {

    public MXAdapter(@Nullable List<MXItem> data) {
        super(R.layout.item_mx, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MXItem item) {
        helper.setText(R.id.mx_name, item.getName());
    }
}
