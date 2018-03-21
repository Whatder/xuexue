package com.nkbh.xuexue.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nkbh.xuexue.R;
import com.nkbh.xuexue.base.BaseActivity;
import com.nkbh.xuexue.base.CourseBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by User on 2018/3/21.
 */

public class CourseDetailActivity extends BaseActivity {


    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;

    CourseBean data;
    @BindView(R.id.ivDetailIcon)
    ImageView ivDetailIcon;
    @BindView(R.id.tvDetailTitle)
    TextView tvDetailTitle;
    @BindView(R.id.tvDetailSummary)
    TextView tvDetailSummary;
    @BindView(R.id.tvSummary)
    TextView tvSummary;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_course_detail;
    }

    @Override
    protected void initParameter() {
        data = (CourseBean) getIntent().getSerializableExtra("data");
        if (data == null)
            finish();

        Glide.with(this).load(data.getPicUrl()).into(ivDetailIcon);
        tvDetailTitle.setText(data.getTitle());
        tvDetailSummary.setText(data.getContent());
    }

    @OnClick(R.id.ivBack)
    void back() {
        finish();
    }

}
