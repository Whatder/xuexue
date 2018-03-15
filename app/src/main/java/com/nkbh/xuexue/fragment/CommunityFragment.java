package com.nkbh.xuexue.fragment;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nkbh.xuexue.R;
import com.nkbh.xuexue.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by User on 2018/3/6.
 */

public class CommunityFragment extends BaseFragment {
    @BindView(R.id.ivBanner)
    ImageView ivBanner;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.collapsingLayout)
    CollapsingToolbarLayout collapsingLayout;
    @BindView(R.id.appbar)
    AppBarLayout appbar;

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_community;
    }

    @Override
    protected void initParameter() {
    }
}
