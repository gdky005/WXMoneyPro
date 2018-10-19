package com.gdky005.wxmoneydemo;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;

import team.zhuoke.sdk.base.BaseActivity;
import team.zhuoke.sdk.component.ZKRecycleView;

/**
 * ActionBar 的使用： https://www.cnblogs.com/Peter-Chen/p/6421354.html
 */
public class MXActivity extends BaseActivity {

    MXAdapter mxAdapter;
    ArrayList<MXItem> list;

    ZKRecycleView recyclerView;

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                list.addAll(list);
                mxAdapter.setNewData(list);
                mxAdapter.loadMoreComplete();
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mx;
    }

    @Override
    protected void initViews() {
//        ActionBar actionBar = this.getActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); //Enable自定义的View
////            actionBar.setCustomView(R.layout.actionbar_custom);//设置自定义的布局：actionbar_custom
//        }

        recyclerView = findViewById(R.id.recycler_view);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            MXItem mxItem = new MXItem();

            mxItem.setName("转账支付_" + (i + 1));
            list.add(mxItem);
        }

        mxAdapter = new MXAdapter(list);
        mxAdapter.setLoadMoreView(new MXLoadingMore());
        mxAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                handler.sendEmptyMessageDelayed(0, 1000);
            }
        }, recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mxAdapter);
    }
}
