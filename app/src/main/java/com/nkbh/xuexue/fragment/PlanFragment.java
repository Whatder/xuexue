package com.nkbh.xuexue.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.nkbh.xuexue.R;
import com.nkbh.xuexue.adapter.PlanFragmentAdapter;
import com.nkbh.xuexue.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

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
    String[] titles = {"已完成", "未完成"};

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_plan;
    }

    @Override
    protected void initParameter() {
        fragments.add(new PlanCompletedFragment());
        fragments.add(new PlanUncompletedFragment());
        adapter = new PlanFragmentAdapter(fragments, titles, getChildFragmentManager());
        viewPagerPlan.setAdapter(adapter);
        tabPlan.setupWithViewPager(viewPagerPlan);
    }
}
