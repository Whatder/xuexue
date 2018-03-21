package com.nkbh.xuexue.activity;

import android.widget.ImageView;
import android.widget.TextView;

import com.nkbh.xuexue.R;
import com.nkbh.xuexue.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by User on 2018/3/21.
 */

public class CourseDetailActivity extends BaseActivity {


    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_course_detail;
    }

    @Override
    protected void initParameter() {

    }

    @OnClick(R.id.ivBack)
    void back() {
        finish();
    }

}
