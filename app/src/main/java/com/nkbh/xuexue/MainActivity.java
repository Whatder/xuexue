package com.nkbh.xuexue;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.bottomNav)
    BottomNavigationBar bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initBottomNav();
    }

    private void initBottomNav() {
        bottomNav.addItem(new BottomNavigationItem(R.drawable.ic_launcher, "计划")).setActiveColor(R.color.colorPrimary)
                .addItem(new BottomNavigationItem(R.drawable.ic_launcher, "学习")).setActiveColor(R.color.colorPrimary)
                .addItem(new BottomNavigationItem(R.drawable.ic_launcher, "交流")).setActiveColor(R.color.colorPrimary)
                .initialise();
    }
}
