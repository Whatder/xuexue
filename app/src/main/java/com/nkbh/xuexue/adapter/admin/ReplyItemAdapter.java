package com.nkbh.xuexue.adapter.admin;

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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by User on 2018/4/3.
 */

public class ReplyItemAdapter extends RecyclerView.Adapter<ReplyItemAdapter.ViewHolder> {
    private Context mContext;
    private List<CommunityReplyBean> data;

    public ReplyItemAdapter(Context mContext, List<CommunityReplyBean> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_admin_reply, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(mContext).load(data.get(position).getProfile_pic()).into(holder.ivPic);
        holder.tvAuthor.setText(data.get(position).getName());
        holder.tvContent.setText(data.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivPic)
        CircleImageView ivPic;
        @BindView(R.id.tvAuthor)
        TextView tvAuthor;
        @BindView(R.id.tvContent)
        TextView tvContent;
        @BindView(R.id.tvDel)
        TextView tvDel;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
