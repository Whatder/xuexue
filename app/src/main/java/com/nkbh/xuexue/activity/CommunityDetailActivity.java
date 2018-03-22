package com.nkbh.xuexue.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nkbh.xuexue.R;
import com.nkbh.xuexue.adapter.CommunityReplyAdapter;
import com.nkbh.xuexue.base.BaseActivity;
import com.nkbh.xuexue.base.CommentBean;
import com.nkbh.xuexue.bean.CommunityReplyBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by User on 2018/3/22.
 */

public class CommunityDetailActivity extends BaseActivity {
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.rlHeader)
    RelativeLayout rlHeader;
    @BindView(R.id.ivProfilePic)
    CircleImageView ivProfilePic;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvContent)
    TextView tvContent;
    @BindView(R.id.rvReply)
    RecyclerView rvReply;

    CommunityReplyAdapter adapter;
    List<CommunityReplyBean> data = new ArrayList<>();
    CommentBean currentTheme;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_community_detail;
    }

    @Override
    protected void initParameter() {
        currentTheme = (CommentBean) getIntent().getSerializableExtra("data");
        if (currentTheme == null)
            finish();
        tvTitle.setText("详情");

        Glide.with(this).load(currentTheme.getProfilePic()).into(ivProfilePic);
        tvName.setText(currentTheme.getName());
        tvTime.setText(currentTheme.getTime());
        tvContent.setText(currentTheme.getContent());

        adapter = new CommunityReplyAdapter(this, data);
        rvReply.setLayoutManager(new LinearLayoutManager(this));
        rvReply.setAdapter(adapter);
        rvReply.setNestedScrollingEnabled(false);

        getData();
    }

    private void getData() {
        for (int i = 0; i < 10; i++) {
            CommunityReplyBean temp = new CommunityReplyBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1521720129230&di=e020e6bb5a818ea9d535669c635190d5&imgtype=jpg&src=http%3A%2F%2Fimg4.imgtn.bdimg.com%2Fit%2Fu%3D461511789%2C4187442821%26fm%3D214%26gp%3D0.jpg", "肥罗", "以前没有想过 有一天竟然会怀念那些被公鸡打鸣叫醒的日子", "2018-01-01");
            data.add(temp);
        }
        adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.ivBack)
    void back() {
        finish();
    }
}
