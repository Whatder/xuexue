package com.nkbh.xuexue.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.nkbh.xuexue.R;
import com.nkbh.xuexue.base.BaseActivity;
import com.nkbh.xuexue.fragment.CommunityFragment;
import com.nkbh.xuexue.fragment.PlanFragment;
import com.nkbh.xuexue.fragment.StudyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {


    @BindView(R.id.bottomNav)
    BottomNavigationBar bottomNav;
    List<Fragment> fragments = new ArrayList<>();

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initParameter() {
        fragments.add(new PlanFragment());
        fragments.add(new StudyFragment());
        fragments.add(new CommunityFragment());
        ChangeFragmentView(fragments.get(0));
        initBottomNav();
    }

    private void initBottomNav() {
        bottomNav.addItem(new BottomNavigationItem(R.drawable.icon_list, "计划")).setActiveColor(R.color.colorPrimary)
                .addItem(new BottomNavigationItem(R.drawable.icon_study, "学习")).setActiveColor(R.color.colorPrimary)
                .addItem(new BottomNavigationItem(R.drawable.icon_community, "交流")).setActiveColor(R.color.colorPrimary)
                .initialise();

        bottomNav.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                ChangeFragmentView(fragments.get(position));
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }

    private void ChangeFragmentView(Fragment fragment) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.mainPanel, fragment);
        ft.commit();
    }
}
