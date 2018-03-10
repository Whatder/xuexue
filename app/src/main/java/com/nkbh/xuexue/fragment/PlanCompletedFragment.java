package com.nkbh.xuexue.fragment;

import android.support.v7.widget.RecyclerView;

import com.nkbh.xuexue.R;
import com.nkbh.xuexue.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by User on 2018/3/11.
 */

public class PlanCompletedFragment extends BaseFragment {
    @BindView(R.id.rvPlan)
    RecyclerView rvPlan;

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_plan_status;
    }

    @Override
    protected void initParameter() {

    }
}
