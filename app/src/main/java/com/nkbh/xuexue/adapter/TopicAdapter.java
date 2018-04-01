package com.nkbh.xuexue.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nkbh.xuexue.R;
import com.nkbh.xuexue.activity.CommunityDetailActivity;
import com.nkbh.xuexue.base.TopicBean;
import com.nkbh.xuexue.utils.TimeUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by User on 2018/3/17.
 */

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.ViewHolder> {
    private Context mContext;
    private List<TopicBean> data;

    public TopicAdapter(Context mContext, List<TopicBean> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_community, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Glide.with(mContext).load(data.get(position).getProfile_pic()).into(holder.ivProfilePic);
        holder.tvName.setText(data.get(position).getName());
        holder.tvTime.setText(TimeUtils.stamp2String(data.get(position).getCreate_time()));
        holder.tvContent.setText(data.get(position).getContent());
        holder.tvLike.setText("赞(" + data.get(position).getLike_count() + ")");
        holder.rlItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, CommunityDetailActivity.class);
                intent.putExtra("data", data.get(position));
                mContext.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((Activity) mContext, holder.rlItem, "topicLayout").toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rlItem)
        RelativeLayout rlItem;
        @BindView(R.id.ivProfilePic)
        CircleImageView ivProfilePic;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvTime)
        TextView tvTime;
        @BindView(R.id.tvContent)
        TextView tvContent;
        @BindView(R.id.tvLike)
        TextView tvLike;
        @BindView(R.id.tvComment)
        TextView tvComment;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
