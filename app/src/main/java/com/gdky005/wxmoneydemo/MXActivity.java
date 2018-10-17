package com.gdky005.wxmoneydemo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;

import team.zhuoke.sdk.component.ZKRecycleView;

public class MXActivity extends AppCompatActivity {

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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mx);

        recyclerView = findViewById(R.id.recycler_view);


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
//                mxAdapter.openLoadAnimation();
//                mxAdapter.notifyLoadMoreToLoading();

                handler.sendEmptyMessageDelayed(0, 1000);

//                try {
//                    Thread.sleep(3000);
//
//                    list.addAll(list);
//                    mxAdapter.notifyDataSetChanged();
//                    mxAdapter.loadMoreEnd();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }

        }, recyclerView);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mxAdapter);


    }
}
