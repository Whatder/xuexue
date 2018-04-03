package com.nkbh.xuexue.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nkbh.xuexue.R;
import com.nkbh.xuexue.adapter.CommunityReplyAdapter;
import com.nkbh.xuexue.base.BaseActivity;
import com.nkbh.xuexue.bean.TopicBean;
import com.nkbh.xuexue.bean.CommunityReplyBean;
import com.nkbh.xuexue.bean.ResponseBean;
import com.nkbh.xuexue.network.RetrofitHelper;
import com.nkbh.xuexue.network.ServiceApi;
import com.nkbh.xuexue.utils.TimeUtils;
import com.nkbh.xuexue.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
    @BindView(R.id.fabAddReply)
    FloatingActionButton fabAddReply;

    CommunityReplyAdapter adapter;
    List<CommunityReplyBean> data = new ArrayList<>();
    TopicBean currentTopic;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_community_detail;
    }

    @Override
    protected void initParameter() {
        currentTopic = (TopicBean) getIntent().getSerializableExtra("data");
        if (currentTopic == null)
            finish();
        tvTitle.setText("详情");

        Glide.with(this).load(currentTopic.getProfile_pic()).into(ivProfilePic);
        tvName.setText(currentTopic.getName());
        tvTime.setText(TimeUtils.stamp2String(currentTopic.getCreate_time()));
        tvContent.setText(currentTopic.getContent());

        adapter = new CommunityReplyAdapter(this, data);
        rvReply.setLayoutManager(new LinearLayoutManager(this));
        rvReply.setAdapter(adapter);
        rvReply.setNestedScrollingEnabled(false);

        getData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    private void getData() {
        ServiceApi service = RetrofitHelper.getService();
        service.getReplyById(currentTopic.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBean<List<CommunityReplyBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBean<List<CommunityReplyBean>> value) {
                        if ("succ".equals(value.getStatus())) {
                            data.clear();
                            data.addAll(value.getData());
                            adapter.notifyDataSetChanged();
                        } else {
                            ToastUtils.show(CommunityDetailActivity.this, value.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.show(CommunityDetailActivity.this, e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @OnClick(R.id.ivBack)
    void back() {
        finish();
    }

    @OnClick(R.id.fabAddReply)
    void onAddReply() {
        Intent intent = new Intent(CommunityDetailActivity.this, PostReplyActivity.class);
        intent.putExtra("topic", currentTopic);
        startActivity(intent);
    }
}
