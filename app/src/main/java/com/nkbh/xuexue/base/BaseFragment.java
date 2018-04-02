package com.nkbh.xuexue.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nkbh.xuexue.dialog.LoadingDialog;
import com.nkbh.xuexue.utils.ACache;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by User on 2018/3/6.
 */

public abstract class BaseFragment extends Fragment {
    protected Context mActivity;
    protected View mContentView;
    protected ACache aCache;
    protected LoadingDialog loadingDialog;
    Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = getContext();
        aCache = ACache.get(mActivity);
        loadingDialog = new LoadingDialog(mActivity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView = inflater.inflate(getLayoutID(), container, false);
        unbinder = ButterKnife.bind(this, mContentView);
        initParameter();
        return mContentView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null)
            unbinder.unbind();
    }

    protected abstract int getLayoutID();

    protected abstract void initParameter();
}
