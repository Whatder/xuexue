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
import com.nkbh.xuexue.bean.TopicBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by User on 2018/4/3.
 */

public class TopicItemAdapter extends RecyclerView.Adapter<TopicItemAdapter.ViewHolder> {
    private Context mContext;
    private List<TopicBean> data;
    private OnDeleteClick click;

    public TopicItemAdapter(Context mContext, List<TopicBean> data, OnDeleteClick click) {
        this.mContext = mContext;
        this.data = data;
        this.click = click;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_admin_topic, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Glide.with(mContext).load(data.get(position).getProfile_pic()).into(holder.ivPic);
        holder.tvAuthor.setText("作者：" + data.get(position).getName());
        holder.tvContent.setText("内容：" + data.get(position).getContent());
        holder.tvDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.OnDeleted(data.get(position));
            }
        });
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

    public interface OnDeleteClick {
        void OnDeleted(TopicBean topicBean);
    }
}
