package com.nkbh.xuexue.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.nkbh.xuexue.R;
import com.nkbh.xuexue.base.BaseActivity;
import com.nkbh.xuexue.bean.admin.Admin;
import com.nkbh.xuexue.fragment.AdminMovieManagerFragment;
import com.nkbh.xuexue.fragment.AdminReplyManagerFragment;
import com.nkbh.xuexue.fragment.AdminTopicManagerFragment;
import com.nkbh.xuexue.fragment.AdminUserManagerFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by User on 2018/4/3.
 */

public class AdminManagerActivity extends BaseActivity {
    @BindView(R.id.toolBarTitle)
    TextView toolBarTitle;
    @BindView(R.id.flContent)
    FrameLayout flContent;
    @BindView(R.id.navMenu)
    NavigationView navMenu;
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    AdminUserManagerFragment userManagerFragment;
    AdminMovieManagerFragment movieManagerFragment;
    AdminTopicManagerFragment topicManagerFragment;
    AdminReplyManagerFragment replyManagerFragment;
    @BindView(R.id.currUserName)
    TextView currUserName;

    Admin currAdmin;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_admin_manager;
    }

    @Override
    protected void initParameter() {
        userManagerFragment = new AdminUserManagerFragment();
        movieManagerFragment = new AdminMovieManagerFragment();
        topicManagerFragment = new AdminTopicManagerFragment();
        replyManagerFragment = new AdminReplyManagerFragment();
        currAdmin = (Admin) aCache.getAsObject("admin");
        currUserName.setText("当前用户：" + currAdmin.getAccount());
        onTvStudentManagerClicked();
    }

    @OnClick(R.id.toolBarTitle)
    void openMenu() {
        drawerLayout.openDrawer(Gravity.START);
    }


    @OnClick(R.id.tvStudentManager)
    public void onTvStudentManagerClicked() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        setTitle("学员管理");
        transaction.replace(R.id.flContent, userManagerFragment);
        transaction.commit();
        drawerLayout.closeDrawers();
    }

    @OnClick(R.id.tvMovieManager)
    public void onTvMovieManagerClicked() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        setTitle("视频管理");
        transaction.replace(R.id.flContent, movieManagerFragment);
        transaction.commit();
        drawerLayout.closeDrawers();
    }

    @OnClick(R.id.tvTopicManager)
    public void onTvTopicManagerClicked() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        setTitle("主题管理");
        transaction.replace(R.id.flContent, topicManagerFragment);
        transaction.commit();
        drawerLayout.closeDrawers();
    }

    @OnClick(R.id.tvReplyManager)
    public void onTvReplyManagerClicked() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        setTitle("回复管理");
        transaction.replace(R.id.flContent, replyManagerFragment);
        transaction.commit();
        drawerLayout.closeDrawers();
    }

    @OnClick(R.id.tvChangePwd)
    public void onTvChangePwdClicked() {
    }

    @OnClick(R.id.tvLogOut)
    public void onTvLogOutClicked() {
        aCache.remove("admin");
        Intent intent = new Intent(AdminManagerActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
