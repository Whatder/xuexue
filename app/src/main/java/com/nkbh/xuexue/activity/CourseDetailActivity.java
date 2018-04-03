package com.nkbh.xuexue.activity;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nkbh.xuexue.R;
import com.nkbh.xuexue.base.BaseActivity;
import com.nkbh.xuexue.bean.CourseBean;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

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

    @BindView(R.id.tvSummary)
    TextView tvSummary;
    @BindView(R.id.jzPlayer)
    JZVideoPlayerStandard jzPlayer;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_course_detail;
    }

    @Override
    protected void initParameter() {
        data = (CourseBean) getIntent().getSerializableExtra("data");
        if (data == null)
            finish();

        Glide.with(this).load(data.getThumbnail()).into(ivDetailIcon);
        tvDetailTitle.setText(data.getTitle());
        tvSummary.setText(data.getSummary());
        jzPlayer.setUp(data.getUrl(), JZVideoPlayer.SCREEN_WINDOW_NORMAL);
    }

    @OnClick(R.id.ivBack)
    void back() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        finish();
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }
}
