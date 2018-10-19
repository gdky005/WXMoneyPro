package com.gdky005.wxmoneydemo;

import com.chad.library.adapter.base.loadmore.LoadMoreView;

public class MXLoadingMore extends LoadMoreView {

    @Override
    public int getLayoutId() {
        return R.layout.item_mx_foot_view;
    }

    @Override
    protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    @Override
    protected int getLoadEndViewId() {
        return R.id.load_more_load_end_view;
    }

}
