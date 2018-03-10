package com.nkbh.xuexue.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.nkbh.xuexue.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by User on 2018/3/3.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private Unbinder unbinder;

    protected abstract int getLayoutID();

    protected abstract void initParameter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        unbinder = ButterKnife.bind(this);
        initParameter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null)
            unbinder.unbind();
    }

    public void setTitle(String titleText) {
        TextView title = findViewById(R.id.toolBarTitle);
        if (title != null) {
            title.setText(titleText);
        }
    }
}
