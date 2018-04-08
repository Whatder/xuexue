package com.nkbh.xuexue.fragment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.nkbh.xuexue.R;
import com.nkbh.xuexue.activity.AdminAddMovieActivity;
import com.nkbh.xuexue.adapter.admin.MovieItemAdapter;
import com.nkbh.xuexue.base.BaseFragment;
import com.nkbh.xuexue.bean.CourseBean;
import com.nkbh.xuexue.bean.ResponseBean;
import com.nkbh.xuexue.network.RetrofitHelper;
import com.nkbh.xuexue.network.ServiceApi;
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
 * Created by User on 2018/4/3.
 */

public class AdminMovieManagerFragment extends BaseFragment {
    @BindView(R.id.rvManager)
    RecyclerView rvManager;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    MovieItemAdapter adapter;
    List<CourseBean> data = new ArrayList<>();
    @BindView(R.id.fabAdd)
    FloatingActionButton fabAdd;

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_admin_common_fragment;
    }

    @Override
    protected void initParameter() {
        adapter = new MovieItemAdapter(mActivity, data, new MovieItemAdapter.OnClickListener() {
            @Override
            public void OnDel(CourseBean data) {
                delMovieById(data.getId());
            }
        });
        rvManager.setLayoutManager(new LinearLayoutManager(mActivity));
        rvManager.setAdapter(adapter);
        getData(null);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getData(refreshLayout);
            }
        });
        fabAdd.setVisibility(View.VISIBLE);
    }

    private void getData(final RefreshLayout refreshLayout) {
        ServiceApi service = RetrofitHelper.getService();
        service.getAllMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBean<List<CourseBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBean<List<CourseBean>> value) {
                        if ("succ".equals(value.getStatus())) {
                            data.clear();
                            data.addAll(value.getData());
                            adapter.notifyDataSetChanged();
                        } else
                            ToastUtils.show(mActivity, value.getMsg());
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

    private void delMovieById(int id) {
        loadingDialog.show();
        Map<String, String> params = new HashMap<>();
        params.put("id", id + "");
        ServiceApi service = RetrofitHelper.getService();
        service.delMovie(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBean<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBean<String> value) {
                        loadingDialog.dismiss();
                        if ("succ".equals(value.getStatus()))
                            ToastUtils.show(mActivity, value.getData());

                        else
                            ToastUtils.show(mActivity, value.getMsg());

                        getData(null);
                    }

                    @Override
                    public void onError(Throwable e) {
                        loadingDialog.dismiss();
                        ToastUtils.show(mActivity, e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @OnClick(R.id.fabAdd)
    public void onViewClicked() {
        Intent intent = new Intent(mActivity, AdminAddMovieActivity.class);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        getData(null);
    }
}
