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
import com.nkbh.xuexue.bean.UserBean;
import com.nkbh.xuexue.network.RetrofitHelper;
import com.nkbh.xuexue.network.ServiceApi;
import com.nkbh.xuexue.utils.DmUtils;
import com.nkbh.xuexue.utils.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    UserBean currUser;

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_community;
    }

    @Override
    protected void initParameter() {
        currUser = (UserBean) aCache.getAsObject("user");
        Glide.with(this).load(currUser.getProfile_pic()).into(ivBanner);
        adapter = new TopicAdapter(mActivity, data, new TopicAdapter.onClickLike() {
            @Override
            public void onLike(int topic_id, int position) {
                likeTopic(topic_id, position);
            }
        });
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

    private void initRefresh() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getData(refreshLayout);
            }
        });
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

    private void likeTopic(int topic_id, final int position) {
        Map<String, String> params = new HashMap<>();
        params.put("id", topic_id + "");
        ServiceApi service = RetrofitHelper.getService();
        service.likeTopic(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBean<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBean<String> value) {
                        if ("succ".equals(value.getStatus())) {
                            ToastUtils.show(mActivity, value.getData());
                            adapter.onLikeCountAdd(position);
                        } else
                            ToastUtils.show(mActivity, value.getMsg());
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.show(mActivity, e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
