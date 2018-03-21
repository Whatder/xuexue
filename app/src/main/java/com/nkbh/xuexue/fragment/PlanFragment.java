package com.nkbh.xuexue.fragment;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.nkbh.xuexue.R;
import com.nkbh.xuexue.activity.AddPlanActivity;
import com.nkbh.xuexue.adapter.PlanFragmentAdapter;
import com.nkbh.xuexue.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by User on 2018/3/6.
 */

public class PlanFragment extends BaseFragment {
    @BindView(R.id.tabPlan)
    TabLayout tabPlan;
    @BindView(R.id.viewPagerPlan)
    ViewPager viewPagerPlan;

    PlanFragmentAdapter adapter;
    List<Fragment> fragments = new ArrayList<>();
    String[] titles = {"未完成", "已完成"};
    @BindView(R.id.fabAddTask)
    FloatingActionButton fabAddTask;

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_plan;
    }

    @Override
    protected void initParameter() {
        fragments.clear();
        fragments.add(new PlanUncompletedFragment());
        fragments.add(new PlanCompletedFragment());
        adapter = new PlanFragmentAdapter(fragments, titles, getChildFragmentManager());
        viewPagerPlan.setAdapter(adapter);
        tabPlan.setupWithViewPager(viewPagerPlan);
    }

    @OnClick(R.id.fabAddTask)
    void add() {
        Intent intent = new Intent(mActivity, AddPlanActivity.class);
        mActivity.startActivity(intent);
    }

}
