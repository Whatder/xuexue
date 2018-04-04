package com.nkbh.xuexue.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nkbh.xuexue.R;
import com.nkbh.xuexue.adapter.admin.TopicItemAdapter;
import com.nkbh.xuexue.base.BaseFragment;
import com.nkbh.xuexue.bean.ResponseBean;
import com.nkbh.xuexue.bean.TopicBean;
import com.nkbh.xuexue.network.RetrofitHelper;
import com.nkbh.xuexue.network.ServiceApi;
import com.nkbh.xuexue.utils.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by User on 2018/4/3.
 */

public class AdminTopicManagerFragment extends BaseFragment {
    @BindView(R.id.rvManager)
    RecyclerView rvManager;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    TopicItemAdapter adapter;
    List<TopicBean> data = new ArrayList<>();

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_admin_common_fragment;
    }

    @Override
    protected void initParameter() {
        adapter = new TopicItemAdapter(mActivity, data, new TopicItemAdapter.OnDeleteClick() {
            @Override
            public void OnDeleted(TopicBean topicBean) {
                delTopic(topicBean.getId());
            }
        });
        rvManager.setLayoutManager(new LinearLayoutManager(mActivity));
        rvManager.setAdapter(adapter);
        getData(null);
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

    private void delTopic(int id) {
        loadingDialog.show();
        Map<String, String> params = new HashMap<>();
        params.put("id", id + "");
        ServiceApi service = RetrofitHelper.getService();
        service.delTopic(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBean<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBean<String> value) {
                        loadingDialog.dismiss();
                        if ("succ".equals(value.getStatus())) {
                            ToastUtils.show(mActivity, value.getData());
                            getData(null);
                        } else
                            ToastUtils.show(mActivity, value.getMsg());
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
}
