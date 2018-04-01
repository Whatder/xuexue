package com.nkbh.xuexue.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nkbh.xuexue.R;
import com.nkbh.xuexue.bean.CommunityReplyBean;
import com.nkbh.xuexue.utils.TimeUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by User on 2018/3/22.
 */

public class CommunityReplyAdapter extends RecyclerView.Adapter<CommunityReplyAdapter.ViewHolder> {

    private Context mContext;
    private List<CommunityReplyBean> data;

    public CommunityReplyAdapter(Context mContext, List<CommunityReplyBean> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_community_reply, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(mContext).load(data.get(position).getProfile_pic()).into(holder.ivReplyPic);
        holder.tvReplyName.setText(data.get(position).getName());
        holder.tvReplyContent.setText(data.get(position).getContent());
        holder.tvReplyTime.setText(TimeUtils.stamp2String(data.get(position).getCreate_time()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivReplyPic)
        CircleImageView ivReplyPic;
        @BindView(R.id.tvReplyName)
        TextView tvReplyName;
        @BindView(R.id.tvReplyContent)
        TextView tvReplyContent;
        @BindView(R.id.tvReplyTime)
        TextView tvReplyTime;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
