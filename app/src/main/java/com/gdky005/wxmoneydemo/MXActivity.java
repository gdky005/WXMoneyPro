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
import android.widget.RelativeLayout;
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

    private int page = 1;
    private static final int pageCount = 20;

    private static final String TAG = "MXActivity";
    // 首次进入延迟时间
    private static final long DELAY_TIME = 1 * 1000;

    MXAdapter mxAdapter;
    ArrayList<MXBean.ResultBean> list;

    TextView loadingTV;
    TextView topLqTV;
    ZKRecycleView recyclerView;
    RelativeLayout rl_close;
    HProgressBarLoading mTopProgress;

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                recyclerView.setVisibility(View.VISIBLE);
                topLqTV.setVisibility(View.VISIBLE);
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
        rl_close = findViewById(R.id.rl_close);

        BarUtils.setStatusBarAlpha(this, 0);
        BarUtils.setStatusBarLightMode(this, true);
    }


    @Override
    protected void initListener() {
        rl_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        list = new ArrayList<>();
        mxAdapter = new MXAdapter(list);
        mxAdapter.setLoadMoreView(new MXLoadingMore());
//        mxAdapter.setNotDoAnimationCount(100);
//        mxAdapter.openLoadAnimation(null);
        mxAdapter.enableLoadMoreEndClick(true);
        mxAdapter.setEnableLoadMore(true);
        mxAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
//                mxAdapter.setLoadMoreView(new MXLoadingMore());
                View view = mxAdapter.getViewByPosition(recyclerView, mxAdapter.getLoadMoreViewPosition(), R.id.load_more_loading_view);
                if (view != null) {
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            loadingTV = v.findViewById(R.id.loading_text);
                            loadingTV.setText("正在加载");

                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    requestData();
                                }
                            }, 500);
                        }
                    });
                }
            }
        }, recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mxAdapter);
        progress();
        requestData();

    }

    private void progress() {
        //如果进度条隐藏则让它显示
        if (View.GONE == mTopProgress.getVisibility()) {
            mTopProgress.setVisibility(View.VISIBLE);
        }
        handler.sendEmptyMessageDelayed(0, DELAY_TIME);
//        mTopProgress.setMax(1000);
        mTopProgress.setCurProgress(50, (long) (DELAY_TIME * 1.5), new HProgressBarLoading.OnEndListener() {
            @Override
            public void onEnd() {
                finishOperation(true);
            }
        });

        topLqTV.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    private void requestData() {
        String url = "https://www.zkteam.cc/WXMoney/lqMX?pageCount=" + pageCount + "&page=" + (page++);
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new MXCallBack() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "onError: ", e);
                        mxAdapter.loadMoreComplete();
                        setLoadingTV();
                    }

                    @Override
                    public void onResponse(MXBean response, int id) {
                        mxAdapter.loadMoreComplete();
                        setLoadingTV();
                        if (response != null) {
                            List<MXBean.ResultBean> mxBeans = response.getResult();
                            if (mxBeans != null) {

                                list.addAll(mxBeans);
                                mxAdapter.setNewData(list);

                            }
                        }
                    }
                });
    }

    private void setLoadingTV() {
        if (loadingTV != null) {
            loadingTV.setText("加载更多");
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
