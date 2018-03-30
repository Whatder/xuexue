package com.nkbh.xuexue.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nkbh.xuexue.R;
import com.nkbh.xuexue.adapter.PlanItemAdapter;
import com.nkbh.xuexue.base.BaseFragment;
import com.nkbh.xuexue.bean.PlanBean;
import com.nkbh.xuexue.bean.ResponseBean;
import com.nkbh.xuexue.bean.UserBean;
import com.nkbh.xuexue.network.RetrofitHelper;
import com.nkbh.xuexue.network.ServiceApi;
import com.nkbh.xuexue.utils.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by User on 2018/3/11.
 */

public class PlanUncompletedFragment extends BaseFragment {
    @BindView(R.id.rvPlan)
    RecyclerView rvPlan;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    PlanItemAdapter adapter;
    List<PlanBean> data = new ArrayList<>();
    private UserBean curUser;

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_plan_status;
    }

    @Override
    protected void initParameter() {
        curUser = (UserBean) aCache.getAsObject("user");
        adapter = new PlanItemAdapter(mActivity, data, new PlanItemAdapter.Listener() {
            @Override
            public void onStatusChanged() {
                getData(null);
            }
        });
        rvPlan.setLayoutManager(new LinearLayoutManager(mActivity));
        rvPlan.setAdapter(adapter);
        getData(null);
        initRefresh();
    }

    @Override
    public void onResume() {
        super.onResume();
        getData(null);
    }

    private void getData(final RefreshLayout refreshLayout) {
        ServiceApi service = RetrofitHelper.getService();
        service.getPlan(curUser.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBean<List<PlanBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBean<List<PlanBean>> value) {
                        if ("succ".equals(value.getStatus())) {
                            data.clear();
                            for (PlanBean bean : value.getData()) {
                                if ("UNFINISH".equals(bean.getStatus()))
                                    data.add(bean);
                            }
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
