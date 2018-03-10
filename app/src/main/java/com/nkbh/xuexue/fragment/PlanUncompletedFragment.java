package com.nkbh.xuexue.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nkbh.xuexue.R;
import com.nkbh.xuexue.adapter.PlanItemAdapter;
import com.nkbh.xuexue.base.BaseFragment;
import com.nkbh.xuexue.bean.PlanBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by User on 2018/3/11.
 */

public class PlanUncompletedFragment extends BaseFragment {
    @BindView(R.id.rvPlan)
    RecyclerView rvPlan;
    PlanItemAdapter adapter;
    List<PlanBean> data = new ArrayList<>();

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_plan_status;
    }

    @Override
    protected void initParameter() {
        adapter = new PlanItemAdapter(mActivity, data);
        rvPlan.setLayoutManager(new LinearLayoutManager(mActivity));
        rvPlan.setAdapter(adapter);
        getData();
    }

    private void getData() {
        for (int i = 0; i < 10; i++) {
            PlanBean bean = new PlanBean("标题" + i, "内容" + i, "2018-3-11", false);
            data.add(bean);
        }
        adapter.notifyDataSetChanged();
    }
}
