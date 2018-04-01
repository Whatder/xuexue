package com.nkbh.xuexue.fragment;

import android.content.Intent;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nkbh.xuexue.R;
import com.nkbh.xuexue.activity.PostArticleActivity;
import com.nkbh.xuexue.adapter.TopicAdapter;
import com.nkbh.xuexue.base.BaseFragment;
import com.nkbh.xuexue.base.TopicBean;
import com.nkbh.xuexue.bean.ResponseBean;
import com.nkbh.xuexue.network.RetrofitHelper;
import com.nkbh.xuexue.network.ServiceApi;
import com.nkbh.xuexue.utils.DmUtils;
import com.nkbh.xuexue.utils.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by User on 2018/3/6.
 */

public class CommunityFragment extends BaseFragment {
    @BindView(R.id.ivBanner)
    ImageView ivBanner;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.collapsingLayout)
    CollapsingToolbarLayout collapsingLayout;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.rvCommunity)
    RecyclerView rvCommunity;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    List<TopicBean> data = new ArrayList<>();
    TopicAdapter adapter;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_community;
    }

    @Override
    protected void initParameter() {
        Glide.with(this).load("https://timgsa.baidu.com/https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1521720543080&di=86a94e285ed789196dae98e345724404&imgtype=0&src=http%3A%2F%2Fimg2.downza.cn%2Fsoft%2Fgqbz-554%2F2015-10-16%2F7d611cb8fe436d95760b75549f4aa4fd.jpg").into(ivBanner);
        adapter = new TopicAdapter(mActivity, data);
        rvCommunity.setLayoutManager(new LinearLayoutManager(mActivity));
        rvCommunity.setAdapter(adapter);
        rvCommunity.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.bottom = DmUtils.dp2px(mActivity, 10);
            }
        });
        getData(null);
        initRefresh();
    }

    @Override
    public void onResume() {
        super.onResume();
        getData(null);
    }

    @OnClick(R.id.fab)
    void postArticle() {
        Intent intent = new Intent(mActivity, PostArticleActivity.class);
        startActivity(intent);
    }

    private void getData(final RefreshLayout refreshLayout) {
        ServiceApi service = RetrofitHelper.getService();
        service.getTopic()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBean<List<TopicBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBean<List<TopicBean>> value) {
                        if ("succ".equals(value.getStatus())) {
                            data.clear();
                            data.addAll(value.getData());
                            adapter.notifyDataSetChanged();
                        } else {
                            ToastUtils.show(mActivity, value.getMsg());
                        }
                        if (refreshLayout != null)
                            refreshLayout.finishRefresh();
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.show(mActivity, e.getMessage());
                        if (refreshLayout != null)
                            refreshLayout.finishRefresh();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initRefresh() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getData(refreshLayout);
            }
        });
    }
}
