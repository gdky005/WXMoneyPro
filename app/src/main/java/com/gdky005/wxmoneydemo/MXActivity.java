package com.gdky005.wxmoneydemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.TextView;

import com.blankj.utilcode.util.BarUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import team.zhuoke.sdk.base.BaseActivity;
import team.zhuoke.sdk.component.ZKRecycleView;

/**
 * ActionBar 的使用： https://www.cnblogs.com/Peter-Chen/p/6421354.html
 */
public class MXActivity extends BaseActivity {

    private static final String TAG = "MXActivity";

    MXAdapter mxAdapter;
    ArrayList<MXBean.ResultBean> list;

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
//        ArrayList newList = new ArrayList<>();

//        for (int i = 0; i < 10; i++) {
//            MXItem mxItem = new MXItem();
//
//            mxItem.setName("转账支付_" + (i + 1));
//            list.add(mxItem);
//        }

        mxAdapter = new MXAdapter(list);
        mxAdapter.setLoadMoreView(new MXLoadingMore());
        mxAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
//                handler.sendEmptyMessageDelayed(0, 1000);
                requestData();
            }
        }, recyclerView);

//        handler.sendEmptyMessageDelayed(0, 1000);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mxAdapter);



        if (isContinue = true) {
            //如果进度条隐藏则让它显示
            if (View.GONE == mTopProgress.getVisibility()) {
                mTopProgress.setVisibility(View.VISIBLE);
            }

            mTopProgress.setCurProgress(100, 2000, new HProgressBarLoading.OnEndListener() {
                @Override
                public void onEnd() {
                    finishOperation(true);
                    isContinue = false;
                }
            });
        }

        requestData();

    }

    private void requestData() {
        String url = "https://www.zkteam.cc/WXMoney/lqMX";
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new MXCallBack() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "onError: ", e);
                    }

                    @Override
                    public void onResponse(MXBean response, int id) {
                        if (response != null) {
                            List<MXBean.ResultBean> mxBeans = response.getResult();
                            if (mxBeans != null) {

                                list.addAll(mxBeans);
                                mxAdapter.setNewData(list);
                                mxAdapter.loadMoreComplete();

                                topLqTV.setVisibility(View.VISIBLE);
                            }
                        }
                    }

                });
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
