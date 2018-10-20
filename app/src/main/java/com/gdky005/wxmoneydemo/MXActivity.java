package com.gdky005.wxmoneydemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.TextView;

import com.blankj.utilcode.util.BarUtils;
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

    TextView topLqTV;
    ZKRecycleView recyclerView;
    HProgressBarLoading mTopProgress;
    boolean isContinue = true;

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                topLqTV.setVisibility(View.VISIBLE);

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
        topLqTV = findViewById(R.id.top_lq);
        mTopProgress = findViewById(R.id.top_progress);
        recyclerView = findViewById(R.id.recycler_view);

        BarUtils.setStatusBarAlpha(this, 0);
        BarUtils.setStatusBarLightMode(this, true);

        topLqTV.setVisibility(View.INVISIBLE);
    }


    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        list = new ArrayList<>();
        ArrayList newList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            MXItem mxItem = new MXItem();

            mxItem.setName("转账支付_" + (i + 1));
            list.add(mxItem);
        }

        mxAdapter = new MXAdapter(newList);
        mxAdapter.setLoadMoreView(new MXLoadingMore());
        mxAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                handler.sendEmptyMessageDelayed(0, 1000);
            }
        }, recyclerView);

        handler.sendEmptyMessageDelayed(0, 1000);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mxAdapter);



        if (isContinue = true) {
            //如果进度条隐藏则让它显示
            if (View.GONE == mTopProgress.getVisibility()) {
                mTopProgress.setVisibility(View.VISIBLE);
            }

            mTopProgress.setCurProgress(100, 3000, new HProgressBarLoading.OnEndListener() {
                @Override
                public void onEnd() {
                    finishOperation(true);
                    isContinue = false;
                }
            });
        }

    }


    /**
     * 结束进行的操作
     */
    private void finishOperation(boolean flag) {
        //最后加载设置100进度
        mTopProgress.setNormalProgress(100);
        hideProgressWithAnim();
    }

    /**
     * 隐藏加载对话框
     */
    private void hideProgressWithAnim() {
        AnimationSet animation = getDismissAnim(this);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mTopProgress.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        mTopProgress.startAnimation(animation);
    }

    /**
     * 获取消失的动画
     *
     * @param context
     * @return
     */
    private AnimationSet getDismissAnim(Context context) {
        AnimationSet dismiss = new AnimationSet(context, null);
        AlphaAnimation alpha = new AlphaAnimation(1.0f, 0.0f);
        alpha.setDuration(1000);
        dismiss.addAnimation(alpha);
        return dismiss;
    }
}
